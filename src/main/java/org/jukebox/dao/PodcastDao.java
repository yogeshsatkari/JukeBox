package org.jukebox.dao;

import org.jukebox.model.Podcast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.jukebox.connection_db.Connector_db.con;

public class PodcastDao {

    //Returns list of all Podcasts from podcastTable
    public static List<Podcast> getPodcastList() throws SQLException {
        List<Podcast> podcastList=new ArrayList<>();
        PreparedStatement ps=con.prepareStatement("select * from podcastTable");
        ResultSet rs= ps.executeQuery();
        while (rs.next()){
            int podcastId=rs.getInt(1);
            String podcastName=rs.getString(2);
            String celebrityName=rs.getString(3);
            Date publishDate=rs.getDate(4);
            String podcastUrl=rs.getString(5);
            Podcast podcast=new Podcast(podcastId,podcastName,celebrityName,publishDate,podcastUrl);
            podcastList.add(podcast);
        }
        rs.close();
        ps.close();
        return podcastList;
    }
}
