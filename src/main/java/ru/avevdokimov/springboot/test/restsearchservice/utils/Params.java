package ru.avevdokimov.springboot.test.restsearchservice.utils;

public class Params {
    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Params(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
