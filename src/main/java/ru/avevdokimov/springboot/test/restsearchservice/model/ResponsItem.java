package ru.avevdokimov.springboot.test.restsearchservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsItem {
    private long question_id;
    private boolean is_answered;
    private List<String> tags;

    public ResponsItem() {

    }

    public ResponsItem(long question_id, boolean is_answered, List<String> tags) {
        this.question_id = question_id;
        this.is_answered = is_answered;
        this.tags = tags;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public boolean isIs_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


}
