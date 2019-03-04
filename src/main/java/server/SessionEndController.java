package server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class handles the REST controlling for any session end.
 * It will end the session given a session ID.
 * @author wouthaakman
 *
 */
@RestController
public class SessionEndController {

    /**
     * This function handles all session end requests
     * @param sessionID String type
     * @return a response to notify the client whether the session has been ended succesfully.
     */
    @RequestMapping(value="/end", method= RequestMethod.POST)
    public String getResponse(@RequestParam String sessionID) {
        ServerApp.removeSessionID(sessionID);
        return "ok";
    }
}
