package com.sage;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.monitor.query.*;
import com.azure.monitor.query.models.*;

public class LogFetcher {
    public static String fetchRecentErrors() {
        LogsQueryClient client = new LogsQueryClientBuilder()
            .credential(new DefaultAzureCredentialBuilder().build())
            .buildClient();
        String query = "AppTraces | where SeverityLevel >= 3 | top 20 by TimeGenerated desc";
        LogsQueryResult result = client.queryWorkspace("<LOG_ANALYTICS_WORKSPACE_ID>", query, QueryTimeInterval.LAST_5_MINUTES);

        return result.getAllTables().toString();
    }
}
