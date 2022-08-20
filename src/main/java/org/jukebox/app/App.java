package org.jukebox.app;
import org.jukebox.connection_db.Connector_db;
import org.jukebox.dao.PlaylistDao;
import org.jukebox.dao.PodcastDao;
import org.jukebox.dao.SongDao;
import org.jukebox.dao.UserDao;
import org.jukebox.model.Podcast;
import org.jukebox.model.Song;
import org.jukebox.model.User;
import org.jukebox.musicplayer.MusicPlayer;
import org.jukebox.operation.PodcastOperation;
import org.jukebox.operation.SongOperation;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scan=new Scanner(System.in);
        try {
            Connector_db.establishConnection();
            String userName;
            String userPassword;
            int songId;
            int podcastId;
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tJUKEBOX\t\t\t\t\t\t\t\t\t\t\t\t\t( input 0 to exit ) ");
            int choice;
            do{
                System.out.print("\n1. SIGNUP \n2. LOGIN\n\nENTER YOUR CHOICE : ");
                choice=Integer.parseInt(scan.nextLine());
                if(choice==1 |choice==2) {
                    switch (choice) {
                        case 1:
                            System.out.print("USERNAME :");
                            userName = scan.nextLine();
                            System.out.print("PASSWORD :");
                            userPassword = scan.nextLine();
                            boolean accountCreated = UserDao.createUser(userName,userPassword);
                            if (accountCreated)
                                System.out.println("Congratulations, your account has been created successfully. Login and enjoy the music...");
                            else
                                System.out.println("The username and password already exists...");
                            break;
                        case 2:
                            System.out.print("USERNAME :");
                            userName = scan.nextLine();
                            System.out.print("PASSWORD :");
                            userPassword = scan.nextLine();
                            if (UserDao.login(userName,userPassword)) {
                                System.out.println("Login successful...");

                                User user = UserDao.getUser(userName,userPassword);                        //logged in user
                                List<Song> songList = SongDao.getSongList();                               //list of all songs from database
                                List<Podcast> podcastList = PodcastDao.getPodcastList();                   //list of all podcasts from database

                                int innerChoice;
                                do{
                                    System.out.print("\n1. ALL SONGS \t\t\t\t\t\t\t5. ENJOY TRENDING SONGS  \t\t\t\t\t\t\t\t\t\t\t\t( input 0 to sign-out )\n2. ALL PODCASTS \t\t\t\t\t\t6. ENJOY TRENDING PODCASTS\n3. SEARCH SONGS \t\t\t\t\t\t7. MY PLAYLISTS \n4. SEARCH PODCASTS \t\t\t\t\t\t8. MANAGE PLAYLISTS \n\nENTER YOUR CHOICE : ");
                                    innerChoice = Integer.parseInt(scan.nextLine());
                                    switch (innerChoice) {
                                        case 1:
                                            SongOperation.displaySongList(songList);
                                            do {
                                                System.out.print("\nENTER THE SONG ID TO PLAY : ");
                                                songId = Integer.parseInt(scan.nextLine());
                                                MusicPlayer.playSong(SongOperation.getSongListContainingSingleSongGivenSongId(songList, songId));
                                            }while (songId!=0);
                                            break;
                                        case 2:
                                            PodcastOperation.displayPodcastList(podcastList);
                                            do {
                                                System.out.print("\nENTER THE PODCAST ID TO PLAY : ");
                                                podcastId = Integer.parseInt(scan.nextLine());
                                                MusicPlayer.playPodcast(PodcastOperation.getPodcastListContainingSinglePodcastGivenPodcastId(podcastList, podcastId));
                                            }while (podcastId!=0);
                                            break;
                                        case 3:
                                            int innerChoice1;
                                            do {
                                                System.out.println("------------------------------------------------------------------------");
                                                System.out.print("\n1. SEARCH BY SONG NAME \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t( input 0 to sign-out )\n2. SEARCH BY ARTIST \n3. SEARCH BY ALBUM \n4. SEARCH BY MOOD/GENRE \n\nENTER YOUR CHOICE : ");
                                                innerChoice1= Integer.parseInt(scan.nextLine());
                                                switch (innerChoice1){
                                                    case 1:
                                                        System.out.print("Enter the song's name : ");
                                                        String songName=scan.nextLine();
                                                        SongOperation.displaySongList(SongOperation.getSongListGivenSongName(songName,songList));
                                                        System.out.print("\nENTER THE SONG ID TO PLAY : ");
                                                        songId = Integer.parseInt(scan.nextLine());
                                                        MusicPlayer.playSong(SongOperation.getSongListContainingSingleSongGivenSongId(songList,songId));
                                                    break;
                                                    case 2:
                                                        System.out.print("Enter the artist's name : ");
                                                        String artistName=scan.nextLine();
                                                        SongOperation.displaySongList(SongOperation.getSongListGivenArtistName(artistName,songList));
                                                        System.out.println("enjoy all songs by your favourite artist...");
                                                        MusicPlayer.playSong(SongOperation.getSongListGivenArtistName(artistName,songList));
                                                        break;
                                                    case 3:
                                                        System.out.print("Enter the album's name : ");
                                                        String albumName=scan.nextLine();
                                                        SongOperation.displaySongList(SongOperation.getSongListGivenAlbumName(albumName,songList));
                                                        System.out.println("enjoy all songs from album...");
                                                        MusicPlayer.playSong(SongOperation.getSongListGivenAlbumName(albumName,songList));
                                                        break;
                                                    case 4:
                                                        System.out.print("Enter the genre name : ");
                                                        String genreName=scan.nextLine();
                                                        SongOperation.displaySongList(SongOperation.getSongListGivenGenre(genreName,songList));
                                                        System.out.println("enjoy and elevate your mood...");
                                                        MusicPlayer.playSong(SongOperation.getSongListGivenGenre(genreName,songList));
                                                        break;
                                                }
                                            }while (innerChoice1 != 0);
                                            break;
                                        case 4:
                                            int innerChoice11;
                                            do {
                                                System.out.println("------------------------------------------------------------------------");
                                                System.out.print("\n1. SEARCH BY PODCAST NAME \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t( input 0 to sign-out )\n2. SEARCH BY CELEBRITY \n3. SEARCH BY PUBLISH DATE \n\nENTER YOUR CHOICE : ");
                                                innerChoice11= Integer.parseInt(scan.nextLine());
                                                switch (innerChoice11){
                                                    case 1:
                                                        System.out.print("Enter the podcast's name : ");
                                                        String podcastName=scan.nextLine();
                                                        PodcastOperation.displayPodcastList(PodcastOperation.getSongListGivenPodcastName(podcastName,podcastList));
                                                        System.out.print("\nENTER THE PODCAST ID TO PLAY : ");
                                                        podcastId = Integer.parseInt(scan.nextLine());
                                                        MusicPlayer.playPodcast(PodcastOperation.getPodcastListContainingSinglePodcastGivenPodcastId(podcastList,podcastId));
                                                        break;
                                                    case 2:
                                                        System.out.print("Enter the celebrity name : ");
                                                        String celebrityName=scan.nextLine();
                                                        PodcastOperation.displayPodcastList(PodcastOperation.getPodcastListGivenCelebrityName(celebrityName,podcastList));
                                                        System.out.println("listen all podcast by celebrity...");
                                                        MusicPlayer.playPodcast(PodcastOperation.getPodcastListGivenCelebrityName(celebrityName,podcastList));
                                                        break;
                                                    case 3:
                                                        System.out.print("Enter the date of publish : ");
                                                        String publish_date=scan.nextLine();
                                                        PodcastOperation.displayPodcastList(PodcastOperation.getPodcastListGivenPublishDate(publish_date,podcastList));
                                                        System.out.println("ENTER THE PODCAST ID TO LISTEN : ");
                                                        podcastId=Integer.parseInt(scan.nextLine());
                                                        MusicPlayer.playPodcast(PodcastOperation.getPodcastListContainingSinglePodcastGivenPodcastId(podcastList,podcastId));
                                                        break;
                                                }
                                            }while (innerChoice11 != 0);
                                            break;
                                            //    PodcastOperation.displayPodcastList(PodcastOperation.getPodcastListGivenPublishDate("11-05-2019",podcastList));
                                        case 5:
                                            System.out.println("relax, it will autoplay....");
                                            MusicPlayer.playSong(songList);
                                            break;
                                        case 6:
                                            System.out.println("relax, it will autoplay....");
                                            MusicPlayer.playPodcast(podcastList);
                                            break;
                                        case 7:
                                            //displaying existing playlists
                                            System.out.print("=================\nMY PLAYLISTS :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t( input 0 to sign-out )\n");
                                            PlaylistDao.displayAllPlaylistOfOneUser(user);
                                            System.out.println("=================");
                                            //asking which one to play
                                            System.out.print("NAME OF PLAYLIST : ");
                                            String playlist_name_to_play = scan.nextLine();
                                            System.out.println("PLAYLIST CONTENTS : ");
                                            List<Song> playlistSongList = PlaylistDao.getSongListFromPlaylist(user,playlist_name_to_play);
                                            List<Podcast> playlistPodcastList = PlaylistDao.getPodcastListFromPlaylist(user,playlist_name_to_play);
                                            SongOperation.displaySongList(playlistSongList);
                                            PodcastOperation.displayPodcastList(playlistPodcastList);
                                            System.out.println(playlistSongList.size()+playlistPodcastList.size()+" audios lined up...");
                                            //playing the playlist
                                            MusicPlayer.playSong(playlistSongList);
                                            MusicPlayer.playPodcast(playlistPodcastList);
                                            break;
                                        case 8:
                                            int innerChoice22;
                                            do {
                                                System.out.println("------------------------------------------------------------------------------------------------------------------");
                                                System.out.print("=================\nMY PLAYLISTS :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t( input 0 to sign-out )\n");
                                                //displaying existing playlists
                                                PlaylistDao.displayAllPlaylistOfOneUser(user);
                                                System.out.println("=================");
                                                System.out.print("1. CREATE PLAYLIST \n2. DELETE PLAYLIST \n3. ADD INTO EXISTING PLAYLIST \n\nENTER YOUR CHOICE : ");
                                                innerChoice22 = Integer.parseInt(scan.nextLine());
                                                switch (innerChoice22) {
                                                    case 1:
                                                        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------");
                                                        System.out.print("NAME YOUR PLAYLIST : ");
                                                        String playlist_name = scan.nextLine();
                                                        System.out.println("Playlist created! let's add something into it.");
                                                        int innerChoice30;
                                                        do {
                                                            System.out.print("\n1. ADD A SONG \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                                                                    "\t\t\t\t\t\t\t\t( input 0 to sign-out )\n2. ADD A PODCAST \n3. ADD AN ALBUM \n\nENTER YOUR CHOICE : ");
                                                            innerChoice30 = Integer.parseInt(scan.nextLine());
                                                            switch (innerChoice30) {
                                                                case 1:
                                                                    System.out.println("----------------------------------------------------------------------------------------------------------");
                                                                    SongOperation.displaySongList(songList);
                                                                    System.out.print("\nENTER THE SONG ID TO ADD : ");
                                                                    songId = Integer.parseInt(scan.nextLine());
                                                                    PlaylistDao.addSongToPlaylist(user.getUserId(), playlist_name, songId);
                                                                    System.out.println("song added...");
                                                                    break;

                                                                case 2:
                                                                    PodcastOperation.displayPodcastList(podcastList);
                                                                    System.out.print("\nENTER THE PODCAST ID TO ADD : ");
                                                                    podcastId = Integer.parseInt(scan.nextLine());
                                                                    PlaylistDao.addPodcastToPlaylist(user.getUserId(), playlist_name, podcastId);
                                                                    System.out.println("podcast added...");
                                                                    break;

                                                                case 3:
                                                                    System.out.println("ALL ALBUMS : ");
                                                                    SongOperation.displayAllAlbumNames(songList);
                                                                    System.out.print("\nWHICH ALBUM TO ADD ? : ");
                                                                    String albumName = scan.nextLine();
                                                                    PlaylistDao.addAlbumToPlaylist(user.getUserId(),playlist_name,SongOperation.getSongListGivenAlbumName(albumName,songList));
                                                                    System.out.println("album added...");
                                                                    break;
                                                            }
                                                        } while (innerChoice30 != 0);
                                                        break;
                                                    case 2:
                                                        System.out.println("------------------------------------------------------------------------------------------------------------------");
                                                        System.out.print("PLAYLIST NAME TO DELETE : ");
                                                        String playlist_name2 = scan.nextLine();
                                                        PlaylistDao.deletePlaylistOfaUser(user,playlist_name2);
                                                        System.out.println("playlist deleted successfully...");
                                                        break;
                                                    case 3:
                                                        System.out.println("------------------------------------------------------------------------------------------------------------------");
                                                        System.out.print("CHOOSE PLAYLIST : ");
                                                        String playlist_name1 = scan.nextLine();
                                                        System.out.println("playlist chosen! let's add something into it.");
                                                        int innerChoice32;
                                                        do {
                                                            System.out.print("\n1. ADD A SONG \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t( input 0 to sign-out )\n2. ADD A PODCAST \n3. ADD AN ALBUM \n\nENTER YOUR CHOICE : ");
                                                            innerChoice32 = Integer.parseInt(scan.nextLine());
                                                            switch (innerChoice32) {
                                                                case 1:
                                                                    System.out.println("------------------------------------------------------------------------------------------------------------------");
                                                                    SongOperation.displaySongList(songList);
                                                                    System.out.print("\nENTER THE SONG ID TO ADD : ");
                                                                    songId = Integer.parseInt(scan.nextLine());
                                                                    PlaylistDao.addSongToPlaylist(user.getUserId(), playlist_name1, songId);
                                                                    System.out.println("song added...");
                                                                    break;

                                                                case 2:
                                                                    PodcastOperation.displayPodcastList(podcastList);
                                                                    System.out.print("\nENTER THE PODCAST ID TO ADD : ");
                                                                    podcastId = Integer.parseInt(scan.nextLine());
                                                                    PlaylistDao.addPodcastToPlaylist(user.getUserId(), playlist_name1, podcastId);
                                                                    System.out.println("podcast added...");
                                                                    break;

                                                                case 3:
                                                                    System.out.println("ALL ALBUMS : ");
                                                                    SongOperation.displayAllAlbumNames(songList);
                                                                    System.out.print("\nWHICH ALBUM TO ADD ? : ");
                                                                    String albumName = scan.nextLine();
                                                                    PlaylistDao.addAlbumToPlaylist(user.getUserId(),playlist_name1,SongOperation.getSongListGivenAlbumName(albumName,songList));
                                                                    System.out.println("album added...");
                                                                    break;
                                                            }
                                                        } while (innerChoice32 != 0);
                                                        break;
                                                }
                                            }while (innerChoice22 != 0) ;
                                            break;
                                    }
                                    if(innerChoice==0) System.out.println("signed-out successfully...");
                                } while (innerChoice != 0);
                            } else System.out.println("login failed! Please try again.");
                    }
                }
                else if(choice!=0) System.out.println("invalid choice...");
                else System.out.println("Jukebox closed successfully...");
            }while(choice!=0);
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
        }finally {
            try {
                Connector_db.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


//sort alphabetically
//List<Song> songList=SongDao.getSongList();
//                                        SongOperation.displaySongList(SongOperation.sortSongListBySongName(songList));
//                                        System.out.print("\nENTER THE SONG ID TO PLAY :");
//                                        songId=Integer.parseInt(scan.nextLine());
//                                        System.out.println("playing...");
//                                        break;