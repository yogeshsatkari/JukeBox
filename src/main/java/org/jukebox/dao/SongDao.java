package org.jukebox.dao;

import org.jukebox.model.Song;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.jukebox.connection_db.Connector_db.con;

public class SongDao {

    //Returns list of all songs from songTable
    public static List<Song> getSongList() throws SQLException {
        List<Song> songList=new ArrayList<>();
        PreparedStatement ps=con.prepareStatement("select * from songTable");
        ResultSet rs= ps.executeQuery();
        while (rs.next()){
             int songId=rs.getInt(1);
             String songName=rs.getString(2);
             String albumName=rs.getString(3);
             String artistName=rs.getString(4);
             String genreName=rs.getString(5);
             Time songDuration=rs.getTime(6);
             String songUrl=rs.getString(7);
             Song song=new Song(songId,songName,albumName,artistName,genreName,songDuration,songUrl);
             songList.add(song);
        }
        rs.close();
        ps.close();
        return songList;
    }

}
