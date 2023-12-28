package Prettier_Homes.controller;


import Prettier_Homes.dto.TourRequestDto;
import Prettier_Homes.service.TourRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tourRequest")
public class TourRequestController {

    @Autowired
    TourRequestService service;
    @PostMapping("/create")
    public ResponseEntity<TourRequestDto> createTourRequest(@RequestBody TourRequestDto dto) {
        return service.createTourRequest(dto);
    }



    @GetMapping
    public ResponseEntity<Page<TourRequestDto>> getTourRequests(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size,
            @RequestParam(value = "sort", defaultValue = "id") String sort,
            @RequestParam(value = "type", defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(name = "q", required = false, defaultValue = "") String search) {
        return service.getTourRequests(page, size, sort, direction, search);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TourRequestDto> getTourRequestById(@PathVariable Long id) {
        return service.getTourRequestById(id);
    }
//   @GetMapping("/{id}")
//   public ResponseEntity<TourRequestDto> getGuestUserById(@PathVariable Long id){
//        return service.getGuestUserBy_Id(id);
//   }
    @PutMapping("/{id}")
    public ResponseEntity<TourRequestDto> updateTourRequest(@PathVariable Long id,
                                                            @RequestBody TourRequestDto dto) {
        return service.updateTourRequest(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourRequestDto> deleteTourRequest(@PathVariable Long id) {
        return service.deleteTourRequest(id);
    }




}
