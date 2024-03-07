package net.aniby.yoomoney.client;

import com.google.gson.Gson;
import lombok.Getter;
import net.aniby.yoomoney.modules.account.AccountInfo;
import net.aniby.yoomoney.modules.forms.QuickPay;
import net.aniby.yoomoney.modules.operations.OperationDetails;
import net.aniby.yoomoney.modules.operations.OperationHistory;
import net.aniby.yoomoney.modules.payments.ProcessedPayment;
import net.aniby.yoomoney.modules.payments.RequestedPayment;
import net.aniby.yoomoney.requests.OperationHistoryRequest;
import net.aniby.yoomoney.requests.P2PPaymentRequest;
import net.aniby.yoomoney.requests.ProcessPaymentRequest;
import net.aniby.yoomoney.requests.ShopPaymentRequest;
import net.aniby.yoomoney.utils.Constant;
import okhttp3.*;

import java.io.IOException;
import java.net.Proxy;
import java.util.List;

public class YooMoneyClient {
    private String accessToken = null;
    @Getter
    private final String clientId;
    private final OkHttpClient client;

    public YooMoneyClient(String clientId, String accessToken) {
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.client = new OkHttpClient.Builder().build();
    }

    public YooMoneyClient(String clientId, String accessToken, Proxy proxy) {
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.client = new OkHttpClient.Builder().proxy(proxy).build();
    }

    <T> T request(RequestBody formBody, String url, Class<T> tClass) throws IOException {
        Request request = new Request.Builder()
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .url(url)
                .post(formBody)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            return null;
        }
        String body = response.body().string();
        T info = new Gson().fromJson(body, tClass);
        response.close();
        return info;
    }
    public AccountInfo getAccountInfo() throws IOException {
        RequestBody formBody = new FormBody.Builder().build();
        return request(formBody, Constant.Host.ACCOUNT_INFO, AccountInfo.class);
    }

    public OperationHistory getOperationHistory(OperationHistoryRequest methodRequest) throws IOException {
        RequestBody formBody = methodRequest.buildRequest();
        return request(formBody, Constant.Host.OPERATION_HISTORY, OperationHistory.class);
    }

    public OperationDetails getOperationDetails(String operationId) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("operation_id", operationId)
                .build();
        return request(formBody, Constant.Host.OPERATION_DETAILS, OperationDetails.class);
    }

    public RequestedPayment requestShopPayment(ShopPaymentRequest paymentRequest) throws IOException {
        RequestBody formBody = paymentRequest.buildRequest();
        return request(formBody, Constant.Host.REQUEST_PAYMENT, RequestedPayment.class);
    }

    public RequestedPayment requestP2PPayment(P2PPaymentRequest paymentRequest) throws IOException {
        RequestBody formBody = paymentRequest.buildRequest();
        return request(formBody, Constant.Host.REQUEST_PAYMENT, RequestedPayment.class);
    }

    public ProcessedPayment processPayment(ProcessPaymentRequest paymentRequest) throws IOException {
        RequestBody formBody = paymentRequest.buildRequest();
        return request(formBody, Constant.Host.PROCESS_PAYMENT, ProcessedPayment.class);
    }

    public String createQuickPayForm(double amount, List<String> paymentTypes, String label, String successURL) throws IOException {
        AccountInfo info = this.getAccountInfo();
        if (info == null)
            return null;
        return QuickPay.builder()
                .receiver(info.getAccount())
                .paymentTypes(paymentTypes)
                .amount(amount)
                .label(label)
                .successURL(successURL)
                .build().create(client);
    }

    public String createQuickPayForm(double amount, String label, String successURL) throws IOException {
        return createQuickPayForm(amount, List.of("AC", "PC"), label, successURL);
    }
    public String createQuickPayForm(double amount, String label) throws IOException {
        return createQuickPayForm(amount, List.of("AC", "PC"), label, null);
    }
    public String createQuickPayForm(double amount, List<String> paymentTypes, String label) throws IOException {
        return createQuickPayForm(amount, paymentTypes, label, null);
    }

    public String createQuickPayForm(double amount, List<String> paymentTypes) throws IOException {
        return createQuickPayForm(amount, paymentTypes, null, null);
    }

    public String createQuickPayForm(double amount) throws IOException {
        return createQuickPayForm(amount, List.of("AC", "PC"), null, null);
    }
}
