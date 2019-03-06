package server.objects;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class ItemController {

    @RequestMapping(value="/getItems", method= RequestMethod.POST)
    public void getItems() {
        
    }
}
