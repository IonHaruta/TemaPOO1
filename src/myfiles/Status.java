package myfiles;

public final class Status {
    private String name;
    private Integer remainedTime;
    private String repeat;
    private Boolean shuffle = false;
    private Boolean paused = false;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getRemainedTime() {
        return remainedTime;
    }

    public void setRemainedTime(final Integer remainedTime) {
        this.remainedTime = remainedTime;
    }

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(final String repeat) {
        this.repeat = repeat;
    }

    public Boolean getShuffle() {
        return shuffle;
    }

    public void setShuffle(final Boolean shuffle) {
        this.shuffle = shuffle;
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(final Boolean paused) {
        this.paused = paused;
    }
}
