package net.aniby.yoomoney.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProcessPaymentRequest extends MethodRequest{
    @BodyRequestField("request_id")
    private String requestId;
    @BodyRequestField("money_source")
    private String moneySource;
    @BodyRequestField("csc")
    private String CSC;
    @BodyRequestField("ext_auth_success_uri")
    private String extAuthSuccessURI;
    @BodyRequestField("ext_auth_fail_uri")
    private String extAuthFailURI;
}
