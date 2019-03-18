package server;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ServerAppTest {


    @Test
    void addSessionID() {
        String uuid = ServerApp.createNewSessionID();
        ServerApp.addSessionID(uuid, 2);
        Assert.assertEquals(2, ServerApp.getUserIDFromSession(uuid));
        ServerApp.addSessionID(uuid, 3);
        Assert.assertEquals(2, ServerApp.getUserIDFromSession(uuid));
    }

    @Test
    void removeSessionID() {
        String uuid = ServerApp.createNewSessionID();
        ServerApp.addSessionID(uuid, 5);
        Assert.assertEquals(5, ServerApp.getUserIDFromSession(uuid));
        ServerApp.removeSessionID(uuid);
    }

    @Test
    void getUserIDFromSession() {
        String uuid1 = ServerApp.createNewSessionID();
        ServerApp.addSessionID(uuid1, 12);
        Assert.assertEquals(12, ServerApp.getUserIDFromSession(uuid1));
        String uuid2 = ServerApp.createNewSessionID();
        ServerApp.addSessionID(uuid2, 14);
        Assert.assertEquals(12, ServerApp.getUserIDFromSession(uuid1));
        Assert.assertEquals(14, ServerApp.getUserIDFromSession(uuid2));
        Assert.assertEquals(-1, ServerApp.getUserIDFromSession("does not exist"));
    }
}