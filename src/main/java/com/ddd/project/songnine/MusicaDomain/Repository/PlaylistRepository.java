package com.ddd.project.songnine.MusicaDomain.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddd.project.songnine.MusicaDomain.Playlist;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, UUID>{

}
