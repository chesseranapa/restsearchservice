package ru.avevdokimov.springboot.test.restsearchservice.actions;

import ru.avevdokimov.springboot.test.restsearchservice.utils.HelperForConnection;
import ru.avevdokimov.springboot.test.restsearchservice.utils.Params;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class RequestTask implements Callable {
    private String tag;
    private List<Params> listTags;
    public RequestTask(String tag, List<Params> listTags) {
        this.tag = tag;
        this.listTags = new ArrayList<>();
        this.listTags.addAll(listTags);
    }

    @Override
    public Params call() throws Exception {
        listTags.add(new Params("tagged", tag));
        System.out.println("tagged: " +  tag);
        String url = HelperForConnection.getSearchURL(listTags);
        return new Params(tag, HelperForConnection.getResultForGetReguest(url));
    }
}
