package main;

import checker.Checker;
import checker.CheckerConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.LibraryInput;
import myfiles.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;

import static myfiles.Playlist.*;
import static myfiles.Podcast.*;
import static myfiles.SearchbarDTO.*;
import static myfiles.Song.*;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    static final String LIBRARY_PATH = CheckerConstants.TESTS_PATH + "library/library.json";

    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * DO NOT MODIFY MAIN METHOD
     * Call the checker
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(CheckerConstants.TESTS_PATH);
        Path path = Paths.get(CheckerConstants.RESULT_PATH);

        if (Files.exists(path)) {
            File resultFile = new File(String.valueOf(path));
            for (File file : Objects.requireNonNull(resultFile.listFiles())) {
                file.delete();
            }
            resultFile.delete();
        }
        Files.createDirectories(path);

        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.getName().startsWith("library")) {
                continue;
            }

            String filepath = CheckerConstants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getName(), filepath);
            }
        }

        Checker.calculateScore();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        LibraryInput library1 = objectMapper.readValue(new File(LIBRARY_PATH), LibraryInput.class);

        ArrayNode outputs = objectMapper.createArrayNode();


        try {

            File jsonFile = new File(LIBRARY_PATH);
            Library library = objectMapper.readValue(jsonFile, Library.class);

            ArrayList<String> currentSongs = new ArrayList<>();
            ArrayList<SearchbarDTO> searchbardto = objectMapper.readValue(new File("input/test02_playPause_song.json"), new TypeReference<ArrayList<SearchbarDTO>>() {});
            for (SearchbarDTO search : searchbardto) {
                if (search.getCommand().equals("search")){
                    SearchbarDTO.setCurrentType(search.getType());
                    switch (search.getType()){
                        case "song":
                            if (search.getFilters() != null) {
                                String name = search.getFilters().getName();
                                String album = search.getFilters().getAlbum();
                                ArrayList<String> tags = search.getFilters().getTags();
                                String lyrics = search.getFilters().getLyrics();
                                String genre = search.getFilters().getGenre();
                                String releaseYear = search.getFilters().getReleaseYear();
                                String artist = search.getFilters().getArtist();

                                ObjectNode searchResultNode = objectMapper.createObjectNode();
                                ArrayList<Song> songsEX = new ArrayList<>(library.getSongs());
                                ArrayList<Song> songTMP = new ArrayList<>();
                                ArrayList<String> melodiiTMP = new ArrayList<>();
                                int count = 0;

                                filterName(name,songsEX, songTMP);
                                filterAlbum(album,songsEX, songTMP);
                                filterTags(tags,songsEX,songTMP);
                                filterLyrics(lyrics,songsEX,songTMP);
                                filterGenre(genre,songsEX,songTMP);
                                filterRealeaseYear(releaseYear,songsEX,songTMP);
                                filterArtist(artist,songsEX,songTMP);
                                sortSongs(songsEX,melodiiTMP,count);

                                currentSongs = new ArrayList<>(melodiiTMP);

                                library.setLastSearchResults(melodiiTMP);
                                ArrayNode resultsArrayNode = objectMapper.valueToTree(melodiiTMP);
                                printCUTMR(searchResultNode,search,resultsArrayNode,melodiiTMP,"Search returned " + melodiiTMP.size() + " results");
                                outputs.add(searchResultNode);
                            }
                            break;
                        case "podcast":
                            if (search.getFilters() != null) {
                                String name = search.getFilters().getName();
                                String owner = search.getFilters().getOwner();

                                ObjectNode searchResultNode = objectMapper.createObjectNode();
                                ArrayList<Podcast> podcastsEX = new ArrayList<>(library.getPodcasts());
                                ArrayList<Podcast> podcastsTMP = new ArrayList<>();
                                ArrayList<String> podcasturiFINAL = new ArrayList<>();
                                int count = 0;

                                filterNamePodcast(name,podcastsEX,podcastsTMP);
                                filterOwnerPodcast(owner,podcastsEX,podcastsTMP);
                                sortPodcasts(podcastsEX,podcasturiFINAL,count);

                                library.setLastSearchResults(podcasturiFINAL);
                                ArrayNode resultsArrayNode = objectMapper.valueToTree(podcasturiFINAL);
                                printCUTMR(searchResultNode,search,resultsArrayNode,podcasturiFINAL,"Search returned " + podcasturiFINAL.size() + " results");
                                outputs.add(searchResultNode);
                            }
                            break;
                        case "playlist":
                            if (search.getFilters() != null) {
                                String name = search.getFilters().getName();
                                String owner = search.getFilters().getOwner();

                                ObjectNode searchResultNode = objectMapper.createObjectNode();
                                ArrayList<Playlist> playlistsEX = new ArrayList<>(library.getPlaylist());
                                ArrayList<Playlist> playlistsTMP = new ArrayList<>();
                                ArrayList<String> playlistFINAL = new ArrayList<>();
                                int count = 0;

                                filterNamePlaylist(name,playlistsEX,playlistsTMP);
                                filterOwnerPlaylist(owner,playlistsEX,playlistsTMP);
                                sortPlaylist(playlistsEX,playlistFINAL,count);

                                library.setLastSearchResults(playlistFINAL);
                                ArrayNode resultsArrayNode = objectMapper.valueToTree(playlistFINAL);
                                printCUTMR(searchResultNode,search,resultsArrayNode,playlistFINAL,"Search returned " + playlistsEX.size() + " results");
                                outputs.add(searchResultNode);
                            }
                            break;
                    }
                } else if (search.getCommand().equals("select")) {
                    int itemNumber = search.getItemNumber();
                    ObjectNode searchResultNode = objectMapper.createObjectNode();
                    printSelect(searchResultNode, library, search, itemNumber);
                    outputs.add(searchResultNode);
                } else if (search.getCommand().equals("load")){
                    ObjectNode loadResultNode = objectMapper.createObjectNode();
                    printLoad(loadResultNode, library, search);
                    outputs.add(loadResultNode);
                } else if (search.getCommand().equals("playPause")){
                    ObjectNode playPauseResultNode = objectMapper.createObjectNode();
                    printPlayPause(playPauseResultNode, library, search);
                    outputs.add(playPauseResultNode);
                } else if (search.getCommand().equals("repeat")){
                    ObjectNode repeatNode = objectMapper.createObjectNode();
                    printRepeat(repeatNode, library, search);
                    outputs.add(repeatNode);
                } else if (search.getCommand().equals("shuffle")){
                    ObjectNode shuffleNode = objectMapper.createObjectNode();
                   if (SearchbarDTO.getCurrentType().equals("playlist")){
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
                               shuffleNode.put("message", "Shuffle function deactivated successfully.");

                           } else if (shuffle == 1) {
                               shuffleNode.put("command", search.getCommand());
                               shuffleNode.put("user", search.getUsername());
                               shuffleNode.put("timestamp", search.getTimestamp());
                               shuffleNode.put("message", "Shuffle function deactivated successfully.");
                               Collections.shuffle(currentSongs, random);
                           }
                       } else {
                           shuffleNode.put("command", search.getCommand());
                           shuffleNode.put("user", search.getUsername());
                           shuffleNode.put("timestamp", search.getTimestamp());
                           shuffleNode.put("message", "Please load a source before using the shuffle function.");
                       }
                   } else {
                       shuffleNode.put("command", search.getCommand());
                       shuffleNode.put("user", search.getUsername());
                       shuffleNode.put("timestamp", search.getTimestamp());
                       shuffleNode.put("message", "The loaded source is not a playlist.");
                   }
                } else if(search.getCommand().equals("status")){
                    ObjectNode statusNode = objectMapper.createObjectNode();
                    ObjectNode statsNode = objectMapper.createObjectNode();
                    statusNode.put("command", search.getCommand());
                    statusNode.put("user", search.getUsername());
                    statusNode.put("timestamp", search.getTimestamp());
                    statsNode.put("name", SearchbarDTO.getCurrentSong());
                    statsNode.put("remainedTime", "");
                    statsNode.put("repeat", "No Repeat");
                    statsNode.put("shuffle", false);
                    statsNode.put("paused", false);
                    statusNode.put("stats", "");
                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }



        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), outputs);
    }


}
