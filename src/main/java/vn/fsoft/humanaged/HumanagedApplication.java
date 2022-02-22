package vn.fsoft.humanaged;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import vn.fsoft.humanaged.controller.AccountController;
import vn.fsoft.humanaged.service.impl.AccountService;

@SpringBootApplication
public class HumanagedApplication {

    public static void main(String[] args) {
        SpringApplication.run(HumanagedApplication.class, args);

    }


}