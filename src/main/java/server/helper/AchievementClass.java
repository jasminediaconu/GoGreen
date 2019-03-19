package server.helper;

public class AchievementClass {

        public int id;
        public String title;
        public String description;
        public String path;

        /**
         * This is the UserClass, all user classes will be populated here.
         *
         * @param id int type
         * @param title  String type
         * @param description  String type
         * @param path double type
         */
        public AchievementClass(int id, String title, String description, String path) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.path = path;
        }
}
