package com.kreitek.kreitefy.kreitefy.domain.persistence;
import com.kreitek.kreitefy.kreitefy.domain.entity.UserSong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserSongPersistence {

    Optional<UserSong> findUserSongBySongIdAndUserId(Long songId, Long userId);

    UserSong saveSongUser(UserSong userSong);

    UserSong addRatingSong(UserSong userSong);

    Page<UserSong> getFavoriteSongsByUser(Long userId, Long rating, Pageable pageable, int size);
}
