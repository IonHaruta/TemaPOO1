package myfiles;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private String owner;

    public static void filterNamePlaylist(String name, ArrayList<Playlist> playlistsEX, ArrayList<Playlist> playlistsTMP){
        if (name != null && !name.isEmpty()){
            for (Playlist playlist : playlistsEX) {
                if (playlist.getName().startsWith(name)){
                    playlistsTMP.add(playlist);
                }
            }
        }
    }
    public static void filterOwnerPlaylist(String owner, ArrayList<Playlist> playlistsEX, ArrayList<Playlist> playlistsTMP){
        if (owner != null && !owner.isEmpty()){
            for (Playlist playlist : playlistsEX) {
                if (playlist.getOwner().contains(owner)){
                    playlistsTMP.add(playlist);
                }
            }
        }
    }
    public static void sortPlaylist(ArrayList<Playlist> playlistsEX, ArrayList<String> playlistFINAL, int count){
        for (Playlist playlist : playlistsEX) {
            playlistFINAL.add(playlist.getName());
            count++;

            if (count >= 5) {
                break;
            }
        }
    }
    public Playlist() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
