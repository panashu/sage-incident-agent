package com.sage;

import com.sage.models.Diagnosis;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.entity.*;
import org.apache.http.util.EntityUtils;

public class IncidentServiceNowClient {
    public static void createIncident(Diagnosis diagnosis, String solution) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost("<SERVICENOW_INSTANCE>/api/now/table/incident");
            String json = "{ \"short_description\": \"" + diagnosis.getIssue() +
                          "\", \"description\": \"" + diagnosis.getDetails() +
                          "\\nProbable fix: " + solution + "\" }";

            request.setEntity(new StringEntity(json));
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Authorization", "Basic <BASE64_CREDENTIALS>");

            CloseableHttpResponse response = client.execute(request);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
