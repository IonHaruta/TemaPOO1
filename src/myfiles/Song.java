package myfiles;

import java.util.ArrayList;

public final class Song {
    public static final int JUST_FIVE_WTF = 5;
    private String name;
    private Integer duration;
    private String album;
    private ArrayList<String> tags;
    private String lyrics;
    private String genre;
    private Integer releaseYear;
    private String artist;
    private static Integer durationCurrentSong;

    public static Integer getDurationCurrentSong() {
        return durationCurrentSong;
    }

    public static void setDurationCurrentSong(final Integer durationCurrentSong) {
        Song.durationCurrentSong = durationCurrentSong;
    }

    /**
     * Filters a list of songs by name, keeping only those that start with the specified prefix.
     *
     * This method takes in a name prefix, a list of songs, and a temporary list for filtering.
     * It iterates through the input list of songs,
     * adding songs whose names start with the given prefix
     * to the temporary list. It then clears the input list,
     * adds the filtered songs back, and returns
     * the modified list.
     *
     * @param name The prefix to filter songs by.
     * @param songsEX The input list of songs to be filtered.
     * @param songTMP The temporary list used for filtering.
     * @return The filtered list of songs or null if the input name is null or empty.
     */
    public static ArrayList<Song> filterName(final String name,
                                             final ArrayList<Song> songsEX,
                                             final ArrayList<Song> songTMP) {
        if (name != null && !name.isEmpty()) {
            for (Song song : songsEX) {
                if (song.getName().startsWith(name)) {
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
            return songsEX;
        }
        return null;
    }
    /**
     * Filters a list of songs by album,
     * keeping only those that start with the specified prefix.
     *
     * This method takes in an album prefix,
     * a list of songs, and a temporary list for filtering.
     * It iterates through the input list of songs,
     * adding songs whose albums start with the given prefix
     * to the temporary list. It then clears the input list,
     * adds the filtered songs back, and updates
     * the list in-place.
     *
     * @param album The prefix to filter songs by album.
     * @param songsEX The input list of songs to be filtered.
     * @param songTMP The temporary list used for filtering.
     */
    public static void filterAlbum(final String album,
                                   final ArrayList<Song> songsEX,
                                   final ArrayList<Song> songTMP) {
        if (album != null && !album.isEmpty()) {
            for (Song song : songsEX) {
                if (song.getAlbum().startsWith(album)) {
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    /**
     * Filters a list of songs by tags,
     * keeping only those that contain all the specified tags.
     *
     * This method takes in a list of tags,
     * a list of songs, and a temporary list for filtering.
     * It iterates through the input list of songs,
     * adding songs whose tags contain all the specified tags
     * to the temporary list.
     * It then updates the input list in-place with the filtered songs.
     *
     * @param tags The list of tags to filter songs by.
     * @param songsEX The input list of songs to be filtered.
     * @param songTMP The temporary list used for filtering.
     */
    public static void filterTags(final ArrayList<String> tags,
                                  final ArrayList<Song> songsEX,
                                  final ArrayList<Song> songTMP) {
        if (tags != null && !tags.isEmpty()) {
            ArrayList<Song> filteredByTags = new ArrayList<>();
            for (Song song : songsEX) {
                if (song.getTags().containsAll(tags)) {
                    filteredByTags.add(song);
                }
            }
            songsEX.retainAll(filteredByTags);
        }
    }
    /**
     * Filters a list of songs by lyrics,
     * keeping only those that contain the specified lyrics.
     *
     * This method takes in lyrics,
     * a list of songs, and a temporary list for filtering.
     * It iterates through the input list of songs,
     * adding songs whose lyrics contain the specified lyrics
     * to the temporary list.
     * It then updates the input list in-place with the filtered songs.
     *
     * @param lyrics The lyrics to filter songs by.
     * @param songsEX The input list of songs to be filtered.
     * @param songTMP The temporary list used for filtering.
     */
    public static void filterLyrics(final String lyrics,
                                    final ArrayList<Song> songsEX,
                                    final ArrayList<Song> songTMP) {
        if (lyrics != null && !lyrics.isEmpty()) {
            for (Song song : songsEX) {
                if (song.getLyrics().contains(lyrics)) {
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    /**
     * Filters a list of songs by genre,
     * keeping only those that match the specified genre.
     *
     * This method takes in a genre,
     * a list of songs, and a temporary list for filtering.
     * It iterates through the input list of songs,
     * adding songs whose genre matches the specified genre
     * (case-insensitive) to the temporary list.
     * It then updates the input list in-place with the filtered songs.
     *
     * @param genre The genre to filter songs by.
     * @param songsEX The input list of songs to be filtered.
     * @param songTMP The temporary list used for filtering.
     */
    public static void filterGenre(final String genre,
                                   final ArrayList<Song> songsEX,
                                   final ArrayList<Song> songTMP) {
        if (genre != null && !genre.isEmpty()) {
            for (Song song : songsEX) {
                if (song.getGenre().toLowerCase().equals(genre.toLowerCase())) {
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    /**
     * Filters a list of songs by release year,
     * keeping only those that satisfy the specified condition.
     *
     * This method takes in a release year condition,
     * a list of songs, and a temporary list for filtering.
     * It iterates through the input list of songs,
     * adding songs that meet the specified condition to the
     * temporary list.
     * The condition is determined by the operator
     * at the beginning of the release year
     * string ("<" for less than, ">" for greater than).
     * It then updates the input list in-place with
     * the filtered songs.
     *
     * @param releaseYear The release year condition (e.g., "<2020", ">1990").
     * @param songsEX The input list of songs to be filtered.
     * @param songTMP The temporary list used for filtering.
     */
    public static void filterRealeaseYear(final String releaseYear,
                                          final ArrayList<Song> songsEX,
                                          final ArrayList<Song> songTMP) {
        if (releaseYear != null && !releaseYear.isEmpty()) {
            for (Song song : songsEX) {

                String operator = releaseYear.substring(0, 1);
                int conditionYear = Integer.parseInt(releaseYear.substring(1));
                if (operator.equals("<")) {
                    if (song.getReleaseYear() < conditionYear) {
                        songTMP.add(song);
                    }
                } else if (operator.equals(">")) {
                    if (song.getReleaseYear() > conditionYear) {
                        songTMP.add(song);
                    }
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    /**
     * Filters a list of songs by artist,
     * keeping only those that match the specified artist.
     *
     * This method takes in an artist,
     * a list of songs, and a temporary list for filtering.
     * It iterates through the input list of songs,
     * adding songs whose artist matches the specified artist
     * to the temporary list.
     * It then updates the input list in-place with the filtered songs.
     *
     * @param artist The artist to filter songs by.
     * @param songsEX The input list of songs to be filtered.
     * @param songTMP The temporary list used for filtering.
     */
    public static void filterArtist(final String artist,
                                    final ArrayList<Song> songsEX,
                                    final ArrayList<Song> songTMP) {
        if (artist != null && !artist.isEmpty()) {
            for (Song song : songsEX) {
                if (song.getArtist().equals(artist)) {
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    /**
     * Extracts song names from a list of songs and populates a temporary list.
     *
     * This method takes in a list of songs,
     * a temporary list for storing song names, and a count
     * to limit the number of songs to extract.
     * It iterates through the input list of songs,
     * adding song names to the temporary list.
     * The count parameter determines the maximum number
     * of songs to extract.
     * The method stops adding songs to the temporary list once the count is
     * reached or exceeded.
     *
     * @param songsEX The list of songs to extract names from.
     * @param melodiiTMP The temporary list for storing song names.
     * @param count The maximum number of songs to extract.
     */
    public static void sortSongs(final ArrayList<Song> songsEX,
                                 final ArrayList<String> melodiiTMP,
                                 int count) {
        for (Song melodii : songsEX) {
            melodiiTMP.add(melodii.getName());
            count++;
            //NU POATE FI FINAL COUNT
            if (count >= JUST_FIVE_WTF) {
                break;
            }
        }
    }

    public Song() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(final Integer duration) {
        this.duration = duration;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(final String album) {
        this.album = album;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(final ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(final String lyrics) {
        this.lyrics = lyrics;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(final int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(final String artist) {
        this.artist = artist;
    }
}
