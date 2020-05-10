package ru.avevdokimov.springboot.test.restsearchservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.avevdokimov.springboot.test.restsearchservice.actions.RequestTaskExecutor;
import ru.avevdokimov.springboot.test.restsearchservice.actions.views.BaseHtmlPage;
import ru.avevdokimov.springboot.test.restsearchservice.model.Answer;
import ru.avevdokimov.springboot.test.restsearchservice.model.Respons;
import ru.avevdokimov.springboot.test.restsearchservice.model.ResponsItem;
import ru.avevdokimov.springboot.test.restsearchservice.utils.HelperForConnection;
import ru.avevdokimov.springboot.test.restsearchservice.utils.Params;
import ru.avevdokimov.springboot.test.restsearchservice.utils.StringConvertor;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class MainPageController {

    @Autowired
    private StringConvertor stringConvertor;
    private static final Logger LOGGER = LoggerFactory.getLogger(MainPageController.class);

    @GetMapping
    public String showStatus(@RequestParam(value = "tag", required = false) List<String> tag) {
        BaseHtmlPage baseHtmlPage = new BaseHtmlPage();
        StringBuffer rowRespons = new StringBuffer("");
        baseHtmlPage.addH2("List finded tags");
        if (null == tag) {
            baseHtmlPage.addTempText("no tags");
        } else {
            for (int i = 0; i < tag.size(); i++) {
                baseHtmlPage.addTempText(tag.get(i));
                baseHtmlPage.addEmptyLine(1);
            }
            Respons responsServer;
            List<Params> listRespons = RequestTaskExecutor.getResultManyTask(tag, 2);
            List<Answer> listAnswer = new ArrayList<>();
            long answered;
            for (int i = 0; i < listRespons.size(); i++) {
                answered = 0L;
                responsServer = stringConvertor.fromString(listRespons.get(i).getValue(), Respons.class);
                List<ResponsItem> itemList = responsServer.getItems();
                for (int j = 0; j < itemList.size(); j++) {
                    if (itemList.get(j).isIs_answered()) {
                        answered++;
                    }
                    rowRespons.append(stringConvertor.toString(itemList.get(j)) + "<br><br>");
                }
                rowRespons.append("<br><br>");
                listAnswer.add(new Answer(listRespons.get(i).getKey(), itemList.size(), answered));
                //rowRespons.append(listRespons.get(i)+ "<br><br>");
            }

            baseHtmlPage.addEmptyLine(1);
            baseHtmlPage.addH2("complex info");

            StringBuffer forComplex = new StringBuffer("");


            for (int j =0; j < listAnswer.size(); j++) {
                forComplex.append(stringConvertor.toString(listAnswer.get(j)));
            }
            baseHtmlPage.addTextArea(forComplex.toString(), 70, 10);
            baseHtmlPage.addEmptyLine(3);
            baseHtmlPage.addH2("Data for report");
            baseHtmlPage.addTempText(rowRespons.toString());
        }


        return baseHtmlPage.getPage();
    }
}
