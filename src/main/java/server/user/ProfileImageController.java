package server.user;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import server.ServerApp;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProfileImageController {

    private static Map<Integer, String> profilePictures = new HashMap<>();

    @RequestMapping(value = "/uploadProfileImage", method = RequestMethod.POST)
    public String uploadProfileImage(@RequestParam String sessionID, @RequestBody String encodedImage) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }

        profilePictures.put(userID, encodedImage);

        return "success";
    }

    @RequestMapping(value = "/getProfileImage", method = RequestMethod.POST)
    public String getProfileImage(@RequestParam String sessionID) {
        int userID = ServerApp.getUserIDfromSession(sessionID);
        if (userID == -1) {
            return null;
        }

        String response = profilePictures.get(userID);
        if(response == null || response.length() <= 1) {
            response = "iVBORw0KGgoAAAANSUhEUgAAAEAAAABACAYAAACqaXHeAAAABGdBTUEAALGPC/xhBQAAACBjS"
                    + "FJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAABmJLR0QA/wD/AP+gvaeTA"
                    + "AAAB3RJTUUH4wQHCDYEtDFZIQAABP5JREFUeNrtm2lTG0cQhp/eU9LqwhDOkLgq//9HxUb3fe09O"
                    + "/kgS8blAALvakWZ9yMljbqf6Tm6e5CHbk/zG8so24Cy9QGgbAPK1geAsg0oWx8AyjagbH0AKNuAs"
                    + "vUBoGwDypZ1zB8TEQCyLEMphVIZGo1hGFimiWEYiAhaHy89OSoA3w+YL5es1muiKEYphQZMw8C2b"
                    + "epejVazSd2rYZrmUUBI0dmgiBCGEf3RiOlsTpIke8d2EQHs/2aaJs1GnevLS5qN+vsHMF8s+drt4"
                    + "vvBDw4/J601tmVxfXXJ9eUfGEZxW1WhS2AynfHvQ4ckSQ52HraRkSpFp9cjSVPub28Kg1DIqCLCY"
                    + "rniy0OHJElf5fxjaQ2D4YjuYEhR20EhAMIo4munS5wkvNH3H9QfDJkvFm8GeXQAw9GYzSvW/EtSS"
                    + "tEdDIiT5LQBiEAQhoyns1xm/vu4wmbjM5vnHwU5R4Awmy8KmSmtNdPZDKXU6QJQSrFcrShixxIRf"
                    + "D8gCMNcoyA3AALESUIQRoVsVgCpUvhBmOuY+UWACHEc5x6ij6W1JgxPFQCQpoosywoDAJCkaa45Q"
                    + "q4AMl2s80DuCVKuAAwpvrxgnPIxaFlWoYkLgG3bp3kKaK1xHRvLNAtzXkSouG6uY+Y6XbZtU61UC"
                    + "itkWJZFrVY9XQCmadJqNgq5B2it8WpVKq57uqcAWtNut3AcJ3cAhmFwfnaGmfMSyxWABiquy8X5p"
                    + "1yN1FpT9zza7dZpH4M7XV2cU/e83Iy1LIvb6ytsK/8CViEAHMfh/u4W13V+GYKIcHt9RavZKGRzL"
                    + "QSA1ppG3ePz/f0vQRARbr4VRotSobeWdqvJP5//plH3tmAO/J7WGtu2+evPO+5urgvLLuFIfYE4j"
                    + "hmOJ4ynM6Io+h4RO8ceRYhtW7SaTa4v/8Cr1Yo07TgAHiuOY5arNav1mjCKSNNt6mwYBo5jU/c8m"
                    + "o0GtWoFwzCO0hkqtC+wC12t9bc6geDVajiOQ5Im29aYBtM0sC0b27awTHOfUu/yiiJB5A5g19xMk"
                    + "pQgDNn4Pn4QEEURSZKilCLT+ienRAQR2cNwXYdatYpXq1KtVnG+JUF5w8htCYgISZqyXm+YLxas1"
                    + "huiOEZl2X6Nv6Y1tpNhGLiOQ92r0W61aNTrOLZ18IZaOAARIYpjZrM5k9mMjR/sQzjP3VvrbRu9W"
                    + "qnw6azN+VkbN4fM8M0ARIQ0TZnM5gxHY/wgQGtd6JG1AyEiuK7L5fknLi7OcWz77XeNtwJYbzZ0e"
                    + "gOWqxVZlhXu+E8g2Fai657H3c0VrWbzOAC01ownUzq9PlEcH93x/7PHsqz9jfG1FalXnQJZltHtD"
                    + "+gPh2RZ8eF+iEQEpRSdbo8ojrm/u31VVepgXJnWdHp9eoOt86cmDYzGE7587byqN3EwgMFwRH84O"
                    + "uoDprdoPJ3y0O0fbOeLAESE+WJJtz84eed3Go7HjCbTfADEcUyn1ydN07L9OliZ1vT6A4Lg5Ubqi"
                    + "wBGkykb3z+JDe9QCdtXKoPR+MWofRZAnCRMprN3E/o/QBBhtpjjB8+/VDGeG2C1WhPm3I8/puIkZ"
                    + "b5YPvuZJwForVmu1mTvcPYfOcFqvX72WHwSgFIKPwjKduGXFYTRsxv4kzdBlWWIUEiT45gyDINUK"
                    + "dwnaglP5gJaa+JH73rfqwTBtp/uWj8ZASKC+85n/xD99v8w8QGgbAPK1m8P4D8Y9Ipup/HkuQAAA"
                    + "CV0RVh0ZGF0ZTpjcmVhdGUAMjAxOS0wNC0wN1QwODo1NDowNC0wNDowME9IZ5QAAAAldEVYdGRhd"
                    + "GU6bW9kaWZ5ADIwMTktMDQtMDdUMDg6NTQ6MDQtMDQ6MDA+Fd8oAAAAAElFTkSuQmCC";
        }
        return response;
    }

}
