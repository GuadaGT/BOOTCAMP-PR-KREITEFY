package com.kreitek.kreitefy.kreitefy.infraestructure.persistence.impl;

import com.kreitek.kreitefy.kreitefy.domain.entity.UserSong;
import com.kreitek.kreitefy.kreitefy.domain.persistence.UserSongPersistence;
import com.kreitek.kreitefy.kreitefy.infraestructure.persistence.UserSongJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserSongPersistenceImpl implements UserSongPersistence {

    private final UserSongJpaRepository userSongRepository;

    @Autowired
    public UserSongPersistenceImpl(UserSongJpaRepository userSongRepository) {
        this.userSongRepository = userSongRepository;
    }

    @Override
    public Optional<UserSong> findUserSongBySongIdAndUserId(Long songId, Long userId) {
        return userSongRepository.findBySongIdAndUserId(songId, userId);
    }

    @Override
    public UserSong saveSongUser(UserSong userSong) {
        return userSongRepository.save(userSong);
    }

    @Override
    public UserSong addRatingSong(UserSong userSong) {
        return userSongRepository.save(userSong);
    }

    @Override
    public Page<UserSong> getFavoriteSongsByUser(Long userId, Long rating, Pageable pageable, int size) {
        return userSongRepository.findFavoriteSongsByUser(userId, rating, pageable);
    }
}
