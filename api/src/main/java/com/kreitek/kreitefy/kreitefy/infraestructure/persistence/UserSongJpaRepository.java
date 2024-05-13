package com.kreitek.kreitefy.kreitefy.infraestructure.persistence;

import com.kreitek.kreitefy.kreitefy.domain.entity.UserSong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserSongJpaRepository extends JpaRepository<UserSong, Long>, JpaSpecificationExecutor<UserSong> {

    Optional<UserSong> findBySongIdAndUserId(Long songId, Long userId);

    @Query("SELECT us FROM UserSong us WHERE us.user.id = :userId AND us.rating >= :rating")
    Page<UserSong> findFavoriteSongsByUser(@Param("userId") Long userId, @Param("rating") Long rating, Pageable pageable);

}
