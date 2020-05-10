package ru.avevdokimov.springboot.test.restsearchservice.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppSettings {
    @Value("${baseHostUrl}")
    public String baseHostUrl;
}
