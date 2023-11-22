package myfiles;

import java.util.ArrayList;

public final class Library {
    private ArrayList<Song> songs = new ArrayList<>();
    private ArrayList<Podcast> podcasts = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Command> commands = new ArrayList<>();
    private ArrayList<Playlist> playlist = new ArrayList<>();
    private ArrayList<String> lastSearchResults = new ArrayList<>();
    private Integer itemNumber;
    private Boolean playPause = true;
    private Integer repeat = 0;
    private Integer shuffle = 0;
    private ArrayList<String> currentSongs;

    public ArrayList<String> getCurrentSongs() {
        return currentSongs;
    }

    public void setCurrentSongs(ArrayList<String> currentSongs) {
        this.currentSongs = currentSongs;
    }

    public Integer getShuffle() {
        return shuffle;
    }

    public void setShuffle(Integer shuffle) {
        this.shuffle = shuffle;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public Boolean getPlayPause() {
        return playPause;
    }

    public void setPlayPause(Boolean playPause) {
        this.playPause = playPause;
    }

    public ArrayList<String> getLastSearchResults() {
        return lastSearchResults;
    }

    public void setLastSearchResults(ArrayList<String> lastSearchResults) {
        this.lastSearchResults = lastSearchResults;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public ArrayList<Playlist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(ArrayList<Playlist> playlist) {
        this.playlist = playlist;
    }

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }


    public Library() {
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(final ArrayList<Song> songs) {
        this.songs = songs;
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(final ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(final ArrayList<User> users) {
        this.users = users;
    }
}
