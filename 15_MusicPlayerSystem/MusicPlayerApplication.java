package MusicPlayerApplication;
import java.util.List;
import java.util.ArrayList;
import MusicPlayerApplication.models.Song;
public class MusicPlayerApplication {
  private static MusicPlayerApplication instance = null;
  private List<Song> songLibrary;

  private MusicPlayerApplication(){
    songLibrary = new ArrayList<>();
  }

  public static synchronized MusicPlayerApplication getInstance(){
    if(instance == null){
        instance = new MusicPlayerApplication();
    }
    return instance;
  }

  public void createSongInLibrary(String title, String artist, String path){
    Song newSong = new Song(title, artist, path);
    songLibrary.add(newSong);
  }

  public Song findSongByTitle(String title){
    for(Song s: songLibrary){
        if(s.getTitle().equals(title)){
            return s;
        }
    }
    return null;
  }

  public void createPlaylist(String playlistName){
    PlaylistManager.getInstance().createPlaylist(playlistName);
  }
}