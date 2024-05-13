package com.kreitek.kreitefy.kreitefy.application.service;

import com.kreitek.kreitefy.kreitefy.application.dto.UserSongDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserSongService {

    void addReproductions(Long songId, Long userId);

    void addRating(Long songId, Long userId, Long rating);

    Page<UserSongDto> getFavoriteSongs(Long userId, Long rating, Pageable pageable, int size);
}
