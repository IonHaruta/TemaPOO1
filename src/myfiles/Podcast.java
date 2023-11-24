package myfiles;

import java.util.ArrayList;

public final class Podcast {
    public static final int JUST_FIVE_WTF = 5;
    private String name;
    private String owner;
    private ArrayList<Episode> episodes;

    /**
     * Filters a list of podcasts by name,
     * keeping only those that start with the specified prefix.
     *
     * This method takes in a name prefix,
     * a list of podcasts, and a temporary list for filtering.
     * It iterates through the input list of podcasts,
     * adding podcasts whose names start with the given prefix
     * to the temporary list.
     * The input list is then updated in-place with the filtered podcasts.
     *
     * @param name The prefix to filter podcasts by.
     * @param podcastsEX The input list of podcasts to be filtered.
     * @param podcastsTMP The temporary list used for filtering.
     */
    public static void filterNamePodcast(final String name,
                                         final ArrayList<Podcast> podcastsEX,
                                         final ArrayList<Podcast> podcastsTMP) {
        if (name != null && !name.isEmpty()) {
            for (Podcast podcast : podcastsEX) {
                if (podcast.getName().startsWith(name)) {
                    podcastsTMP.add(podcast);
                }
            }
            podcastsEX.clear();
            podcastsEX.addAll(podcastsTMP);
            podcastsTMP.clear();
        }
    }
    /**
     * Filters a list of podcasts by owner,
     * keeping only those that contain the specified owner.
     *
     * This method takes in an owner, a list of podcasts,
     * and a temporary list for filtering.
     * It iterates through the input list of podcasts,
     * adding podcasts that contain the specified owner
     * to the temporary list.
     * The input list is then updated in-place with the filtered podcasts.
     *
     * @param owner The owner to filter podcasts by.
     * @param podcastsEX The input list of podcasts to be filtered.
     * @param podcastsTMP The temporary list used for filtering.
     */
    public static void filterOwnerPodcast(final String owner,
                                          final ArrayList<Podcast> podcastsEX,
                                          final ArrayList<Podcast> podcastsTMP) {
        if (owner != null && !owner.isEmpty()) {
            for (Podcast podcast : podcastsEX) {
                if (podcast.getOwner().contains(owner)) {
                    podcastsTMP.add(podcast);
                }
            }
            podcastsEX.clear();
            podcastsEX.addAll(podcastsTMP);
            podcastsTMP.clear();
        }
    }
    /**
     * Extracts podcast names from a list of
     * podcasts and populates a temporary list.
     *
     * This method takes in a list of podcasts,
     * a temporary list for storing podcast names, and a count
     * to limit the number of podcasts to extract.
     * It iterates through the input list of podcasts,
     * adding podcast names to the temporary list.
     * The count parameter determines the maximum number
     * of podcasts to extract.
     * The method stops adding podcasts to the temporary list once the count is
     * reached or exceeded.
     *
     * @param podcastsEX The list of podcasts to extract names from.
     * @param podcasturiFINAL The temporary list for storing podcast names.
     * @param count The maximum number of podcasts to extract.
     */
    public static void sortPodcasts(final ArrayList<Podcast> podcastsEX,
                                    final ArrayList<String> podcasturiFINAL,
                                    int count) {
        for (Podcast podcasturi : podcastsEX) {
            podcasturiFINAL.add(podcasturi.getName());
            count++;
            //NU POATE FI FINAL COUNT
            if (count >= JUST_FIVE_WTF) {
                break;
            }
        }
    }
    public Podcast() {
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

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(final ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }
}
