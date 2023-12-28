package Prettier_Homes.service;

import Prettier_Homes.dto.WordsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

public interface WordsService {

    ResponseEntity<WordsDto> create(WordsDto dto);

    ResponseEntity<WordsDto> getById(Long id);

    ResponseEntity<WordsDto> update(Long id, WordsDto dto);

    ResponseEntity<WordsDto> delete(Long id);

    ResponseEntity<Page<WordsDto>> getList(int page, int size, String sort, Sort.Direction direction, String search);
}
