package server;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class handles the REST controlling for any default request,
 * when the user either doesn't provide a path or the /info path.
 * It will return some info about the project.
 * @author wouthaakman
 *
 */
@RestController
public class InfoController {

    /**
     * This function returns some information about this project.
     * @return some information about this project as type String.
     */
    @RequestMapping("/info")
    public String info() {
        return "Welcome to the GoGreen project of Group72.\n\nOur aim is to go green, do you go green with us?\n\nTry logging in by going to:\nhttps://group72.herokuapp.com/login?username=[usrnm]&password=[pwd]";
    }

}
