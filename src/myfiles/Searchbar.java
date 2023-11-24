package myfiles;

import java.util.ArrayList;

public final class Searchbar {
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private ArrayList<Podcast> podcasts = new ArrayList<>();

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(final ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(final ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
