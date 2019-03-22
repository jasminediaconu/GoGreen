package server;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

@RestController
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam String sessionID, @RequestBody String encodedImage) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedImage);
            FileUtils.writeByteArrayToFile(new File("profilePicture" + userID), decodedBytes);

            return "profilePicture" + userID;
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
