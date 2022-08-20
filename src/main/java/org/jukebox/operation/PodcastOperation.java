package org.jukebox.operation;

import org.jukebox.model.Podcast;
import org.jukebox.model.Song;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PodcastOperation {


    //Sorts arraylist of podcast by podcastName.
    public static List<Podcast> sortPodcastListByPodcastName(List<Podcast> podcastList){
        return podcastList.stream().sorted(Comparator.comparing(s -> s.getPodcastName().toUpperCase())).collect(Collectors.toList());
    }

    //prints the songs present in list in tabular format
    public static void displayPodcastList(List<Podcast> podcastList){
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%s %16s %28s %30s", "PODCAST ID", "PODCAST", "CELEBRITY ","PUBLISHED ON");
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        for(Podcast p: podcastList){
            System.out.printf("%s %32s %25s %25s",p.getPodcastId(),p.getPodcastName(),p.getCelebrityName(),new SimpleDateFormat("dd-MM-yyyy").format(p.getPublishDate()));
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------------------------------------------------------");
    }

    //Returns list of podcast, given podcast name.
    public static List<Podcast> getSongListGivenPodcastName(String podcast_name, List<Podcast> podcastList) {
        List<Podcast> newPodcastList;
        newPodcastList = podcastList.stream().filter(p -> p.getPodcastName().toLowerCase().contains(podcast_name.toLowerCase())).collect(Collectors.toList());
        return newPodcastList;
    }

    //Returns list of Podcasts of a celebrity.
    public static List<Podcast> getPodcastListGivenCelebrityName(String celebrity_name, List<Podcast> podcastList) {
        List<Podcast> newPodcastList;
        newPodcastList = podcastList.stream().filter(p -> p.getCelebrityName().toLowerCase().contains(celebrity_name.toLowerCase())).collect(Collectors.toList());
        return newPodcastList;
    }

    //Returns list of all Podcasts published on a date.
    public static List<Podcast> getPodcastListGivenPublishDate(String publish_date, List<Podcast> podcastList) throws ParseException {
        List<Podcast> newPodcastList;
        DateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date uPublish_date=sdf.parse(publish_date);                                                                   //string to util.Date
        long l=uPublish_date.getTime();
        java.sql.Date sPublish_date=new Date(l);                                                                                //util.Date to sql.Date
        newPodcastList = podcastList.stream().filter(p -> p.getPublishDate().equals(sPublish_date)).collect(Collectors.toList());
        return newPodcastList;
    }

    //returns a list containing only one Podcast related to specific podcastId.
    public static List<Podcast> getPodcastListContainingSinglePodcastGivenPodcastId(List<Podcast> podcastList, int podcastId){
        return podcastList.stream().filter(p->p.getPodcastId()==podcastId).collect(Collectors.toList());
    }

}

