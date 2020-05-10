package ru.avevdokimov.springboot.test.restsearchservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Respons {
    private List<ResponsItem> items;
    public Respons() {
    }

    public List<ResponsItem> getItems() {
        return items;
    }

    public void setItems(List<ResponsItem> items) {
        this.items = items;
    }
}
