package org.jukebox.app;

import org.jukebox.model.Podcast;
import org.jukebox.model.Song;
import org.jukebox.operation.PodcastOperation;
import org.jukebox.operation.SongOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.sql.Time;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppTest {
    List<Song> songList = new ArrayList<>();
    List<Podcast> podcastList = new ArrayList<>();

    @Before
    public void setUp() {
        Song song1=new Song(1, "Everything At Once", "Narrated For You", "Lenka", "Pop", new Time(1524), "D:\\jukeboxAudioFiles\\song\\Everything At Once.wav");
        Song song2=new Song(2, "Hold me now", "Fifty Shades of Grey", "Westlife", "Romantic", new Time(45624), "D:\\jukeboxAudioFiles\\song\\Hold me now.wav");
        Song song3=new Song(3, "ARON", "dfs", "fsdf", "fsfsdf", new Time(45624), "D:\\jukeboxAudioFiles\\song\\Hold me now.wav");
        songList.add(song1);
        songList.add(song2);
        songList.add(song3);

        Podcast podcast1=new Podcast(1, "Everything At Once", "Joe", new Date(42342),  "D:\\jukeboxAudioFiles\\song\\Everything At Once.wav");
        Podcast podcast2=new Podcast(2, "Hold me now", "Akon",new Date(343), "D:\\jukeboxAudioFiles\\song\\Hold me now.wav");
        Podcast podcast3=new Podcast(3, "Amber", "Yogesh",new Date(34853), "D:\\jukeboxAudioFiles\\song\\Hold me now.wav");
        podcastList.add(podcast1);
        podcastList.add(podcast2);
        podcastList.add(podcast3);

    }

    @AfterEach
    void tearDown() {songList=null;}


    @Test
    public void checkIfGettingSongListGivenSongName() {
        List<Song> lst = SongOperation.getSongListGivenSongName("Hold me now",songList);
        Assertions.assertEquals(1,lst.size());
    }

    @Test
    public void checkIfGettingPodcastListGivenAlbumName(){
        List<Song> lst = SongOperation.getSongListGivenAlbumName("Narrated",songList);
        Assertions.assertEquals(1,lst.size());

    }


    @Test
    public void checkIfGettingSongListGivenArtistName() {
        List<Song> lst = SongOperation.getSongListGivenArtistName("lenka",songList);
        Assertions.assertEquals(1,lst.size());
    }

    @Test
    public void checkIfGettingSongListGivenGenreName(){
        List<Song> lst = SongOperation.getSongListGivenGenre("Love",songList);
        Assertions.assertEquals(0,lst.size());

    }


    @Test
    public void checkIfGettingPodcastListGivenCelebrityName() {
        List<Podcast> lst = PodcastOperation.getPodcastListGivenCelebrityName("Akon",podcastList);
        Assertions.assertEquals(1,lst.size());
    }

    @Test
    public void checkIfGettingPodcastListGivenPodcastDate() throws ParseException {
        List<Podcast> lst = PodcastOperation.getPodcastListGivenPublishDate("02-02-2011",podcastList);
        Assertions.assertEquals(0,lst.size());
    }

    @Test
    public void checkingSortSongListBySongName(){
        Song song3=new Song(3, "ARON", "dfs", "fsdf", "fsfsdf", new Time(45624), "D:\\jukeboxAudioFiles\\song\\Hold me now.wav");
        List<Song> lst=SongOperation.sortSongListBySongName(songList);
        Assertions.assertEquals(lst.get(0),song3);
    }

    @Test
    public void checkingSortPodcastListByPodcastName(){
        Podcast podcast3=new Podcast(3, "Amber", "Yogesh",new Date(34853), "D:\\jukeboxAudioFiles\\song\\Hold me now.wav");
        List<Podcast> lst=PodcastOperation.sortPodcastListByPodcastName(podcastList);
        Assertions.assertEquals(lst.get(0),podcast3);
    }

}
