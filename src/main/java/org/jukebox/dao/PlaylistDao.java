package org.jukebox.dao;

import org.jukebox.model.Podcast;
import org.jukebox.model.Song;
import org.jukebox.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.jukebox.connection_db.Connector_db.con;

public class PlaylistDao {

    public static void addSongToPlaylist(int user_Id, String playlist_name, int song_id) throws SQLException {
        PreparedStatement ps=con.prepareStatement("insert ignore into playlistTable select ?,?, audioId from audioTable where songId = ?");
        ps.setInt(1,user_Id);
        ps.setString(2,playlist_name);
        ps.setInt(3,song_id);
        ps.executeUpdate();
        ps.close();
    }

    public static void addPodcastToPlaylist(int user_Id, String playlist_name, int podcast_id) throws SQLException{
        PreparedStatement ps=con.prepareStatement("insert ignore into playlistTable select ?,?, audioId from audioTable where podcastId = ?");
        ps.setInt(1,user_Id);
        ps.setString(2,playlist_name);
        ps.setInt(3,podcast_id);
        ps.executeUpdate();
        ps.close();

    }

    public static void addAlbumToPlaylist(int user_Id, String playlist_name, List<Song> albumSongList) throws SQLException {
        for(Song s : albumSongList){
            PreparedStatement ps=con.prepareStatement("insert ignore into playlistTable select ?,?, audioId from audioTable where songId = ?");
            ps.setInt(1,user_Id);
            ps.setString(2,playlist_name);
            ps.setInt(3,s.getSongId());
            ps.executeUpdate();
            ps.close();
        }
    }

    public static void displayAllPlaylistOfOneUser(User user) throws SQLException{
        PreparedStatement ps=con.prepareStatement("select distinct (playlistName) from playlistTable where userId=?");
        ps.setInt(1,user.getUserId());
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            System.out.println(rs.getString(1));
        }
        rs.close();
        ps.close();
    }

    //get list of all songs in a playlist
    public static List<Song> getSongListFromPlaylist(User user,String playlist_name) throws SQLException{                                                                                       //to play a playlist
        List<Song> playlistSongList=new ArrayList<>();
        PreparedStatement ps=con.prepareStatement("select * from songTable where songId in(select songId from audioTable where audioId in(select audioId from playlistTable where userId=? and playlistName=?))");
        ps.setInt(1,user.getUserId());
        ps.setString(2,playlist_name);
        ResultSet rs=ps.executeQuery();
            while (rs.next()){
                int songId=rs.getInt(1);
                String songName=rs.getString(2);
                String albumName=rs.getString(3);
                String artistName=rs.getString(4);
                String genreName=rs.getString(5);
                Time songDuration=rs.getTime(6);
                String songUrl=rs.getString(7);
                Song song=new Song(songId,songName,albumName,artistName,genreName,songDuration,songUrl);
                playlistSongList.add(song);
            }
            rs.close();
            ps.close();
        return playlistSongList ;
    }

    //get list of all podcasts in a playlist
    public static List<Podcast> getPodcastListFromPlaylist(User user,String playlist_name) throws SQLException{                                                                                       //to play a playlist
        List<Podcast> playlistPodcastList=new ArrayList<>();
        PreparedStatement ps=con.prepareStatement("select * from podcastTable where podcastId in(select podcastId from audioTable where audioId in(select audioId from playlistTable where userId=? and playlistName=?))");
        ps.setInt(1,user.getUserId());
        ps.setString(2,playlist_name);
        ResultSet rs=ps.executeQuery();
        while (rs.next()){
            int podcastId=rs.getInt(1);
            String podcastName=rs.getString(2);
            String celebrityName=rs.getString(3);
            Date publishDate=rs.getDate(4);
            String podcastUrl=rs.getString(5);
            Podcast podcast=new Podcast(podcastId,podcastName,celebrityName,publishDate,podcastUrl);
            playlistPodcastList.add(podcast);
        }
        rs.close();
        ps.close();
        return playlistPodcastList ;
    }

    //delete playlist from playlistTable
    public static void deletePlaylistOfaUser(User user, String playlist_name) throws SQLException{
        PreparedStatement ps=con.prepareStatement("delete from playlistTable where userId = ? and playlistName = ?");
        ps.setInt(1,user.getUserId());
        ps.setString(2,playlist_name);
        ps.executeUpdate();
    }

}
