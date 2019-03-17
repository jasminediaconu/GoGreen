package client;

class ServerRequestsTest {

    /**
    private Activity activity;
    private ServerRequests sv;

    @BeforeEach
    void setup() {
        sv = new ServerRequests();
        ServerRequests.requestUrl = "https://group72.herokuapp.com/";
        Main.sessionID = "test";
    }

    @Test
    void login() {

    }

    @Test
    void signUp() {
    }

    @Test
    void endSessionOK() {
        String response = sv.endSession();
        Assert.assertEquals("ok", response);
    }

    @Test
    void endSessionFail() {
        ServerRequests.requestUrl = "";
        Assert.assertNull(sv.endSession());
    }

    @Test
    void getItems() {
        sv.getItems();
        Assert.assertNotEquals(0, Main.items);
    }

    @Test
    void addActivityTrue() {
        activity = new Activity(2, 5, LocalDate.now());
        Assert.assertEquals(true, sv.addActivity(activity));
    }

    @Test
    void addActivityFalse() {
        ServerRequests.requestUrl = "";
        Assert.assertEquals(false, sv.addActivity(new Activity(2, 5, LocalDate.now())));
        activity = new Activity(2, 5, LocalDate.now());
        ServerRequests.requestUrl = "https://group72.herokuapp.com/";
        Assert.assertNotEquals(false, sv.addActivity(activity));
    }

    @Test
    void removeActivityTrue() {
        Assert.assertEquals(true, sv.removeActivity(activity.getActivityID()));
    }

    @Test
    void removeActivityFalseID() {
        Assert.assertEquals(false, sv.removeActivity(-1));
    }

    @Test
    void removeActivityFalseRequest() {
        ServerRequests.requestUrl = "";
        Assert.assertEquals(false, sv.removeActivity(1000));
    }

    @Test
    void retrieveActivities() {
        Assert.assertNotEquals(null, sv.retrieveActivities("h"));
        Assert.assertNotEquals(null, sv.retrieveActivities("y"));
        List<Activity> activityList = sv.retrieveActivities("w");
        Assert.assertNotEquals(0, activityList.size());
        Assert.assertNotEquals(null, activityList.get(0));
    }

    @Test
    void getClientUserProfile() {

    }

    @Test
    void updateClientUserProfile() {
    }

    @Test
    void getFollowingProfile() {
    }

    @Test
    void getGlobalBestProfile() {
    }

    @Test
    void getUserID() {
    }
    */
}