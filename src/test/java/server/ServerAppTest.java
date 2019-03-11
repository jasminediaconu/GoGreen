package server;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class ServerAppTest {

    @Test
    void createNewSessionID() {
//        Assert.assertEquals(UUID.randomUUID().toString(), ServerApp.createNewSessionID());
//        Assert.assertEquals(UUID.randomUUID().toString(), ServerApp.createNewSessionID());
    }

    @Test
    void addSessionID() {
        String uuid = ServerApp.createNewSessionID();
        ServerApp.addSessionID(uuid, 2);
        Assert.assertEquals(ServerApp.getUserIDFromSession(uuid), 2);
    }

    @Test
    void removeSessionID() {
        String uuid = ServerApp.createNewSessionID();
        ServerApp.addSessionID(uuid, 5);
        Assert.assertEquals(ServerApp.getUserIDFromSession(uuid), 5);
        ServerApp.removeSessionID(uuid);
//        Assert.assertEquals(ServerApp.getUserIDFromSession(uuid), null);
    }

    @Test
    void getUserIDFromSession() {
        String uuid1 = ServerApp.createNewSessionID();
        ServerApp.addSessionID(uuid1, 12);
        Assert.assertEquals(ServerApp.getUserIDFromSession(uuid1), 12);
        String uuid2 = ServerApp.createNewSessionID();
        ServerApp.addSessionID(uuid2, 14);
        Assert.assertEquals(ServerApp.getUserIDFromSession(uuid1), 12);
        Assert.assertEquals(ServerApp.getUserIDFromSession(uuid2), 14);
    }
}