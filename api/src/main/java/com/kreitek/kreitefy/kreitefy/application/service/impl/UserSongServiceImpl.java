package com.kreitek.kreitefy.kreitefy.application.service.impl;

import com.kreitek.kreitefy.kreitefy.application.dto.UserSongDto;
import com.kreitek.kreitefy.kreitefy.application.mapper.SongMapper;
import com.kreitek.kreitefy.kreitefy.application.mapper.UserMapper;
import com.kreitek.kreitefy.kreitefy.application.mapper.UserSongMapper;
import com.kreitek.kreitefy.kreitefy.application.service.SongService;
import com.kreitek.kreitefy.kreitefy.application.service.UserSongService;
import com.kreitek.kreitefy.kreitefy.domain.entity.Song;
import com.kreitek.kreitefy.kreitefy.domain.entity.User;
import com.kreitek.kreitefy.kreitefy.domain.entity.UserSong;
import com.kreitek.kreitefy.kreitefy.domain.entity.associative.UserSongAssociative;
import com.kreitek.kreitefy.kreitefy.domain.persistence.UserSongPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserSongServiceImpl implements UserSongService {

    private final UserSongPersistence userSongPersistence;
    private final SongMapper songMapper;
    private final UserMapper userMapper;
    private final SongService songService;
    private final UserSongMapper userSongMapper;

    @Autowired
    public UserSongServiceImpl(UserSongPersistence userSongPersistence, SongMapper songMapper, UserMapper userMapper, SongService songService, UserSongMapper userSongMapper) {
        this.userSongPersistence = userSongPersistence;
        this.songMapper = songMapper;
        this.userMapper = userMapper;
        this.songService = songService;
        this.userSongMapper = userSongMapper;
    }

    @Override
    @Transactional
    public void addReproductions(Long songId, Long userId) {
        songService.addReproductionSong(songId);
        Optional<UserSong> userSongOptional = userSongPersistence.findUserSongBySongIdAndUserId(songId, userId);
        LocalDateTime now = LocalDateTime.now();
        if (userSongOptional.isPresent()) {
            UserSong userSongToUpdate = userSongOptional.get();
            userSongToUpdate.setReproductions(userSongToUpdate.getReproductions() + 1);
            userSongToUpdate.setReproductionDate(now);
            userSongPersistence.saveSongUser(userSongToUpdate);
        } else {
            UserSong userSongToUpdate = new UserSong();
            UserSongAssociative id = new UserSongAssociative();
            id.setUserId(userId);
            id.setSongId(songId);
            userSongToUpdate.setId(id);
            userSongToUpdate.setReproductions(1L);
            userSongToUpdate.setReproductionDate(now);

            User user = userMapper.fromId(userId);
            Song song = songMapper.fromId(songId);
            userSongToUpdate.setUser(user);
            userSongToUpdate.setSong(song);

            userSongPersistence.saveSongUser(userSongToUpdate);
        }
    }

    @Override
    @Transactional
    public void addRating(Long songId, Long userId, Long rating) {
        songService.addRatingSong(songId);
        Optional<UserSong> userSongOptional = userSongPersistence.findUserSongBySongIdAndUserId(songId, userId);
        if (userSongOptional.isPresent()) {
            UserSong userSongToUpdate = userSongOptional.get();
            Long currentRating = userSongToUpdate.getRating();
            userSongToUpdate.setRating(currentRating + rating);
            userSongPersistence.addRatingSong(userSongToUpdate);
        } else {
            UserSong userSongToUpdate = new UserSong();
            UserSongAssociative id = new UserSongAssociative();
            id.setUserId(userId);
            id.setSongId(songId);
            userSongToUpdate.setId(id);
            userSongToUpdate.setRating(rating);

            User user = userMapper.fromId(userId);
            Song song = songMapper.fromId(songId);
            userSongToUpdate.setUser(user);
            userSongToUpdate.setSong(song);

            userSongPersistence.addRatingSong(userSongToUpdate);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserSongDto> getFavoriteSongs(Long userId, Long rating, Pageable pageable, int size) {
        return userSongPersistence.getFavoriteSongsByUser(userId, rating, pageable, size)
                .map(userSongMapper::toDto);
    }
}
