package server.objects;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.ServerApp;
import server.helper.ItemClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the REST controlling for any item related requests.
 * It will handle getting all the items from the database.
 *
 * @author wouthaakman
 */
@RestController
public class ItemController {

    private static PreparedStatement getItems;

    static {
        try {
            getItems = ServerApp.dbConnection.prepareStatement("SELECT * FROM items");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function will handle retrieving the items from the database,
     * converting them to ItemClass objects that are inserted in a list and converted to a JSON
     * This JSON will be send to the client as a response.
     *
     * @return a JSON String containing the Items in the database
     */
    @RequestMapping(value = "/getItems", method = RequestMethod.POST)
    public String getItems() {
        try {
            List<ItemClass> items = new ArrayList<ItemClass>();
            ResultSet result = getItems.executeQuery();
            while (result.next()) {
                ItemClass item = new ItemClass(result.getInt(1),
                        result.getString(2), result.getString(3),
                        result.getDouble(4));
                items.add(item);
            }

            return ServerApp.gson.toJson(items);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
