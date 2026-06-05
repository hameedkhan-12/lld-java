package MusicPlayerApplication.models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String playlistName;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName(){
        return playlistName;
    }

    public List<Song> getSongs(){
        return songs;
    }

    public int getSize(){
        return songs.size();
    }

    public void addSongToPlaylist(Song song){
        if(song == null){
            throw new RuntimeException("Song cannot be null");
        }
        songs.add(song);
    }
}