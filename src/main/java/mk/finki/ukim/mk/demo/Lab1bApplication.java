package mk.finki.ukim.mk.demo;


import jakarta.servlet.annotation.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan

public class Lab1bApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab1bApplication.class, args);
    }

}