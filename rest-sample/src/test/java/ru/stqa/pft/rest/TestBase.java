package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import java.io.IOException;
import java.util.Set;

public class TestBase {

    private Executor getExecutorForTestbase() {
        return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==","");
    }


    public String getStatusById(int issueId) throws IOException {
        String json = getExecutorForTestbase().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json",issueId))).returnContent().toString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issue =  new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        return issue.iterator().next().getStatus();
    }


    public boolean isIssueOpen(int issueId) throws IOException {
        String status = getStatusById(issueId);
        if(status.equals("Closed") || status.equals("Resolved")) {
            return false;
        } else
            return true;
    }


    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            System.out.println("Ignored because of issue http://demo.bugify.com/issues/" + issueId +
                    " status is not Resolved or Closed." + System.getProperty("line.separator") +
                    "Please change status of issue and run test again.");
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }
}