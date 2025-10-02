package com.sage;

import java.nio.file.*;
import org.json.*;

public class RunbookService {
    public static String findSolution(Diagnosis diagnosis) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("resources/runbooks.json")));
            JSONArray runbooks = new JSONArray(content);

            for (int i = 0; i < runbooks.length(); i++) {
                JSONObject rb = runbooks.getJSONObject(i);
                if (diagnosis.getIssue().contains(rb.getString("issue"))) {
                    return rb.getString("solution");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "No runbook found. Escalate to DevOps.";
    }
}
