package net.aniby.yoomoney.modules.forms;

import lombok.Builder;
import lombok.Getter;
import net.aniby.yoomoney.requests.BodyRequestField;
import net.aniby.yoomoney.requests.MethodRequest;
import net.aniby.yoomoney.utils.Constant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

@Getter
@Builder
public class QuickPay extends MethodRequest {
    @BodyRequestField("receiver")
    private final String receiver;
    @BodyRequestField("quickpay-form")
    private final String quickPayForm = "button";
    @BodyRequestField("paymentType")
    private final String paymentType; // PC / AC
    @BodyRequestField("sum")
    private final double amount;
    @BodyRequestField("label")
    private String label = null;
    @BodyRequestField("successURL")
    private String successURL = null;

    public @Nullable String create(OkHttpClient client) throws IOException {
        Request request = new Request.Builder()
                .url(Constant.Host.QUICKPAY_CONFIRM)
                .post(this.buildRequest())
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            return null;
        }
        ResponseBody body = response.body();
        return body == null ? null : body.string();
    }
}
