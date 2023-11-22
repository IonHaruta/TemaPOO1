package myfiles;

import java.util.ArrayList;

public final class Podcast {
    private String name;
    private String owner;
    private ArrayList<Episode> episodes;

    public static void filterNamePodcast(String name, ArrayList<Podcast> podcastsEX, ArrayList<Podcast> podcastsTMP){
        if (name != null && !name.isEmpty()){
            for (Podcast podcast : podcastsEX) {
                if (podcast.getName().startsWith(name)){
                    podcastsTMP.add(podcast);
                }
            }
            podcastsEX.clear();
            podcastsEX.addAll(podcastsTMP);
            podcastsTMP.clear();
        }
    }
    public static void filterOwnerPodcast(String owner, ArrayList<Podcast> podcastsEX, ArrayList<Podcast> podcastsTMP){
        if (owner != null && !owner.isEmpty()){
            for (Podcast podcast : podcastsEX) {
                if (podcast.getOwner().contains(owner)){
                    podcastsTMP.add(podcast);
                }
            }
            podcastsEX.clear();
            podcastsEX.addAll(podcastsTMP);
            podcastsTMP.clear();
        }
    }
    public static void sortPodcasts(ArrayList<Podcast> podcastsEX, ArrayList<String> podcasturiFINAL, int count){
        for (Podcast podcasturi : podcastsEX) {
            podcasturiFINAL.add(podcasturi.getName());
            count++;

            if (count >= 5) {
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
