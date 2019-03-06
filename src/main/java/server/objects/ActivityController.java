package server.objects;

import client.objects.Item;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class ActivityController {

    @RequestMapping(value="/addActivity", method= RequestMethod.POST)
    public String addActivity(@RequestBody ActivityClass a) {

    }



}

class ActivityClass {

    private int activityID;
    private Item item;
    private double amount;
    private LocalDate date;

    /**
     * This is the ActivityClass class, all activities will be populated in here.
     * @param item Item type
     * @param amount double type
     * @param date LocalDate type
     */
    public ActivityClass(int activityID,  Item item, double amount, LocalDate date) {
        this.activityID = activityID;
        this.item = item;
        this.amount = amount;
        this.date = date;
    }

}
