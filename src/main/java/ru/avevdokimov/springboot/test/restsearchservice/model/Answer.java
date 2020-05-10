package ru.avevdokimov.springboot.test.restsearchservice.model;

public class Answer {
    private String tag;
    private long total;
    private long answered;

    public Answer() {
    }

    public Answer(String tag, long total, long answered) {
        this.tag = tag;
        this.total = total;
        this.answered = answered;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getAnswered() {
        return answered;
    }

    public void setAnswered(long answered) {
        this.answered = answered;
    }
}
