package com.sage.models;

public class Diagnosis {
    private String issue;
    private String details;

    public Diagnosis(String issue, String details) {
        this.issue = issue;
        this.details = details;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
}
