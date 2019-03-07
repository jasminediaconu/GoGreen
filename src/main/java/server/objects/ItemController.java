package server.objects;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import server.ServerApp;
import server.helper.ItemClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ItemController {

    private static PreparedStatement getItems;

    static {
        try{
            getItems = ServerApp.dbConnection.prepareStatement("SELECT * FROM items");
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/getItems", method= RequestMethod.POST)
    public String getItems() {
        try{
            List<ItemClass> items = new ArrayList<ItemClass>();
            ResultSet result = getItems.executeQuery();
            while(result.next()){
                ItemClass item = new ItemClass(result.getInt(1), result.getString(2), result.getString(3), result.getDouble(4));
                items.add(item);
            }

            return new Gson().toJson(items);
        }catch(Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
