package company.storage.dao;

import company.entityclass.Song;

import java.sql.SQLException;
import java.util.Map;
import java.util.UUID;

public interface SongDao {

    Song getSongById(UUID id);

    Map<UUID,Song> getAllSongs();

    void addSongs(Song ... song) throws SQLException;

    void deleteSongById(UUID id) throws SQLException;

    void updateSong(Song song) throws SQLException;
}
