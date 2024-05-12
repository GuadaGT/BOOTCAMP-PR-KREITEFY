package com.kreitek.kreitefy.kreitefy.infraestructure.rest;
import com.kreitek.kreitefy.kreitefy.application.dto.StyleDto;
import com.kreitek.kreitefy.kreitefy.application.service.StyleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class StyleController {

    private final StyleService styleService;

    public StyleController(StyleService styleService) {
        this.styleService = styleService;
    }

    @GetMapping(value = "/styles", produces = "application/json")
    ResponseEntity<Page<StyleDto>> getStyles(Pageable pageable, @RequestParam(value = "filter", required = false) String search) {
        Page<StyleDto> styles = this.styleService.getAllStylesByCriteria(pageable, search);
        return new ResponseEntity<>(styles, HttpStatus.OK);
    }

    @GetMapping(value = "/styles/{styleId}", produces = "application/json")
    ResponseEntity<StyleDto> getStyle(@PathVariable Long styleId) {
        return styleService.getStyleById(styleId)
                .map(styleDto -> new ResponseEntity<>(styleDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/styles", produces = "application/json", consumes = "application/json")
    ResponseEntity<StyleDto> newStyle(@RequestBody StyleDto styleDto) {
        return new ResponseEntity<>(styleService.createStyle(styleDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/styles/{stylesId}", produces = "application/json", consumes = "application/json")
    ResponseEntity<StyleDto> updateStyle(@PathVariable Long stylesId,@RequestBody StyleDto styleDto) {
      Optional<StyleDto> existingStyle = this.styleService.updateStyle(stylesId, styleDto);
      if (existingStyle.isPresent()) {
          return new ResponseEntity<>(existingStyle.get(), HttpStatus.OK);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @DeleteMapping(value = "/styles/{songId}", produces = "application/json")
    ResponseEntity<Void> deleteStyleById(@PathVariable Long styleId) {
        styleService.deleteStyleById(styleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
