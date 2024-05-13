package com.kreitek.kreitefy.kreitefy.infraestructure.rest;

import com.kreitek.kreitefy.kreitefy.application.dto.ReproductionsDto;
import com.kreitek.kreitefy.kreitefy.application.dto.UserSongDto;
import com.kreitek.kreitefy.kreitefy.application.service.ReproductionsService;
import com.kreitek.kreitefy.kreitefy.application.service.UserSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserSongController {

    private final UserSongService userSongService;
    private final ReproductionsService reproductionsService;
    private static final Long DEFAULT_RATING = 3L;

    @Autowired
    public UserSongController(UserSongService userSongService, ReproductionsService reproductionsService) {
        this.userSongService = userSongService;
        this.reproductionsService = reproductionsService;
    }

    @PutMapping(value = "/song/{songId}/user/{userId}/reproductions", produces = "application/json")
    public ResponseEntity<UserSongDto> addReproduction(@PathVariable Long songId, @PathVariable Long userId) {
        try {
            userSongService.addReproductions(songId, userId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user/{userId}/reproductions/count", produces = "application/json")
    public ResponseEntity<Long> getReproductionsCountByUser(@PathVariable Long userId) {
        try {
            Long count = reproductionsService.getReproductionsCountByUserId(userId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping(value = "/song/{songId}/user/{userId}/rating", produces = "application/json")
    public ResponseEntity<UserSongDto> addRating(@PathVariable Long songId, @PathVariable Long userId, @RequestParam("rating") Long rating) {
        try {
            userSongService.addRating(songId, userId, rating);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/user/{userId}/reproductions", produces = "application/json")
    public ResponseEntity<List<ReproductionsDto>> getReproductionsByUser(@PathVariable Long userId, Pageable pageable){
        Page<ReproductionsDto> reproductionsPage = reproductionsService.getReproductionsByUserId(pageable, userId);
        return new ResponseEntity<>(reproductionsPage.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}/ratings", produces = "application/json")
    public ResponseEntity<Page<UserSongDto>> getUserRatings(@PathVariable Long userId,
                                                            Pageable pageable,
                                                            @RequestParam(defaultValue = "5") int size) {
        try {
            Page<UserSongDto> userSongPage = userSongService.getFavoriteSongs(userId, DEFAULT_RATING, pageable, size);
            return new ResponseEntity<>(userSongPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
