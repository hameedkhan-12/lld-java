package MusicPlayerApplication.strategies;

import MusicPlayerApplication.models.Playlist;
import MusicPlayerApplication.models.Song;

public interface PlayStrategy {
    void setPlaylist(Playlist playlist);
    Song nextSong();
    boolean hasNext();
    Song previous();
    Song hasPrevious();
    default void addToNext(Song song){};
}