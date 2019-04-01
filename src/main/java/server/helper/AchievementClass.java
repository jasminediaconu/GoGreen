package server.helper;

public class AchievementClass {

    public int id;
    public String title;
    public String description;
    public String path;
    public int progress;
    public int goal;
    public boolean achieved;

    /**
     * This is the AchievementClass, all achievements will be populated here.
     *
     * @param id          int type
     * @param title       String type
     * @param description String type
     * @param path        double type
     * @param progress    int type
     * @param achieved    boolean type
     */
    public AchievementClass(int id, String title, String description, String path, int progress, int goal, boolean achieved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.path = path;
        this.progress = progress;
        this.goal = goal;
        this.achieved = achieved;
    }
}
