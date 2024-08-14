package com.test.tserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

@Slf4j
@RestController
public class ProjectController {

    @PostMapping("/upload")
    public String handleImageUpload(@RequestBody ImageData imageData) {
        // Преобразование строки base64 в байты
        String base64Image = imageData.getImage().split(",")[1];
        byte[] imageBytes = java.util.Base64.getDecoder().decode(base64Image);

        File file = new File("/images/image" + new Random().nextInt(1000) + ".png");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(imageBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Сохранение изображения на сервере (в данном примере просто логируем размер изображения)
        System.out.println("Received image with size: " + imageBytes.length + " bytes");

        // Можно сохранить изображение на диск или в базу данных

        return "Image uploaded successfully";
    }
}

class ImageData {
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}