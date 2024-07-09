package com.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.*;

@RestController
public class ImageController {

    private static final String IMAGE_PATH = "D:/demo/latest_image.jpg"; // 使用绝对路径

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            System.out.println("Received file: " + file.getOriginalFilename());
            File imageFile = new File(IMAGE_PATH);

            // 确认父目录存在，如果不存在则创建
            File parentDir = imageFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            file.transferTo(imageFile);
            System.out.println("File saved to: " + imageFile.getAbsolutePath());
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading image");
        }
    }

    @GetMapping(value = "/image/latest", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<InputStreamResource> getLatestImage() {
        try {
            File imgFile = new File(IMAGE_PATH);
            if (!imgFile.exists()) {
                throw new FileNotFoundException("latest_image.jpg (系统找不到指定的文件。)");
            }
            InputStreamResource resource = new InputStreamResource(new FileInputStream(imgFile));
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResponseEntity.status(404).body(null);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }
}
