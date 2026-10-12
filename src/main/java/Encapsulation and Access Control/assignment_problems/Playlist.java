import java.util.Arrays;

public class Playlist {
    private final String[] songs;
    private int songCount;

    public Playlist(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(
                    "Capacity cannot be negative.");
        }

        songs = new String[capacity];
        songCount = 0;
    }

    public void addSong(String song) {
        if (song == null || song.isEmpty()) {
            throw new IllegalArgumentException(
                    "Song title cannot be empty.");
        }

        if (songCount == songs.length) {
            System.out.println("Playlist is full.");
            return;
        }

        songs[songCount] = song;
        songCount++;
    }

    public String[] getSongs() {
        return Arrays.copyOf(songs, songCount);
    }

    public int getSongCount() {
        return songCount;
    }

    public static void main(String[] args) {
        Playlist p = new Playlist(10);

        p.addSong("Song A");
        p.addSong("Song B");

        String[] copy = p.getSongs();
        copy[0] = "Hacked";

        System.out.println("First song: " + p.getSongs()[0]);
        System.out.println("Second song: " + p.getSongs()[1]);
        System.out.println("Song count: " + p.getSongCount());
    }
}