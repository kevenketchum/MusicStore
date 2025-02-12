package Music;
import java.io.*;
import java.util.*;

public class MusicStore {
    private HashMap<String, Album> musicLibrary; // Stores all albums

    public MusicStore() {
        musicLibrary = new HashMap<>();
        loadMusicStore();
    }

    // Loads album names from albums.txt and reads individual album files
    private void loadMusicStore() {
        String albumsFile = "albums.txt"; // Ensure this file is in the correct directory

        try (BufferedReader reader = new BufferedReader(new FileReader(albumsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String albumTitle = parts[0].trim();
                    String artist = parts[1].trim();
                    String fileName = albumTitle + "_" + artist + ".txt"; // Construct album file name

                    loadAlbum(fileName, albumTitle, artist); // Read album details
                }
            }
        } catch (IOException e) {
            System.out.println("ERROR: Could not read albums.txt - " + e.getMessage());
        }
    }

    // Reads individual album files and stores them in the HashMap
    private void loadAlbum(String fileName, String albumTitle, String artist) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstLine = reader.readLine();
            if (firstLine == null) return;

            String[] meta = firstLine.split(",");
            if (meta.length < 4) return;

            String genre = meta[2].trim();
            int year = Integer.parseInt(meta[3].trim());

            Album album = new Album(albumTitle, artist, genre, year);

            String song;
            while ((song = reader.readLine()) != null) {
                album.addSong(song.trim());
            }

            musicLibrary.put(albumTitle, album);
        } catch (IOException e) {
            System.out.println("ERROR: Could not read file " + fileName + " - " + e.getMessage());
        }
    }

    // Searches for an album by title
    public Album searchAlbum(String albumTitle) {
        return musicLibrary.get(albumTitle);
    }

    // Prints all stored albums
    public void printAllAlbums() {
        if (musicLibrary.isEmpty()) {
            System.out.println("No albums found in the music store.");
            return;
        }

        for (String albumTitle : musicLibrary.keySet()) {
            System.out.println(musicLibrary.get(albumTitle));
        }
    }
}
