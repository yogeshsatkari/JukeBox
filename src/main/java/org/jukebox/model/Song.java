package org.jukebox.model;

import java.sql.Time;
import java.util.Objects;

public class Song {
    private int songId;
    private String songName;
    private String albumName;
    private String artistName;
    private String genreName;
    private Time songDuration;
    private String songUrl;

    //constructor
    public Song(int songId, String songName, String albumName, String artistName, String genreName, Time songDuration, String songUrl) {
        this.songId = songId;
        this.songName = songName;
        this.albumName = albumName;
        this.artistName = artistName;
        this.genreName = genreName;
        this.songDuration = songDuration;
        this.songUrl = songUrl;
    }

    //getters
    public int getSongId() {
        return songId;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getGenreName() {
        return genreName;
    }

    public Time getSongDuration() {
        return songDuration;
    }

    public String getSongUrl() {
        return songUrl;
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", albumName='" + albumName + '\'' +
                ", artistName='" + artistName + '\'' +
                ", genreName='" + genreName + '\'' +
                ", songDuration=" + songDuration +
                ", songUrl='" + songUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return songId == song.songId && Objects.equals(songName, song.songName) && Objects.equals(albumName, song.albumName) && Objects.equals(artistName, song.artistName) && Objects.equals(genreName, song.genreName) && Objects.equals(songDuration, song.songDuration) && Objects.equals(songUrl, song.songUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songId, songName, albumName, artistName, genreName, songDuration, songUrl);
    }
}

