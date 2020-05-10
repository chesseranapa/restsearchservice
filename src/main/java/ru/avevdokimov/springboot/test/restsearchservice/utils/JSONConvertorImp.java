package ru.avevdokimov.springboot.test.restsearchservice.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JSONConvertorImp implements StringConvertor{
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONConvertorImp.class);
    public static final String ERROR_PARSING_FROM_JSON_MSG = "Error parsing from json!";
    public static final String ERROR_PARSING_TO_JSON_MSG = "Error parsing to json!";

    public  <T> T fromString(String src, Class<T> type) {
        try {
            T result = new ObjectMapper().readValue(src, type);
            return result;
        } catch (IOException e) {
            LOGGER.error(ERROR_PARSING_FROM_JSON_MSG, e);
            return null;
        }
    }

    public  String toString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error(ERROR_PARSING_TO_JSON_MSG, e);
            return "error";
        }
    }
}
