package code.choi;

import code.choi.ch01.Ch01Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@ComponentScan(basePackages = "code.choi.ch01")
@SpringBootApplication
public class ChoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChoiApplication.class, args);
    }

}
