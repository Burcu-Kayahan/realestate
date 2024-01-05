package Prettier_Homes.service.Impl;

import Prettier_Homes.converter.TourRequestMapper;

import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.entity.TourRequestEntity;
import Prettier_Homes.data.entity.UserEntity;
import Prettier_Homes.data.enums.EnmLog;
import Prettier_Homes.data.enums.EnmTourReqStatus;
import Prettier_Homes.data.repository.AdvertsRepository;


import Prettier_Homes.data.repository.TourRequestRepository;
import Prettier_Homes.dto.CategoriesDto;
import Prettier_Homes.dto.TourRequestDto;
import Prettier_Homes.security.JwtUserDetails;
import Prettier_Homes.service.LogsService;
import Prettier_Homes.service.TourRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class TourRequestServiceImpl implements TourRequestService {
    @Autowired
    TourRequestRepository repository;

    @Autowired
    TourRequestMapper mapper;
    @Autowired
    AdvertsRepository advertsRepository;
    @Autowired
    LogsService logsService;


    @Override
    public ResponseEntity<List<TourRequestDto>> getAllByUserId(Long userId) {
        return null;
    }



    @Override
    public ResponseEntity<Page<TourRequestDto>> getTourRequests(int page, int size, String sort, Sort.Direction direction, String search) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        Page<TourRequestEntity> entities = repository.findByAdvertTitleOrGuestUserEmailOrOwnerUserEmail(search, search, search, pageable);
        Page<TourRequestDto> results = entities.map(entity -> mapper.toDto(entity));
        results = new PageImpl<>(results.getContent(), pageable, entities.getTotalElements());
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<TourRequestDto> getTourRequestById(Long id) {
        // TODO: Implement method logic here
        return null;
    }

    @Override

    public ResponseEntity<TourRequestDto> create(TourRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        UserEntity user = new UserEntity();
        user.setId(jwtUserDetails.getId());
        Optional<AdvertsEntity> advert = advertsRepository.findById(dto.getAdvert().getId());
        dto.setStatus("Pending");
        TourRequestEntity entity = mapper.toEntity(dto);
        entity.setCreatAt();
        entity.setGuestUser(user);
        if(advert.isEmpty()) return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        entity.setAdvert(advert.get());
        entity.setOwnerUser(advert.get().getUser());
        entity.setStatus(EnmTourReqStatus.Pending);
        TourRequestEntity tourRequestEntity = repository.save(entity);
        logsService.createLog(advert.get(),tourRequestEntity.getGuestUser(), EnmLog.TOUR_REQUEST_CREATED);
        return new ResponseEntity<>(mapper.toDto(tourRequestEntity), HttpStatus.OK);
    }

    public ResponseEntity<TourRequestDto> updateTourRequest(Long id, TourRequestDto dto) {
        Optional<TourRequestEntity> exists = repository.findById(id);
        if (exists.isEmpty()) {
            throw new RuntimeException("Data could not be found: " + id);
        }
        mapper.toEntityForUpdate(dto, exists.get());
        TourRequestEntity entity = repository.save(exists.get());
        TourRequestDto result = mapper.toDto(entity);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<TourRequestDto> deleteTourRequest(Long id) {
        Optional<TourRequestEntity> exists = repository.findById(id);

        if (exists.isEmpty()) {
            throw new RuntimeException("Data could not be found: " + id);
        }
        repository.deleteById(id);
        return new ResponseEntity<>(mapper.toDto(exists.get()), HttpStatus.OK);
    }


//    @Override
//    public ResponseEntity<TourRequestDto> getGuestUserBy_Id(Long id) {
//       TourRequestDto result = mapper.toDto(repository.findByGuestUser_Id(id));
//        return new ResponseEntity<>(result,HttpStatus.OK);
//    }



}

