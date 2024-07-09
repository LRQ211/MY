package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.IOException;

@MapperScan("com.demo.mapper") // MyBatis Mapper 扫描路径
@EnableJpaRepositories(basePackages = "com.demo.repository") // JPA Repository 扫描路径
@SpringBootApplication(scanBasePackages = {"com.demo"}) // 确保 Spring Boot 扫描路径包含所有包
public class DemoApplication implements CommandLineRunner {

    private static final String IMAGE_PATH = "latest_image.jpg";

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createEmptyImageFile();
    }

    private void createEmptyImageFile() {
        File imageFile = new File(IMAGE_PATH);
        if (!imageFile.exists()) {
            try {
                imageFile.createNewFile(); // 创建空文件
                System.out.println("Created empty file: " + imageFile.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
