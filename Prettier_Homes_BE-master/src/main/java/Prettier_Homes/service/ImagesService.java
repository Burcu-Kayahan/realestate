package Prettier_Homes.service;

import Prettier_Homes.dto.ImagesDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ImagesService {
    ResponseEntity<ImagesDto> get(Long id);


    ResponseEntity<Boolean> create(Long advertId, List<ImagesDto> imagesDtos);

    ResponseEntity<ImagesDto> delete(Long imagesId);

    ResponseEntity<ImagesDto> update(Long imagesId, ImagesDto images);

    ResponseEntity<List<ImagesDto>> getImagesList(Long advert);

    ResponseEntity<List<ImagesDto>> getImagesByAdvertId(Long id);
}
