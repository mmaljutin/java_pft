package ru.stqa.pft.mantis.appmanager;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;


public class BugifyHelper {

    private ApplicationManager app;

    public BugifyHelper(ApplicationManager app) {
        this.app = app;
    }

    public String getIssueByID(int issueId) {
        RestAssured.authentication = RestAssured.basic(app.getProperty("bugify.API"), app.getProperty("bugify.password"));
        String json = RestAssured.get(app.getProperty("bugify.url") + issueId + ".json").asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        JsonObject issue = issues.getAsJsonArray().get(0).getAsJsonObject();
        return issue.get("state_name").getAsString();
    }
}
