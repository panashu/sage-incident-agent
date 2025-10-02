package com.sage;

import com.microsoft.azure.functions.*;
import java.util.logging.Logger;

public class App {
    @FunctionName("SageIncidentCheck")
    public void run(
        @TimerTrigger(name = "timerInfo", schedule = "0 */5 * * * *") String timerInfo,
        final ExecutionContext context) {

        Logger logger = context.getLogger();
        logger.info("Running SAGE check at: " + java.time.LocalDateTime.now());

        // Step 1: Fetch logs
        String logs = LogFetcher.fetchRecentErrors();

        // Step 2: Analyze
        Diagnosis diagnosis = Analyzer.analyzeLogs(logs);

        if (diagnosis != null) {
            // Step 3: Get runbook / probable solution
            String solution = RunbookService.findSolution(diagnosis);

            // Step 4: Create incident in ServiceNow
            IncidentServiceNowClient.createIncident(diagnosis, solution);

            // Step 5: Optional remediation
            Remediator.restartPodIfNeeded(diagnosis);
        }
    }
}
