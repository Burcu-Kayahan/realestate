package Prettier_Homes.service;

import Prettier_Homes.dto.TourRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.net.CacheRequest;
import java.util.List;

public interface TourRequestService {

   ResponseEntity<List<TourRequestDto>> getAllByUserId(Long userId);

    ResponseEntity<TourRequestDto> create(TourRequestDto dto);

 ResponseEntity<Page<TourRequestDto>> getTourRequests(int page, int size, String sort, Sort.Direction direction, String search);

 ResponseEntity<TourRequestDto> getTourRequestById(Long id);

 ResponseEntity<TourRequestDto> updateTourRequest(Long id, TourRequestDto dto);

 ResponseEntity<TourRequestDto> deleteTourRequest(Long id);

 //ResponseEntity<TourRequestDto> getGuestUserBy_Id(Long id);

}
