package run.services;

import company.entityclass.Song;
import company.storage.dao.SongDao;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class SongService{
    private final SongDao songRepo;

    public SongService(SongDao songRepo) {
        this.songRepo = songRepo;
    }

    public void addSong(Song song) throws SQLException {
        songRepo.addSongs(song);
    }

    public Collection<Song> findAllSong() {
        return songRepo.getAllSongs().values();
    }

    public Song deleteSong(UUID id) throws SQLException {
        Song song = songRepo.getSongById(id);
        songRepo.deleteSongById(id);
        return song;
    }

    public Song getSong(UUID id) throws SQLException {
       return songRepo.getSongById(id);
    }

    public Song updateSong(Song song) throws SQLException {
        songRepo.updateSong(song);
        return song;
    }
}
