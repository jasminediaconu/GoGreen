package server.helper;

public class AchievementClass {

    public int id;
    public String title;
    public String description;
    public String path;
    public int goal;
    public int progress;
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
    public AchievementClass(int id, String title, String description, String path, int goal, int progress, boolean achieved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.path = path;
        this.goal = goal;
        this.progress = progress;
        this.achieved = achieved;
    }
}
