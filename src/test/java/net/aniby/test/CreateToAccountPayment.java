package net.aniby.test;

import net.aniby.yoomoney.client.YooMoneyClient;
import net.aniby.yoomoney.modules.account.AccountInfo;
import net.aniby.yoomoney.modules.payments.ProcessedPayment;
import net.aniby.yoomoney.modules.payments.RequestedPayment;
import net.aniby.yoomoney.requests.ProcessPaymentRequest;
import net.aniby.yoomoney.requests.ToAccountPaymentRequest;

import java.io.IOException;

public class CreateToAccountPayment {
    public static void main(String[] args) throws IOException {
        YooMoneyClient client = new YooMoneyClient(
                "",
                ""
        );
        RequestedPayment requestedPayment = client.requestToAccountPayment(
                ToAccountPaymentRequest.builder()
                        .to("4100118142469010")
                        .amount(100)
                        .comment("Thank you for API!")
                        .build()
        );
        ProcessedPayment processedPayment = client.processPayment(
                new ProcessPaymentRequest(requestedPayment.getRequestId())
        );
        System.out.println(processedPayment.toString());
    }
}
