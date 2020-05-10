package ru.avevdokimov.springboot.test.restsearchservice.actions;

import ru.avevdokimov.springboot.test.restsearchservice.utils.Params;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class RequestTaskExecutor {
/*    public static void doManyTask(List<String> list, int countExecutors){
        ExecutorService es = Executors.newFixedThreadPool(countExecutors);
        List<Future<String>> listResults = new ArrayList<Future<String>>();
        for (String line: list) {
            listResults.add(es.submit(new RequestTask(line)));
        }

        for (int i = 0; i < listResults.size(); i++) {
            try {
                System.out.println(listResults.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }*/
    public static List<Params> getResultManyTask(List<String> listTags, int countExecutors){
        ExecutorService es = Executors.newFixedThreadPool(countExecutors);
        List<Future<Params>> listResults = new ArrayList<Future<Params>>();
        List<Params> list = new ArrayList<Params>();

        list.add(new Params("pagesize", "100"));
        list.add(new Params("order", "desc"));
        list.add(new Params("sort", "creation"));
        list.add(new Params("site", "stackoverflow"));

        for (String line: listTags) {
            listResults.add(es.submit(new RequestTask(line, list)));
        }
        List<Params> returnList = new ArrayList<Params>();

        for (int i = 0; i < listResults.size(); i++) {
            try {
                returnList.add(listResults.get(i).get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return returnList;
    }
}
