package Prettier_Homes.service.Impl;


import Prettier_Homes.converter.ImagesMapper;
import Prettier_Homes.data.entity.AdvertsEntity;
import Prettier_Homes.data.entity.ImagesEntity;
import Prettier_Homes.data.repository.ImagesRepository;
import Prettier_Homes.dto.ImagesDto;
import Prettier_Homes.service.ImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    ImagesRepository repository;
    @Autowired
    ImagesMapper mapper;


    @Override
    public ResponseEntity<ImagesDto> get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Boolean> create(Long advertId, List<ImagesDto> imagesDto) {
       List<ImagesEntity> entity = mapper.toEntity(imagesDto);
       entity.forEach(x -> x.setAdvert(new AdvertsEntity(advertId)));
        System.out.println("img cservce1");
       repository.saveAll(entity);
        System.out.println("img ser2");
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ImagesDto> delete(Long imagesId) {
        return null;
    }

    @Override
    public ResponseEntity<ImagesDto> update(Long imagesId, ImagesDto images) {
        return null;
    }

    @Override
    public ResponseEntity<List<ImagesDto>> getImagesList(Long advert) {
        return new ResponseEntity<>(mapper.toDto(repository.findByAdvertId(advert)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ImagesDto>> getImagesByAdvertId(Long id) {
        List<ImagesDto> dtos = mapper.toDto(repository.findByAdvertId(id));
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


}
