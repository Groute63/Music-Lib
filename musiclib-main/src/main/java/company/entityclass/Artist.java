package company.entityclass;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Artist implements Serializable,EntityClassMarker {
    private String name;
    private List<Album> albums;
    private UUID id;

    public Artist(UUID id,String name) {
        this.id = id;
        this.name = name;
        this.albums = new ArrayList<>();
    }

    public Artist() {

    }

    public Artist(String name, Album... albums) {
        id = UUID.randomUUID();
        this.name = name;
        this.albums = new ArrayList<>(albums.length);
        Collections.addAll(this.albums, albums);
    }

    public Artist(UUID id,String name, Album... albums) {
        this.id = id;
        this.name = name;
        this.albums = new ArrayList<>(albums.length);
        Collections.addAll(this.albums, albums);
    }

    public void setId(UUID newId){
        id=newId;
    }

    public UUID getId(){
        return id;
    }

    public String toString() {
        StringBuffer b = new StringBuffer();
        b.append("Artist{NAME - ").append(name).append('\n');
        for (int i = 0; i < albums.size(); i++)
            b.append("       ").append(albums.get(i)).append('\n');
        b.append("       }");
        return b.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public void addAlbums(Album... albums) {
        Collections.addAll(this.albums, albums);
    }

    public void setAlbums(Album... albums) {
        this.albums = new ArrayList<>();
        Collections.addAll(this.albums, albums);
    }

    public ArrayList<String> getTags() {
        ArrayList<String> tags = new ArrayList<>();
        for (int i = 0; i < albums.size(); i++) {
            tags.addAll(albums.get(i).getTags());
        }
        return tags;
    }

    public ArrayList<Song> getSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        for (int i = 0; i < albums.size(); i++) {
            songs.addAll(albums.get(i).getSongs());
        }
        return songs;
    }

}