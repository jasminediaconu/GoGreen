package server;

import org.springframework.web.bind.annotation.*;

/**
 * This class handles the REST controlling for any session end.
 * It will end the session given a session ID.
 * @author wouthaakman
 *
 */
@RestController
public class SessionController {

    /**
     * This function handles all session end requests.
     * @param sessionID String type
     * @return a response to notify the client whether the session has been ended successfully.
     */
    @RequestMapping(value="/end", method= RequestMethod.POST)
    public String endSession(@RequestBody String sessionID) {
        ServerApp.removeSessionID(sessionID);
        return "ok";
    }
}
