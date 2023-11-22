package myfiles;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
//@JsonIgnoreProperties(ignoreUnknown = true)
public final class SearchbarDTO {
    private String command;
    private String username;
    private Integer timestamp;
    private FilterDTO filters;
    private Integer itemNumber;
    private String type;
    private Status stats;
    private static String currentType;
    private Integer seed;
    private String playlistName;
    private Integer playlistId;
    private static boolean isLoaded = false;
    private static Integer currentSeed;
    private static Integer currentTimestamp;
    private static String currentSong;

    public static String getCurrentSong() {
        return currentSong;
    }

    public static void setCurrentSong(final String currentSong) {
        SearchbarDTO.currentSong = currentSong;
    }

    public static Integer getCurrentTimestamp() {
        return currentTimestamp;
    }

    public static void setCurrentTimestamp(final Integer currentTimestamp) {
        SearchbarDTO.currentTimestamp = currentTimestamp;
    }

    public final Status getStats() {
        return stats;
    }

    public final void setStats(final Status stats) {
        this.stats = stats;
    }

    public static Integer getCurrentSeed() {
        return currentSeed;
    }

    public static void setCurrentSeed(final Integer currentSeed) {
        SearchbarDTO.currentSeed = currentSeed;
    }

    public static boolean isIsLoaded() {
        return isLoaded;
    }

    public static void setIsLoaded(final boolean isLoaded) {
        SearchbarDTO.isLoaded = isLoaded;
    }

    public static String getCurrentType() {
        return currentType;
    }

    public static void setCurrentType(final String currentType) {
        SearchbarDTO.currentType = currentType;
    }

    public Integer getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(final Integer playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public static void printCUTMR(ObjectNode searchResultNode, SearchbarDTO search, ArrayNode resultsArrayNode, ArrayList<String> melodiiTMP, String message){
        searchResultNode.put("command", search.getCommand());
        searchResultNode.put("user", search.getUsername());
        searchResultNode.put("timestamp", search.getTimestamp());
        searchResultNode.put("message", message);
        searchResultNode.put("results", resultsArrayNode);
    }
    public static void printSelect(ObjectNode searchResultNode, Library library, SearchbarDTO search, int itemNumber){
        if (library.getLastSearchResults().isEmpty()) {
            searchResultNode.put("command", search.getCommand());
            searchResultNode.put("user", search.getUsername());
            searchResultNode.put("timestamp", search.getTimestamp());
            searchResultNode.put("message", "Please conduct a search before making a selection.");
        } else if (itemNumber < 1 || itemNumber > library.getLastSearchResults().size()) {
            searchResultNode.put("command", search.getCommand());
            searchResultNode.put("user", search.getUsername());
            searchResultNode.put("timestamp", search.getTimestamp());
            searchResultNode.put("message", "The selected ID is too high.");
        } else {
            String selectedTrack = library.getLastSearchResults().get(itemNumber - 1);
            SearchbarDTO.setCurrentSong(selectedTrack);
            searchResultNode.put("command", search.getCommand());
            searchResultNode.put("user", search.getUsername());
            searchResultNode.put("timestamp", search.getTimestamp());
            searchResultNode.put("message", "Successfully selected " + selectedTrack + ".");
        }
    }
    public static void printLoad(ObjectNode loadResultNode, Library library, SearchbarDTO search){
        if (SearchbarDTO.getCurrentType().equals("playlist") || SearchbarDTO.getCurrentType().equals("song") || SearchbarDTO.getCurrentType().equals("podcast")) {
            if (library.getLastSearchResults().isEmpty()) {
                loadResultNode.put("command", search.getCommand());
                loadResultNode.put("user", search.getUsername());
                loadResultNode.put("timestamp", search.getTimestamp());
                loadResultNode.put("message", "Please select a source before attempting to load.");
            } else if (library.getLastSearchResults().size() == 0) {
                loadResultNode.put("command", search.getCommand());
                loadResultNode.put("user", search.getUsername());
                loadResultNode.put("timestamp", search.getTimestamp());
                loadResultNode.put("message", "You can't load an empty audio collection!");
            } else {
                SearchbarDTO.setIsLoaded(true);
                SearchbarDTO.setCurrentTimestamp(search.getTimestamp());
                String selectedSource = library.getLastSearchResults().get(0);
                loadResultNode.put("command", search.getCommand());
                loadResultNode.put("user", search.getUsername());
                loadResultNode.put("timestamp", search.getTimestamp());
                loadResultNode.put("message", "Playback loaded successfully.");
            }
        } else {
            loadResultNode.put("command", search.getCommand());
            loadResultNode.put("user", search.getUsername());
            loadResultNode.put("timestamp", search.getTimestamp());
            loadResultNode.put("message", "Please select a source before attempting to load.");
        }
    }
    public static void printPlayPause(ObjectNode playPauseResultNode, Library library, SearchbarDTO search){
        if (library.getLastSearchResults().isEmpty()) {
            playPauseResultNode.put("command", search.getCommand());
            playPauseResultNode.put("user", search.getUsername());
            playPauseResultNode.put("timestamp", search.getTimestamp());
            playPauseResultNode.put("message", "Please load a source before attempting to pause or resume playback.");
        } else {
            library.setPlayPause(!library.getPlayPause());

            playPauseResultNode.put("command", search.getCommand());
            playPauseResultNode.put("user", search.getUsername());
            playPauseResultNode.put("timestamp", search.getTimestamp());

            if (library.getPlayPause()) {
                playPauseResultNode.put("message", "Playback resumed successfully.");
            } else {
                playPauseResultNode.put("message", "Playback paused successfully.");
            }
        }
    }
    public static void printRepeat(ObjectNode repeatNode, Library library, SearchbarDTO search){
        search.setType(SearchbarDTO.getCurrentType());
        if (library.getLastSearchResults().isEmpty()) {
            repeatNode.put("command", search.getCommand());
            repeatNode.put("user", search.getUsername());
            repeatNode.put("timestamp", search.getTimestamp());
            repeatNode.put("message", "Please load a source before setting the repeat status.");
        } else {

            repeatNode.put("command", search.getCommand());
            repeatNode.put("user", search.getUsername());
            repeatNode.put("timestamp", search.getTimestamp());

            int repeat = library.getRepeat() + 1;
            library.setRepeat(repeat);
            if (search.getType().equals("playlist")){
                if (library.getRepeat() == 3){
                    library.setRepeat(0);
                }
                if (library.getRepeat() == 0){
                    repeatNode.put("message", "Repeat mode changed to no repeat.");
                } else if (library.getRepeat() == 1){
                    repeatNode.put("message", "Repeat mode changed to repeat all.");
                } else if (library.getRepeat() == 2){
                    repeatNode.put("message", "Repeat mode changed to repeat current song.");
                }
            } else if (search.getType().equals("podcast") || search.getType().equals("song")){
                if (library.getRepeat() == 3){
                    library.setRepeat(0);
                }
                if (library.getRepeat() == 0){
                    repeatNode.put("message", "Repeat mode changed to no repeat.");
                } else if (library.getRepeat() == 1){
                    repeatNode.put("message", "Repeat mode changed to repeat once.");
                } else if (library.getRepeat() == 2){
                    repeatNode.put("message", "Repeat mode changed to repeat infinite.");
                }
            }
        }
    }


    public Integer getSeed() {
        return seed;
    }

    public void setSeed(Integer seed) {
        this.seed = seed;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timpestamp) {
        this.timestamp = timpestamp;
    }

    public FilterDTO getFilters() {
        return filters;
    }

    public void setFilters(FilterDTO filters) {
        this.filters = filters;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

/*
* command": "search",
    "username": "alice22",
    "timestamp": 10,
    "type": "song",
    "filters": {
      "name": "Sta"
    }*/
