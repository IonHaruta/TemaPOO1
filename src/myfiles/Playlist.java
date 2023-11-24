package myfiles;

import java.util.ArrayList;

public final class Playlist {
    public static final int JUST_FIVE_WTF = 5;
    private String name;
    private String owner;
    /**
     * Filters a list of playlists by name,
     * keeping only those that start with the specified prefix.
     *
     * This method takes in a name prefix,
     * a list of playlists, and a temporary list for filtering.
     * It iterates through the input list of playlists,
     * adding playlists whose names start with the given prefix
     * to the temporary list.
     *
     * @param name The prefix to filter playlists by.
     * @param playlistsEX The input list of playlists to be filtered.
     * @param playlistsTMP The temporary list used for filtering.
     */
    public static void filterNamePlaylist(final String name,
                                          final ArrayList<Playlist> playlistsEX,
                                          final ArrayList<Playlist> playlistsTMP) {
        if (name != null && !name.isEmpty()) {
            for (Playlist playlist : playlistsEX) {
                if (playlist.getName().startsWith(name)) {
                    playlistsTMP.add(playlist);
                }
            }
        }
    }
    /**
     * Filters a list of playlists by owner,
     * keeping only those that contain the specified owner.
     *
     * This method takes in an owner, a list of playlists,
     * and a temporary list for filtering.
     * It iterates through the input list of playlists,
     * adding playlists that contain the specified owner
     * to the temporary list.
     *
     * @param owner The owner to filter playlists by.
     * @param playlistsEX The input list of playlists to be filtered.
     * @param playlistsTMP The temporary list used for filtering.
     */
    public static void filterOwnerPlaylist(final String owner,
                                           final ArrayList<Playlist> playlistsEX,
                                           final ArrayList<Playlist> playlistsTMP) {
        if (owner != null && !owner.isEmpty()) {
            for (Playlist playlist : playlistsEX) {
                if (playlist.getOwner().contains(owner)) {
                    playlistsTMP.add(playlist);
                }
            }
        }
    }
    /**
     * Extracts playlist names from a list of playlists and populates a temporary list.
     *
     * This method iterates through the input list
     * of playlists and adds playlist names to the temporary list.
     * The count parameter determines the maximum number of playlists to extract.
     * The method stops adding playlists
     * to the temporary list once the count is reached or exceeded.
     *
     * @param playlistsEX The list of playlists to extract names from.
     * @param playlistFINAL The temporary list for storing playlist names.
     * @param count The maximum number of playlists to extract.
     */
    public static void sortPlaylist(final ArrayList<Playlist> playlistsEX,
                                    final ArrayList<String> playlistFINAL,
                                    int count) {
        for (Playlist playlist : playlistsEX) {
            playlistFINAL.add(playlist.getName());
            count++;
            //NU POATE FI FINAL COUNT
            if (count >= JUST_FIVE_WTF) {
                break;
            }
        }
    }
    public Playlist() {
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(final String owner) {
        this.owner = owner;
    }
}
