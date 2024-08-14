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

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://1e2a-193-30-245-6.ngrok-free.app/upload"))
                .POST(HttpRequest.BodyPublishers.ofString("{\"image\" : \"" + imageData.getImage() + "\"}"))
                .header("Content-Type", "application/json").build();

        log.info(base64Image);

        try {
            var ll = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            log.info(ll.body());
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