package org.jukebox.operation;

import org.jukebox.model.Podcast;
import org.jukebox.model.Song;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.jukebox.connection_db.Connector_db.con;


public class SongOperation {

    //Sorts arraylist of songs by song name.
    public static List<Song> sortSongListBySongName(List<Song> songList){
        return songList.stream().sorted(Comparator.comparing(s -> s.getSongName().toUpperCase())).collect(Collectors.toList());
    }

    //prints the songs present in list in tabular format
    public static void displaySongList(List<Song> songList){
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s %14s %26s %25s %21s %16s", "SONG ID", "SONG", "ARTIST", "ALBUM", "GENRE", "DURATION");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for(Song s: songList){
            System.out.printf("%s %28s %22s %28s %15s %15s", s.getSongId(), s.getSongName(), s.getArtistName(), s.getAlbumName(), s.getGenreName(), s.getSongDuration());
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    //Returns list of song by song name.
    public static List<Song> getSongListGivenSongName(String song_name, List<Song> songList) {
        List<Song> newSongList;
        newSongList = songList.stream().filter(song -> song.getSongName().toLowerCase().contains(song_name.toLowerCase())).collect(Collectors.toList());
        return newSongList;
    }

    //Returns list of song by an artist.
    public static List<Song> getSongListGivenArtistName(String artist_name, List<Song> songList) {
        List<Song> newSongList;
        newSongList = songList.stream().filter(song -> song.getArtistName().toLowerCase().contains(artist_name.toLowerCase())).collect(Collectors.toList());
        return newSongList;
    }

    //Returns list of song from a given album.
    public static List<Song> getSongListGivenAlbumName(String album_Name, List<Song> songList) {
        List<Song> newSongList;
        newSongList = songList.stream().filter(song -> song.getAlbumName().toLowerCase().contains(album_Name.toLowerCase())).collect(Collectors.toList());
        return newSongList;
    }

    //Returns list of song from a given genre.
    public static List<Song> getSongListGivenGenre(String genre_name, List<Song> songList) {
        List<Song> newSongList;
        newSongList = songList.stream().filter(song -> song.getGenreName().toLowerCase().contains(genre_name.toLowerCase())).collect(Collectors.toList());
        return newSongList;
    }

    //creating distinctByKey function to use in next method.
    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    //Displays all distinct album names.
    public static void displayAllAlbumNames( List<Song> songList) {
        List<Song> newSongList;
        songList.stream().filter(distinctByKey(s -> s.getAlbumName())).map(s->s.getAlbumName()).forEach(s-> System.out.println(s));
    }

    //returns a list containing only one Song related to specific songId.
    public static List<Song> getSongListContainingSingleSongGivenSongId(List<Song> songList, int song_Id){
        return songList.stream().filter(s->s.getSongId()==song_Id).collect(Collectors.toList());
    }
}
