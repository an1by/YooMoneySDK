package net.aniby.yoomoney.client;

import com.google.gson.Gson;
import lombok.Getter;
import net.aniby.yoomoney.modules.account.AccountInfo;
import net.aniby.yoomoney.modules.operation.OperationDetails;
import net.aniby.yoomoney.modules.operation.OperationHistory;
import net.aniby.yoomoney.requests.OperationHistoryRequest;
import net.aniby.yoomoney.utils.Constant;
import okhttp3.*;

import java.io.IOException;
import java.net.Proxy;

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

    public class Methods {
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
            RequestBody formBody = methodRequest.build();
            return request(formBody, Constant.Host.OPERATION_HISTORY, OperationHistory.class);
        }

        public OperationDetails getOperationDetails(String operationId) throws IOException {
            RequestBody formBody = new FormBody.Builder()
                    .add("operation_id", operationId)
                    .build();
            return request(formBody, Constant.Host.OPERATION_DETAILS, OperationDetails.class);
        }
    }
}
