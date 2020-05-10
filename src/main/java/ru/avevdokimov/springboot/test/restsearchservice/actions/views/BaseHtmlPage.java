package ru.avevdokimov.springboot.test.restsearchservice.actions.views;

public class BaseHtmlPage {
    private String head;
    private String body;

    public String getTempText() {
        return tempText;
    }

    public void setTempText(String tempText) {
        this.tempText = tempText;
    }

    public void addTempText(String text) {
        if (null == tempText) {
            tempText = text;
        } else {
            tempText = tempText + text;
        }
    }

    public void addH2(String text) {
        if (null == tempText) {
            tempText = "";
        }
        tempText = tempText + "<h2>" + text + "</h2>";
    }

    public void addTextArea(String text, int col, int row) {
        if (null == tempText) {
            tempText = "";
        }
        tempText = tempText + "<textarea rows=\"" + row + "\" cols=\"" + col +"\">";
        tempText = tempText + text;
        tempText = tempText +  "</textarea>";

    }

    public void addEmptyLine(int count) {
        if (null == tempText) {
            tempText = "";
        }
        for (int i = 0; i < count; i++) {
            tempText = tempText + "<br>";
        }
    }

    private String tempText;

    public BaseHtmlPage() {
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = "<head>" + head + "</head>";
    }

    public String getBody() {
        return body;
    }

    public void setBody() {
        this.body = "<body>" + tempText + "</body>";
    }

    public String getPage() {
        if (null == head) {
            head = "<head></head>";
        }
        if (null == body) {
            if (null == tempText) {
                body = "<body></body>";
            } else {
                body = "<body>" + tempText + "</body>";
            }
        }

        return head + body;
    }


}
