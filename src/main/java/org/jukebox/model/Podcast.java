package org.jukebox.model;

import java.util.Date;
import java.util.Objects;

public class Podcast {
    private int podcastId;
    private String podcastName;
    private String celebrityName;
    private Date publishDate;
    private String podcastUrl;

    //constructor
    public Podcast(int podcastId, String podcastName, String celebrityName, Date publishDate, String podcastUrl) {
        this.podcastId = podcastId;
        this.podcastName = podcastName;
        this.celebrityName = celebrityName;
        this.publishDate = publishDate;
        this.podcastUrl = podcastUrl;
    }

    //getters
    public int getPodcastId() {
        return podcastId;
    }

    public String getPodcastName() {
        return podcastName;
    }

    public String getCelebrityName() {
        return celebrityName;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getPodcastUrl() {
        return podcastUrl;
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "podcastId=" + podcastId +
                ", podcastName='" + podcastName + '\'' +
                ", celebrityName='" + celebrityName + '\'' +
                ", publishDate=" + publishDate +
                ", podcastUrl='" + podcastUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podcast podcast = (Podcast) o;
        return podcastId == podcast.podcastId && Objects.equals(podcastName, podcast.podcastName) && Objects.equals(celebrityName, podcast.celebrityName) && Objects.equals(publishDate, podcast.publishDate) && Objects.equals(podcastUrl, podcast.podcastUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(podcastId, podcastName, celebrityName, publishDate, podcastUrl);
    }
}
