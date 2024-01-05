package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.AdvertMapper;
import Prettier_Homes.converter.CategoryProValuesMapper;
import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.enums.EnmAdvertStatus;
import Prettier_Homes.data.enums.EnmLog;
import Prettier_Homes.data.repository.*;
import Prettier_Homes.dto.AdvertsDto;
import Prettier_Homes.dto.CategoryPropertiesValuesDto;
import Prettier_Homes.dto.ImagesDto;
import Prettier_Homes.dto.requests.AdvertCreateRequest;
import Prettier_Homes.dto.response.*;
import Prettier_Homes.security.JwtUserDetails;
import Prettier_Homes.service.AdvertsService;
import Prettier_Homes.service.ImagesService;
import Prettier_Homes.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import java.util.Optional;
import java.util.Random;




@Service
public class AdvertServiceImpl implements AdvertsService{

    @Autowired
    AdvertsRepository repository;

    @Autowired
    AdvertMapper mapper;

    @Autowired
    CategoryPropertiesValueRepository valueRepository;
    @Autowired
    CategoryProValuesMapper valuesMapper;
    @Autowired
    ImagesService imagesService;
    @Autowired
    LogsRepository logsRepository;
    @Autowired
    FavoritesRepository favoritesRepository;
    @Autowired
    TourRequestRepository tourRequestRepository;
    @Autowired
    ImagesRepository imagesRepository;

    @Autowired
    LogsService logsService;

    @Override
    public ResponseEntity<Page<AdvertsDto>> getList(int page, int size, String sort, Sort.Direction direction, String search) {
        return null;
    }

    @Override
    public ResponseEntity<List<AdvertsDto>> getList() {
        List <AdvertsEntity> entities=repository.findAll();
        List<AdvertsDto> dtos=mapper.toDto(entities);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<AdvertCartRespons>> getListFilter(int page, int size, String sort, Sort.Direction direction, String search, Long category, Long advert_type, Double price_start, Double price_end, int status, Long country,Long city,Long district) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));
        Page<AdvertCartRespons> resultList= repository.getFilterByAdmin(search,category,advert_type,price_start, price_end,country,city,district,pageable);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<AdvertCreateRequest> create(AdvertCreateRequest dto, JwtUserDetails jwtUserDetails) {
        try{
            dto.setStatus(EnmAdvertStatus.Pending.name());
            AdvertsEntity entity = mapper.toEntity(dto).setCreateAt();

            do {
                entity.setSlug(createSlug(dto.getTitle()));
            }while (repository.findBySlug(entity.getSlug()).isPresent());
            System.out.println("service   : " +entity.getSlug());
            entity.setUser(new UserEntity().setId(jwtUserDetails.getId()));
            entity.setUpdateAt(LocalDateTime.now());
            AdvertsEntity entity1= repository.save(entity);
            AdvertCreateRequest result = mapper.toCreateDto(entity1);
            logsService.createLog(entity1, entity1.getUser(), EnmLog.CREATED );

           // logInfo.info(Log_Messages.ADVERT_CREATED_LOG_MESSAGES, result.getId());// burada parametre olarak birden fazla obje verebiliyoruz
            dto.getValues().forEach(x -> x.setAdvert(new AdvertsDto(result.getId())));
            result.setValues(valuesMapper.toDto(valueRepository.saveAll(valuesMapper.toEntity(dto.getValues()))));
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }catch (Exception e){
            //logInfo.error("Error: { }", e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @Override
    public ResponseEntity<List<AdvertsDto>> getAdvertsByUserId(Long userId) {
        List<AdvertsEntity> entities = repository.findByUserId(userId);

        return new ResponseEntity<>(mapper.toDto(entities), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<AdvertCartRespons>> getFilterByAdmin(int page, int size, String sort, Sort.Direction direction, String search, Long category, Long advert_type, Double price_start, Double price_end, Integer status, Long country,Long city,Long district) {
        Pageable pageable = PageRequest.of(page,size, Sort.by(direction,sort));
        Page<AdvertCartRespons> resultList =repository.getFilterByAdmin(search,category,advert_type,price_start, price_end ,country,city,district,pageable);
      //LOGrEPOSITORY.SAVE()
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }


    public String createSlug(String title){
        String modifiedString = title.replaceAll("\\s", "&");
        Random random = new Random();
        Long randomNumber = 999999 + (long) (Math.random() * (100001 - 999999));
        if(title.length()>0 && title.length()<=40){
            modifiedString= modifiedString  +"-"+randomNumber;
            return title; 
        }else if(title.length()>40) {
            modifiedString= modifiedString.substring(0,40);
            modifiedString= modifiedString+"-"+randomNumber;
            return title;
        }else {
            return "advert-"+randomNumber+"-";
        }
    }


    @Override
    public ResponseEntity<List<AdvertsCitiesResponse>> getGroupAdvertCity() {
        Pageable pageable = PageRequest.of(0,10);
        List<AdvertsCitiesResponse> resultList=repository.getGroupAdvertCity(pageable);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<AdvertsCountriesResponse>> getGroupAdvertCountry() {
        Pageable pageable = PageRequest.of(0,10);
        List<AdvertsCountriesResponse> resultList=repository.getGroupAdvertCountry(pageable);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<AdvertCartRespons>> getFilterByAuth(int page, int size, String sort, String search, JwtUserDetails jwtUserDetails) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(sort));
        Page<AdvertCartRespons> resultList =repository.getFilterByAuth(search,pageable,jwtUserDetails.getId());
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdvertDetailBySlugResponse> getAdvertDetailBySlug(String slug) {
        AdvertsDto dto = mapper.toDto(repository.findBySlug(slug).get());
        if(dto.getId() ==null)  return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        List<ImagesDto> imgDto = imagesService.getImagesByAdvertId(dto.getId()).getBody();
        List<CategoryPropertiesValuesDto> properties =valuesMapper.toDto(valueRepository.findByAdvertId(dto.getId()));
        AdvertDetailBySlugResponse result=new AdvertDetailBySlugResponse();
        result.setAdvert(dto);
        result.setValues(properties);
        result.setImges(imgDto);
        //todo burada db den list seklinde img veya imd-url cekilip respons clasa eklenmeli. url gonderebilirsek daha hizli olacak
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AdvertCreateRequest> update(AdvertCreateRequest dto, Long id) {
        Optional<AdvertsEntity> exist = repository.findBySlug(dto.getSlug());
        if(exist.isPresent()){
            if (exist.get().getUser().getId() != id){
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
        AdvertsEntity entity = mapper.toEntity(dto);
        entity.setUpdateAt(LocalDateTime.now());
        AdvertsEntity entity1= repository.save(entity);
        AdvertCreateRequest result = mapper.toCreateDto(entity1);
        result.setValues(valuesMapper.toDto(valueRepository.saveAll(valuesMapper.toEntity(dto.getValues()))));
        logsService.createLog(entity1, entity1.getUser(), EnmLog.UPDATED );
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<AdvertCreateRequest> updateForAdmin(AdvertCreateRequest dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        Optional<AdvertsEntity> exist = repository.findById(dto.getId());
        if(exist.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        AdvertsEntity entity = mapper.toEntity(dto);
        entity.setUpdateAt(LocalDateTime.now());
        AdvertsEntity entity1= repository.save(entity);
        AdvertCreateRequest result = mapper.toCreateDto(entity1);
        result.setValues(valuesMapper.toDto(valueRepository.saveAll(valuesMapper.toEntity(dto.getValues()))));
        UserEntity user = new UserEntity();
        user.setId(jwtUserDetails.getId());
        logsService.createLog(entity1,user,EnmLog.UPDATED );
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
    @Transactional
    @Override
    public ResponseEntity<DeleteRespons> delete(Long id, JwtUserDetails user) {
        Optional<AdvertsEntity> exist = repository.findById(id);
        if(exist.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(exist.get().getBuiltIn()) return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED); // built_in true ise silmeyecek

        if (user.getUserRole().getName().equals("CUSTOMER")){

            if (user.getId()== exist.get().getUser().getId()){
               //once diger tablolardaki elemanlar silinecek
                //todo burada diger silme islemleri yapilacak. advertId temizlenecek.
              return   deleteAll(id);
//                repository.deleteById(id);

            }else{
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
        //todo burada diger silme islemleri yapilacak. advertId temizlenecek.

//        repository.deleteById(id);

        return deleteAll(id);
    }


    @Transactional
    public ResponseEntity<DeleteRespons> deleteAll(Long id){
        tourRequestRepository.deleteByAdvertId(id);
        System.out.println("toru");
        favoritesRepository.deleteByAdvertId(id);
        System.out.println("2");
        imagesRepository.deleteByAdvertId(id);
        System.out.println("3");
        logsRepository.deleteByAdvertId(id);
        System.out.println("4");
        valueRepository.deleteByAdvertId(id);
        System.out.println("5");
        repository.deleteById(id);
        DeleteRespons response = new DeleteRespons();
        response.setDelete(true);
        response.setMessage("Delete Success");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @Override
    public ResponseEntity<List<AdvertCartRespons>> getPopulerList(Long amount) {
            Pageable pageable = PageRequest.of(0, Math.toIntExact(amount));
            List<AdvertCartRespons> resultList = repository.getPopulerList(pageable);
            return new ResponseEntity<>(resultList, HttpStatus.OK);
        }

    @Override
    public ResponseEntity<Page<AdvertCartRespons>> getAdvertsByUser(Long id, int page, int size, String sort, JwtUserDetails jwtUserDetails) {
        Pageable pageable = PageRequest.of(page,size,Sort.by(sort));
        Page<AdvertCartRespons> resultList =repository.getFilterByAuth("",pageable,id);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}



