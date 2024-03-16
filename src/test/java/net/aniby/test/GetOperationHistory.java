package net.aniby.test;

import net.aniby.yoomoney.client.YooMoneyClient;
import net.aniby.yoomoney.modules.operations.Operation;
import net.aniby.yoomoney.requests.OperationHistoryRequest;

import java.io.IOException;

public class GetOperationHistory {
    public static void main(String[] args) throws IOException {
        YooMoneyClient client = new YooMoneyClient(
                "",
                ""
        );
        OperationHistoryRequest request = OperationHistoryRequest.builder()
                .records(10)
                .type("deposition")
                .build();
        for (Operation operation : client.getOperationHistory(request).getOperations()) {
            System.out.println(operation.toString());
        }
    }
}
