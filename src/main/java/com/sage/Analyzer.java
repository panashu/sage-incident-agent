package com.sage;

import com.sage.Models.Diagnosis;

public class Analyzer {
    public static Diagnosis analyzeLogs(String logs) {
        if (logs.contains("NullPointerException")) {
            return new Diagnosis("Application crash", "Java NullPointerException detected");
        }
        if (logs.contains("OutOfMemoryError")) {
            return new Diagnosis("Memory issue", "Heap space exhausted");
        }
        return null; // No critical issue
    }
}
