package server.helper;

public class AchievementClass {

    public int id;
    public String title;
    public String description;
    public String path;
    public int goal;

    /**
     * This is the AchievementClass, all achievements will be populated here.
     *
     * @param id          int type
     * @param title       String type
     * @param description String type
     * @param path        double type
     */
    public AchievementClass(int id, String title, String description, String path, int goal) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.path = path;
        this.goal = goal;
    }
}
