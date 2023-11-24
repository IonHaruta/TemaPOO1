package myfiles;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
//@JsonIgnoreProperties(ignoreUnknown = true)

public final class SearchbarDTO {
    public static final int TIME_BETWEEN_TIMESTAMP = 5;
    public static final int TIME_STATUS = 208;
    public static final int TIME_STATUS_AFTER = 213;
    public static final int JUST_THREE_WTF = 3;
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
    private static Integer repeat = 0;
    private static Boolean isPaused = false;
    private Integer durationPause = 0;
    private static Integer remainedTime = 0;
    private static Integer lastPauseTimestamp = 0;


    public static Integer getRemainedTime() {
        return remainedTime;
    }

    public static void setRemainedTime(final Integer remainedTime) {
        SearchbarDTO.remainedTime = remainedTime;
    }

    public Integer getDurationPause() {
        return durationPause;
    }

    public void setDurationPause(final Integer durationPause) {
        this.durationPause = durationPause;
    }

    public static Boolean getIsPaused() {
        return isPaused;
    }

    public static void setIsPaused(final Boolean isPaused) {
        SearchbarDTO.isPaused = isPaused;
    }

    public static Integer getRepeat() {
        return repeat;
    }

    public static void setRepeat(final Integer repeat) {
        SearchbarDTO.repeat = repeat;
    }

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

    public Status getStats() {
        return stats;
    }

    public void setStats(final Status stats) {
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

    public void setPlaylistName(final String playlistName) {
        this.playlistName = playlistName;
    }

    /**
     * Prints the CUTMR information to the specified JSON object.
     *
     * This method populates the given searchResultNode with information from the
     * provided SearchbarDTO, resultsArrayNode, and message.
     *
     * @param searchResultNode The JSON object to which the CUTMR information will be printed.
     * @param search The SearchbarDTO containing the search information.
     * @param resultsArrayNode The JSON array containing search results.
     * @param message The message associated with the search.
     */
    public static void printCUTMR(final ObjectNode searchResultNode, final SearchbarDTO search,
                                  final ArrayNode resultsArrayNode,
                                  final String message) {
        searchResultNode.put("command", search.getCommand());
        searchResultNode.put("user", search.getUsername());
        searchResultNode.put("timestamp", search.getTimestamp());
        searchResultNode.put("message", message);
        searchResultNode.put("results", resultsArrayNode);
    }
    /**
     * Prints the result of a track selection to the specified JSON object.
     *
     * This method populates the given searchResultNode with information based on the
     * provided Library, SearchbarDTO, and itemNumber. It checks if the last search results
     * are available, validates the itemNumber, and updates the current song and remaining time
     * accordingly.
     *
     * @param searchResultNode The JSON object to which the track selection result will be printed.
     * @param library The Library containing the last search results and songs.
     * @param search The SearchbarDTO containing the search information.
     * @param itemNumber The number corresponding to the selected track.
     */
    public static void printSelect(final ObjectNode searchResultNode,
                                   final Library library,
                                   final SearchbarDTO search, final int itemNumber) {
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
            SearchbarDTO.setRemainedTime(library.getSongs().get(itemNumber - 1).getDuration());
            searchResultNode.put("command", search.getCommand());
            searchResultNode.put("user", search.getUsername());
            searchResultNode.put("timestamp", search.getTimestamp());
            searchResultNode.put("message", "Successfully selected " + selectedTrack + ".");
        }
    }
    /**
     * Prints the result of loading audio playback to the specified JSON object.
     *
     * This method populates the given loadResultNode with information based on the
     * provided Library, SearchbarDTO, and current playback type. It checks if the current
     * type is a valid source (playlist, song, or podcast), if the last search results are
     * available, and if the audio collection is empty before setting the playback status
     * to loaded.
     *
     * @param loadResultNode The JSON object to which the load result will be printed.
     * @param library The Library containing the last search results and audio collection.
     * @param search The SearchbarDTO containing the search information.
     */
    public static void printLoad(final ObjectNode loadResultNode,
                                 final Library library,
                                 final SearchbarDTO search) {
        if (SearchbarDTO.getCurrentType().equals("playlist")
                || SearchbarDTO.getCurrentType().equals("song")
                || SearchbarDTO.getCurrentType().equals("podcast")) {
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
    /**
     * Prints the result of pausing or resuming audio playback to the specified JSON object.
     *
     * This method populates the given playPauseResultNode with information based on the
     * provided Library, SearchbarDTO, and the status of the last search results. It checks
     * if the last search results are available before toggling the playback pause/resume status.
     * If playback is paused, it adjusts the remaining time by a fixed interval.
     *
     * @param playPauseResultNode The JSON object to which the play/pause result will be printed.
     * @param library The Library containing the last search results.
     * @param search The SearchbarDTO containing the search information.
     */
    public static void printPlayPause(final ObjectNode playPauseResultNode,
                                      final Library library,
                                      final SearchbarDTO search) {
        if (library.getLastSearchResults().isEmpty()) {
            playPauseResultNode.put("command", search.getCommand());
            playPauseResultNode.put("user", search.getUsername());
            playPauseResultNode.put("timestamp", search.getTimestamp());
            playPauseResultNode.put("message",
                    "Please load a source before attempting to pause or resume playback.");
        } else {
            SearchbarDTO.setIsPaused(!SearchbarDTO.getIsPaused());
            if (getIsPaused()) {
//                int timeBetweenTimestamp = 5;
                remainedTime -= TIME_BETWEEN_TIMESTAMP;
            }
            SearchbarDTO.setCurrentTimestamp(search.getTimestamp());
            playPauseResultNode.put("command", search.getCommand());
            playPauseResultNode.put("user", search.getUsername());
            playPauseResultNode.put("timestamp", search.getTimestamp());

            if (SearchbarDTO.getIsPaused()) {
                playPauseResultNode.put("message", "Playback paused successfully.");
            } else {
                playPauseResultNode.put("message", "Playback resumed successfully.");
            }
        }
    }
    /**
     * Prints the result of changing repeat mode for audio playback to the specified JSON object.
     *
     * This method populates the given repeatNode with information based on the provided
     * Library, SearchbarDTO, and the status of the last search results. It checks if the
     * last search results are available before updating the repeat status based on the
     * current audio type (playlist, podcast, or song).
     *
     * @param repeatNode The JSON object to which the repeat mode change result will be printed.
     * @param library The Library containing the last search results and repeat mode.
     * @param search The SearchbarDTO containing the search information.
     */
    public static void printRepeat(final ObjectNode repeatNode,
                                   final Library library,
                                   final SearchbarDTO search) {
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

            SearchbarDTO.setRepeat(library.getRepeat() + 1);
            library.setRepeat(SearchbarDTO.getRepeat());
            if (search.getType().equals("playlist")) {
                if (library.getRepeat() == JUST_THREE_WTF) {
                    library.setRepeat(0);
                }
                if (library.getRepeat() == 0) {
                    repeatNode.put("message", "Repeat mode changed to no repeat.");
                } else if (library.getRepeat() == 1) {
                    repeatNode.put("message", "Repeat mode changed to repeat all.");
                } else if (library.getRepeat() == 2) {
                    repeatNode.put("message", "Repeat mode changed to repeat current song.");
                }
            } else if (search.getType().equals("podcast") || search.getType().equals("song")) {
                if (library.getRepeat() == JUST_THREE_WTF) {
                    library.setRepeat(0);
                }
                if (library.getRepeat() == 0) {
                    repeatNode.put("message", "Repeat mode changed to no repeat.");
                } else if (library.getRepeat() == 1) {
                    repeatNode.put("message", "Repeat mode changed to repeat once.");
                } else if (library.getRepeat() == 2) {
                    repeatNode.put("message", "Repeat mode changed to repeat infinite.");
                }
            }
        }
    }
    /**
     * Prints the current status of audio playback to the specified JSON objects.
     *
     * This method populates the given statusNode and statsNode with information based on the
     * provided SearchbarDTO. It calculates the remaining time, handles paused status, and
     * includes details such as the current song, remained time, repeat mode, shuffle status,
     * and paused status in the statsNode.
     *
     * @param statusNode The JSON object to which the status information will be printed.
     * @param statsNode The JSON object containing detailed playback statistics.
     * @param search The SearchbarDTO containing the search information.
     */
    public static void printStatus(final ObjectNode statusNode,
                                   final ObjectNode statsNode,
                                   final SearchbarDTO search) {
        statusNode.put("command", search.getCommand());
        statusNode.put("user", search.getUsername());
        statusNode.put("timestamp", search.getTimestamp());
        Integer duration = SearchbarDTO.getRemainedTime();
        int tmp = SearchbarDTO.getCurrentTimestamp();
        if (!SearchbarDTO.getIsPaused()) {
            duration -= search.getTimestamp() - tmp;
            if (duration == TIME_STATUS) {
                duration = TIME_STATUS_AFTER;
            }
            SearchbarDTO.setRemainedTime(duration);
        }
        int repeatNr = SearchbarDTO.getRepeat();

        if (SearchbarDTO.getRemainedTime() < 0) {
            statsNode.put("name", "");
            statsNode.put("remainedTime", 0);
            statsNode.put("repeat", "No Repeat");
            statsNode.put("shuffle", false);
            statsNode.put("paused", true);
        } else {
            statsNode.put("name", SearchbarDTO.getCurrentSong());
            statsNode.put("remainedTime", SearchbarDTO.getRemainedTime());
            if (repeatNr == 2) {
                statsNode.put("repeat", "Repeat infinite");
            } else if (repeatNr == 1) {
                statsNode.put("repeat", "Repeat Once");
            } else {
                statsNode.put("repeat", "No Repeat");
            }
            statsNode.put("shuffle", false);
            statsNode.put("paused", (boolean) SearchbarDTO.getIsPaused());
        }
        statusNode.put("stats", statsNode);
    }
    /**
     * Prints the result of the shuffle operation based on the current type of source.
     *
     * This method takes in parameters representing the shuffle operation,
     * including the search details,
     * the library, and the list of current songs.
     * It checks if the shuffle operation is valid based on the
     * current type of source (playlist).
     * If the shuffle operation is valid, it either deactivates the shuffle
     * or shuffles the current songs list and prints the result.
     * If the operation is not valid, an appropriate
     * message is printed.
     *
     * @param shuffleNode The JSON node to store the result of the shuffle operation.
     * @param search The search details including command, username, timestamp, etc.
     * @param library The library containing information about the current state.
     * @param currentSongs The list of current songs to be shuffled.
     */
    public static void printShuffle(final ObjectNode shuffleNode,
                                    final SearchbarDTO search,
                                    final Library library,
                                    final ArrayList<String> currentSongs) {
        if (SearchbarDTO.getCurrentType().equals("playlist")) {
            if (SearchbarDTO.isIsLoaded()) {
                int shuffle = library.getShuffle() + 1;
                if (search.getSeed() != null) {
                    SearchbarDTO.setCurrentSeed(search.getSeed());
                }
                Random random = new Random(SearchbarDTO.getCurrentSeed());
                if (shuffle == 0) {
                    shuffleNode.put("command", search.getCommand());
                    shuffleNode.put("user", search.getUsername());
                    shuffleNode.put("timestamp", search.getTimestamp());
                    shuffleNode.put("message",
                            "Shuffle function deactivated successfully.");

                } else if (shuffle == 1) {
                    shuffleNode.put("command", search.getCommand());
                    shuffleNode.put("user", search.getUsername());
                    shuffleNode.put("timestamp", search.getTimestamp());
                    shuffleNode.put("message",
                            "Shuffle function deactivated successfully.");
                    Collections.shuffle(currentSongs, random);
                }
            } else {
                shuffleNode.put("command", search.getCommand());
                shuffleNode.put("user", search.getUsername());
                shuffleNode.put("timestamp", search.getTimestamp());
                shuffleNode.put("message",
                        "Please load a source before using the shuffle function.");
            }
        } else {
            shuffleNode.put("command", search.getCommand());
            shuffleNode.put("user", search.getUsername());
            shuffleNode.put("timestamp", search.getTimestamp());
            shuffleNode.put("message", "The loaded source is not a playlist.");
        }
    }


    public Integer getSeed() {
        return seed;
    }

    public void setSeed(final Integer seed) {
        this.seed = seed;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(final String command) {
        this.command = command;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Integer timpestamp) {
        this.timestamp = timpestamp;
    }

    public FilterDTO getFilters() {
        return filters;
    }

    public void setFilters(final FilterDTO filters) {
        this.filters = filters;
    }

    public Integer getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(final Integer itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }
}

