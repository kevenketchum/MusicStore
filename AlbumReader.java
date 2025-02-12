package Music;

public class AlbumReader {
    public static void main(String[] args) {
        MusicStore store = new MusicStore();

        String searchTitle = "Old Ideas";
        Album result = store.searchAlbum(searchTitle);
        if (result != null) {
            System.out.println("Found Album: " + result);
        } else {
            System.out.println("\nAlbum '" + searchTitle + "' not found.");
        }
    }
}
