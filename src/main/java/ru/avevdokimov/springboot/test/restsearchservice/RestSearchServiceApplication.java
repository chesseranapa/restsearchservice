package ru.avevdokimov.springboot.test.restsearchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestSearchServiceApplication {



    public static void main(String[] args) {
        SpringApplication.run(RestSearchServiceApplication.class, args);

        System.out.println("start");
        //String respons = HelperForConnection.getResultForGetReguest(url);
        //System.out.println(respons);


    }

}
