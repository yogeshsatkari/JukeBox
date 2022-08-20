package org.jukebox.musicplayer;

import org.jukebox.model.Podcast;
import org.jukebox.model.Song;

import  javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MusicPlayer {
    //Plays a song_List
    public static void playSong(List<Song> song_List) {
        try {
            Scanner scan = new Scanner(System.in);
            forLoop:
            for (int i = 0; i < song_List.size(); i++) {
                System.out.println("playing...'" + song_List.get(i).getSongName() + "' by '" + song_List.get(i).getArtistName() + "'");
                System.out.println("duration : "+song_List.get(i).getSongDuration());
                File file = new File(song_List.get(i).getSongUrl());
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                String response;
                whileLoop:
                while (true) {
                    System.out.print("[ r:resume | p:pause | pre:previous | nex:next | l:loop | e:exit ] CHOOSE : ");
                    response = scan.nextLine();
                    response = response.toUpperCase();
                    switch (response) {
                        case "R":
                            clip.start();
                            break;
                        case "P":
                            clip.stop();
                            break;
                        case "PRE":
                            if (i > 0) {
                                clip.close();
                                i = i - 2;
                                break whileLoop;
                            } else System.out.println("we are at the first song...");
                            break;
                        case "NEX":
                            clip.close();
                            break whileLoop;
                        case "L":
                            clip.close();
                            i=i-1;
                            break whileLoop;
                        case "E":
                            clip.close();
                            break forLoop;
                        default:
                            System.out.println("Invalid response...");
                    }
                }
                if (i == song_List.size() - 1) System.out.println("playlist has ended...");
            }
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }


    //Plays a podcast_list
    public static void playPodcast(List<Podcast> podcast_List) {
        try {
            Scanner scan = new Scanner(System.in);
            forLoop:
            for (int i = 0; i < podcast_List.size(); i++) {
                System.out.println("playing...'" + podcast_List.get(i).getPodcastName() + "' by '" + podcast_List.get(i).getCelebrityName() + "'");
                File file = new File(podcast_List.get(i).getPodcastUrl());
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
                String response;
                whileLoop:
                while (true) {
                    System.out.print("[ r:resume | p:pause | pre:previous | nex:next | l:loop | e:exit ] CHOOSE : ");
                    response = scan.nextLine();
                    response = response.toUpperCase();
                    switch (response) {
                        case "R":
                            clip.start();
                            break;
                        case "P":
                            clip.stop();
                            break;
                        case "PRE":
                            if (i > 0) {
                                clip.close();
                                i = i - 2;
                                break whileLoop;
                            } else System.out.println("we are at the first podcast...");
                            break;
                        case "NEX":
                            clip.close();
                            break whileLoop;
                        case "L":
                            clip.close();
                            i=i-1;
                            break whileLoop;
                        case "E":
                            clip.close();
                            break forLoop;
                        default:
                            System.out.println("Invalid response...");
                    }
                }
                if (i == podcast_List.size() - 1) System.out.println("playlist has ended...");
            }
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }
}
