import java.util.*;

interface Iterator<T> {
    boolean hasNext();
    T next();
}

interface Iterable<T> {
    Iterator<T> getIterator();
}

class LinkedList implements Iterable<Integer> {
    public int data;
    public LinkedList next;

    public LinkedList(int value){`
        data = value;
        next = null;
    }

    public Iterator<Integer> getIterator () {
        return new LinkedListIterator(this);
    }
}

class BinaryTree implements Iterable<Integer> {
    public int data;
    public BinaryTree left;
    public BinaryTree right;

    public BinaryTree(int value){
        data = value;
        left = null;
        right = null;
    }

    public Iterator<Integer> getIterator () {
        return new BinaryTreeIterator(this);
    }
}

class Song {
    public String title;
    public String artist;

    public Song(String title, String artist){
        this.title = title;
        this.artist = artist;
    }
}

class Playlist implements Iterable<Song>{
    public List<Song> songs = new ArrayList<Song>();

    public void addSong(Song s){
        songs.add(s);
    }

    public Iterator<Song> getIterator(){
        return new PlaylistIterator(songs);
    }
}

class LinkedListIterator implements Iterator<Integer> {
    private LinkedList current;

    public LinkedListIterator(LinkedList head){
        current = head;
    }

    public boolean hasNext(){
        return current != null;
    }

    public Integer next(){
        int result = current.data;
        current = current.next;
        return result;
    }
}

class BinaryTreeIterator implements Iterator<Integer> {
    private Stack<BinaryTree> stack = new Stack<BinaryTree>();

    public BinaryTreeIterator(BinaryTree root){
        stack.push(root);
    }

    public boolean hasNext(){
        return !stack.isEmpty();
    }

    public Integer next(){
        BinaryTree current = stack.pop();
        if (current.right != null) stack.push(current.right);
        if (current.left != null) stack.push(current.left);
        return current.data;
    }
}

class PlaylistIterator implements Iterator<Song> {
    private int index = 0;
    private List<Song> songs;

    public PlaylistIterator(List<Song> songs){
        this.songs = songs;
    }

    public boolean hasNext(){
        return index < songs.size();
    }

    public Song next(){
        return songs.get(index++);
    }
}

public class IteratorPattern {
    public static void main(String[] args){
        LinkedList list = new LinkedList(1);
        list.next = new LinkedList(2);
        list.next.next = new LinkedList(3);

        Iterator<Integer> it = list.getIterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

        BinaryTree tree = new BinaryTree(1);
        tree.left = new BinaryTree(2);
        tree.right = new BinaryTree(3);

        Iterator<Integer> it2 = tree.getIterator();
        while(it2.hasNext()){
            System.out.println(it2.next());
        }

        Playlist playlist = new Playlist();
        playlist.addSong(new Song("Song 1", "Artist 1"));
        playlist.addSong(new Song("Song 2", "Artist 2"));
        playlist.addSong(new Song("Song 3", "Artist 3"));

        Iterator<Song> it3 = playlist.getIterator();
        while(it3.hasNext()){
            System.out.println(it3.next().title);
        }
    }
}