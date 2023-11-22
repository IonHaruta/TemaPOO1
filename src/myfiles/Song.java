package myfiles;

import java.util.ArrayList;

public final class Song {
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

    public static void setDurationCurrentSong(Integer durationCurrentSong) {
        Song.durationCurrentSong = durationCurrentSong;
    }

    public static ArrayList<Song> filterName(String name, ArrayList<Song> songsEX, ArrayList<Song> songTMP){
        if (name != null && !name.isEmpty()){
            for (Song song : songsEX){
                if (song.getName().startsWith(name)){
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
    public static void filterAlbum(String album, ArrayList<Song> songsEX, ArrayList<Song> songTMP){
        if (album != null && !album.isEmpty()){
            for (Song song : songsEX) {
                if (song.getAlbum().startsWith(album)){
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    public static void filterTags(ArrayList<String> tags, ArrayList<Song> songsEX, ArrayList<Song> songTMP){
        if (tags != null && !tags.isEmpty()){
            ArrayList<Song> filteredByTags = new ArrayList<>();
            for (Song song : songsEX) {
                if (song.getTags().containsAll(tags)){
                    filteredByTags.add(song);
                }
            }
            songsEX.retainAll(filteredByTags);
        }
    }
    public static void filterLyrics(String lyrics, ArrayList<Song> songsEX, ArrayList<Song> songTMP){
        if (lyrics != null && !lyrics.isEmpty()){
            for (Song song : songsEX) {
                if (song.getLyrics().contains(lyrics)){
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    public static void filterGenre(String genre, ArrayList<Song> songsEX, ArrayList<Song> songTMP){
        if (genre != null && !genre.isEmpty()){
            for (Song song : songsEX) {
                if (song.getGenre().toLowerCase().equals(genre.toLowerCase())){
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    public static void filterRealeaseYear(String releaseYear, ArrayList<Song> songsEX, ArrayList<Song> songTMP){
        if (releaseYear != null && !releaseYear.isEmpty()){
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
    public static void filterArtist(String artist, ArrayList<Song> songsEX, ArrayList<Song> songTMP){
        if (artist != null && !artist.isEmpty()){
            for (Song song : songsEX) {
                if (song.getArtist().equals(artist)){
                    songTMP.add(song);
                }
            }
            songsEX.clear();
            songsEX.addAll(songTMP);
            songTMP.clear();
        }
    }
    public static void sortSongs(ArrayList<Song> songsEX, ArrayList<String> melodiiTMP, int count){
        for (Song melodii : songsEX) {
            melodiiTMP.add(melodii.getName());
            count++;

            if (count >= 5) {
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
