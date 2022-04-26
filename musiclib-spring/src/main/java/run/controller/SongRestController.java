package run.controller;

import company.entityclass.Song;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import run.services.SongService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/song-management")
public class SongRestController {
    private final SongService songService;

    public SongRestController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/song")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<List<Song>> getAllSong(){
        Collection<Song> song = songService.findAllSong();
        return new ResponseEntity<>(new ArrayList<>(song), HttpStatus.OK);
    }

    @GetMapping("/song/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Song> getSong(@PathVariable String id) throws SQLException {
       Song song = songService.getSong(UUID.fromString(id));
       return new ResponseEntity(song, HttpStatus.OK);
    }

    @PostMapping(value = "/song")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Song> addSong(@RequestBody Song song) throws SQLException {
        song.setId(UUID.randomUUID());
        songService.addSong(song);
        return new ResponseEntity<>(song, HttpStatus.CREATED);
    }

    @PutMapping(value =  "/song")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Song> updateSong(@RequestBody Song song) throws SQLException {
        songService.updateSong(song);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @DeleteMapping(value =  "/song/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Song> deleteSong(@PathVariable String id) throws SQLException {
        Song song = songService.deleteSong(UUID.fromString(id));
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

}
