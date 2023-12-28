package Prettier_Homes.controller;


import Prettier_Homes.dto.ContactsDto;
import Prettier_Homes.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping
public class ContactsController {
    @Autowired
    ContactsService service;


    @PostMapping
    public ResponseEntity<ContactsDto> create(@RequestBody ContactsDto dto){
        return service.create(dto);
    }
    @GetMapping("/contact-messages")
    public ResponseEntity<Page<ContactsDto>> getList(@RequestParam(name="q" , required = false)String search,
                                                     @RequestParam(value = "page", defaultValue = "0")int page,
                                                     @RequestParam(value = "size" , defaultValue = "20")int size,
                                                     @RequestParam(value = "sort", defaultValue = "id") String sort,
                                                     @RequestParam(value = "type", defaultValue = "desc") Sort.Direction direction)
    {
        return service.getList(search,page,size,sort, direction );
    }
}