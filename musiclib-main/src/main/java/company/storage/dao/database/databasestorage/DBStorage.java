package company.storage.dao.database.databasestorage;

import company.entityclass.Album;
import company.entityclass.Artist;
import company.entityclass.EntityClassMarker;
import company.entityclass.Song;
import company.storage.dao.DaoType;
import company.storage.Storage;
import company.storage.dao.AlbumDao;
import company.storage.dao.ArtistDao;
import company.storage.dao.SongDao;
import company.storage.dao.database.dao.DataBaseAlbumDao;
import company.storage.dao.database.dao.DataBaseArtistDao;
import company.storage.dao.database.dao.DataBaseSongDao;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.*;

public class DBStorage implements Storage, AutoCloseable {

    private static DBStorage dbStorage;
   // private String dbUrl = "jdbc:postgresql://localhost:5432/musiclib33";
   // private String dbUserName = "postgres";
   // private String dbPassword = "092327asd";
    private Connection con = null;
    private SongDao songs;
    private AlbumDao albums;
    private ArtistDao artists;
    private PreparedStatement selectLogin;
    private static final Object sync = new Object();

    private DBStorage(MyConnection con) {
        try {
            this.con = con.getConnection();
            songs = new DataBaseSongDao(con);
            albums = new DataBaseAlbumDao(con);
            artists = new DataBaseArtistDao(con);
            //getConnection();
            init();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static DBStorage getInstance() {
        if (dbStorage == null) {
            synchronized (sync) {
                if (dbStorage == null) {
                    dbStorage = new DBStorage(new MyConnection());
                }
            }
        }
        return dbStorage;
    }

  /*  public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }*/

    private void init() throws SQLException {
        selectLogin = con.prepareStatement("SELECT acc FROM users  WHERE login = ? and password = ?");
    }

   /* public void getConnection() throws SQLException {
        con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        MyConnection daoCon =  new MyConnection(con);
        songs = new DataBaseSongDao(daoCon);
        albums = new DataBaseAlbumDao(daoCon);
        artists = new DataBaseArtistDao(daoCon);
        con.setAutoCommit(false);
    }*/

    public int login(String login, String password) {
        try {
            password = getHash(password);
            selectLogin.setString(1, login);
            selectLogin.setString(2, password);
            ResultSet res = selectLogin.executeQuery();
            if (res.next()) return res.getInt(1);
        } catch (SQLException | NoSuchAlgorithmException throwable) {
            throwable.printStackTrace();
        }
        return 0;
    }

    public static String getHash(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.update(password.getBytes());
        return String.valueOf(Hex.encodeHex(messageDigest.digest()));
    }

    @Override
    public Map<UUID, ? extends EntityClassMarker> get(DaoType type) {
        switch (type) {
            case SONG: {
                return songs.getAllSongs();
            }
            case ALBUM: {
                return albums.getAllAlbum();
            }
            case ARTIST: {
                return artists.getAllArtist();
            }
            default:
                return null;
        }
    }

    @Override
    public void add(EntityClassMarker obj, DaoType type) {
        try {
            switch (type) {
                case ARTIST: {
                    artists.addArtist((Artist) obj);
                    break;
                }
                case ALBUM: {
                    albums.addAlbum((Album) obj);
                    break;
                }
                case SONG: {
                    songs.addSongs((Song) obj);
                    break;
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void printAll(DaoType type) {
        switch (type) {
            case SONG: {
                Collection<Song> a = songs.getAllSongs().values();
                for (Song s : a)
                    System.out.println(s);
                break;
            }
            case ALBUM: {
                Collection<Album> a = albums.getAllAlbum().values();
                for (Album b : a)
                    System.out.println(b);
                break;
            }
            case ARTIST: {
                Collection<Artist> a = artists.getAllArtist().values();
                for (Artist b : a)
                    System.out.println(b);
                break;
            }
        }
    }

    @Override
    public void printName(DaoType type) {
        int i = 0;
        switch (type) {
            case SONG: {
                Collection<Song> b = songs.getAllSongs().values();
                for (Song a : b) {
                    System.out.println(i + " - " + a.getName());
                    i++;
                }
                break;
            }
            case ALBUM: {
                Collection<Album> b = albums.getAllAlbum().values();
                for (Album a : b) {
                    System.out.println(i + " - " + a.getName());
                    i++;
                }
                break;
            }
            case ARTIST: {
                Collection<Artist> b = artists.getAllArtist().values();
                for (Artist a : b) {
                    System.out.println(i + " - " + a.getName());
                    i++;
                }
                break;
            }
        }

    }

    @Override
    public void delete(UUID id, DaoType type) {
        try {
            switch (type) {
                case SONG: {
                    songs.deleteSongById(id);
                    break;
                }
                case ALBUM: {
                    albums.deleteAlbumById(id);
                    break;
                }
                case ARTIST: {
                    artists.deleteArtistById(id);
                    break;
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void addIn(UUID id, UUID id2, DaoType type) {
        try {
            switch (type) {
                case ALBUM: {
                    albums.getAlbumById(id).addSongs(songs.getSongById(id2));
                    break;
                }
                case ARTIST: {
                    artists.addNewAlbum(id, albums.getAlbumById(id2));
                    break;
                }
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void close() {
        try {
            con.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
