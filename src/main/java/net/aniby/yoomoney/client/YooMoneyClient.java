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
import net.aniby.yoomoney.requests.ToAccountPaymentRequest;
import net.aniby.yoomoney.requests.ProcessPaymentRequest;
import net.aniby.yoomoney.requests.MerchantPaymentRequest;
import net.aniby.yoomoney.utils.Constant;
import okhttp3.*;

import java.io.IOException;
import java.net.Proxy;
import java.util.List;

public class YooMoneyClient {
    private final String accessToken;
    @Getter
    private final String clientId;
    private final OkHttpClient client;

    /**
     * Constructor for YooMoney client
     * @param clientId       Application's Client ID
     * @param accessToken    Application's access token
     * @see YooMoneyClient
     */
    public YooMoneyClient(String clientId, String accessToken) {
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.client = new OkHttpClient.Builder().build();
    }

    /**
     * Constructor for YooMoney client
     * @param clientId       Application's Client ID
     * @param accessToken    Application's access token
     * @param proxy          {@link Proxy} for {@link OkHttpClient}
     * @see YooMoneyClient
     */
    public YooMoneyClient(String clientId, String accessToken, Proxy proxy) {
        this.accessToken = accessToken;
        this.clientId = clientId;
        this.client = new OkHttpClient.Builder().proxy(proxy).build();
    }

    /**
     * Unique function of request creating for client's methods
     * @param formBody       Request body
     * @param url            Request url
     * @param _class         Class of retrieving T object
     * @return               {@link T} object
     * @throws IOException   If request exception occurred
     */
    <T> T request(RequestBody formBody, String url, Class<T> _class) throws IOException {
        Request request = new Request.Builder()
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .url(url)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        ResponseBody responseBody = response.body();
        if (responseBody == null)
            throw new IOException("Response body is null");
        String body = responseBody.string();
        if (body.isEmpty())
            throw new IOException("Response body is empty");

        T info = new Gson().fromJson(body, _class);
        response.close();
        return info;
    }

    /**
     * Getting information about the status of the
     * <a href="https://yoomoney.ru/docs/wallet/user-account/account-info">user account</a>
     * <br/>
     * <a href="https://yoomoney.ru/docs/wallet/using-api/authorization/protocol-rights">Required permissions:</a>
     * <code>account-info</code>
     * @return               Information about the status of the user account
     * @see AccountInfo
     * @throws IOException   If request exception occurred
     */
    public AccountInfo getAccountInfo() throws IOException {
        RequestBody formBody = new FormBody.Builder().build();
        return request(formBody, Constant.Host.ACCOUNT_INFO, AccountInfo.class);
    }

    /**
     * This method allows viewing the full or partial
     * <a href="https://yoomoney.ru/docs/wallet/user-account/operation-history">history of operations</a>
     * in page mode. History records are displayed in reverse chronological order (from most recent to oldest)
     * <br/>
     * <a href="https://yoomoney.ru/docs/wallet/using-api/authorization/protocol-rights">Required permissions:</a>
     * <code>operation-history</code>
     * @param requestBuilder {@link OperationHistoryRequest} builder
     * @return               Operation history
     * @see OperationHistory
     * @throws IOException   If request exception occurred
     */
    public OperationHistory getOperationHistory(OperationHistoryRequest requestBuilder) throws IOException {
        RequestBody formBody = requestBuilder.buildRequest();
        return request(formBody, Constant.Host.OPERATION_HISTORY, OperationHistory.class);
    }

    /**
     * Provides
     * <a href="https://yoomoney.ru/docs/wallet/user-account/operation-details">detailed information</a>
     * about a particular operation from the history
     * <br/>
     * <a href="https://yoomoney.ru/docs/wallet/using-api/authorization/protocol-rights">Required permissions:</a>
     * <code>operation-details</code>
     * @param operationId    ID of needed operation
     * @return               Operation history
     * @see OperationDetails
     * @throws IOException   If request exception occurred
     */
    public OperationDetails getOperationDetails(String operationId) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("operation_id", operationId)
                .build();
        return request(formBody, Constant.Host.OPERATION_DETAILS, OperationDetails.class);
    }

    /**
     * Creates a
     * <a href="https://yoomoney.ru/docs/wallet/process-payments/request-payment">payment</a>,
     * checks parameters and verifies that the merchant can accept the payment,
     * or that funds can be transferred to a YooMoney user account
     * <br/>
     * <a href="https://yoomoney.ru/docs/wallet/using-api/authorization/protocol-rights">Required permissions:</a>
     * <code>payment.to-pattern</code> (“Payment Pattern”) or <code>payment-shop</code>
     * @param requestBuilder {@link MerchantPaymentRequest} builder
     * @return               Information about requested payment
     * @see RequestedPayment
     * @throws IOException   If request exception occurred
     */
    public RequestedPayment requestMerchantPayment(MerchantPaymentRequest requestBuilder) throws IOException {
        RequestBody formBody = requestBuilder.buildRequest();
        return request(formBody, Constant.Host.REQUEST_PAYMENT, RequestedPayment.class);
    }

    /**
     * Creates a
     * <a href="https://yoomoney.ru/docs/wallet/process-payments/request-payment">payment</a>,
     * checks parameters and verifies that the merchant can accept the payment,
     * or that funds can be transferred to a YooMoney user account
     * <br/>
     * <a href="https://yoomoney.ru/docs/wallet/using-api/authorization/protocol-rights">Required permissions:</a>
     * <code>payment.to-account</code> (“payee ID”, “ID type”) or <code>payment-p2p</code>
     * @param requestBuilder {@link ToAccountPaymentRequest} builder
     * @return               Information about requested payment
     * @see RequestedPayment
     * @throws IOException   If request exception occurred
     */
    public RequestedPayment requestToAccountPayment(ToAccountPaymentRequest requestBuilder) throws IOException {
        RequestBody formBody = requestBuilder.buildRequest();
        return request(formBody, Constant.Host.REQUEST_PAYMENT, RequestedPayment.class);
    }

    /**
     * <a href="https://yoomoney.ru/docs/wallet/process-payments/process-payment">Confirms a payment</a>
     * that was created using the
     * {@link #requestMerchantPayment(MerchantPaymentRequest)}
     * or
     * {@link #requestToAccountPayment(ToAccountPaymentRequest)}
     * method.
     * Specifies the method for making the payment
     * @param requestBuilder {@link ProcessPaymentRequest} builder
     * @return               Information about processed payment
     * @see ProcessedPayment
     * @throws IOException   If request exception occurred
     */
    public ProcessedPayment processPayment(ProcessPaymentRequest requestBuilder) throws IOException {
        RequestBody formBody = requestBuilder.buildRequest();
        return request(formBody, Constant.Host.PROCESS_PAYMENT, ProcessedPayment.class);
    }

    /**
     * Creates <a href="https://yoomoney.ru/docs/payment-buttons/using-api/forms">YooMoney form</a>: set of fields with information about a transfer
     * @param requestBuilder {@link QuickPay} builder
     * @return               URL to quick pay form
     * @throws IOException   If request exception occurred
     */
    public String createQuickPayForm(QuickPay requestBuilder) throws IOException {
        AccountInfo info = this.getAccountInfo();
        if (info == null)
            return null;

        Request request = new Request.Builder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .url(Constant.Host.QUICKPAY_CONFIRM)
                .post(requestBuilder.buildRequest())
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            return null;
        }
        ResponseBody body = response.body();
        response.close();
        if (body == null)
            throw new IOException("Response body is null");
        String uri = response.toString();
        if (uri.isEmpty())
            throw new IOException("Response body is empty");
        return uri.substring(uri.indexOf("url=") + 4, uri.length() - 1);
    }
}
