package ru.avevdokimov.springboot.test.restsearchservice.utils;

public interface StringConvertor {
    <T> T fromString(String src, Class<T> type);
    String toString(Object obj);
}
