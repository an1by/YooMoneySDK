package net.aniby.yoomoney.requests;

import lombok.Getter;

@Getter
public class ProcessPaymentRequest extends MethodRequest{
    @BodyRequestField("request_id")
    private final String requestId;
    @BodyRequestField("money_source")
    private final String moneySource;
    @BodyRequestField("csc")
    private final String CSC;
    @BodyRequestField("ext_auth_success_uri")
    private final String extAuthSuccessURI;
    @BodyRequestField("ext_auth_fail_uri")
    private final String extAuthFailURI;

    public ProcessPaymentRequest(String requestId, String moneySource, String csc, String extAuthSuccessURI, String extAuthFailURI) {
        this.requestId = requestId;
        this.moneySource = moneySource;
        this.CSC = csc;
        this.extAuthSuccessURI = extAuthSuccessURI;
        this.extAuthFailURI = extAuthFailURI;
    }

    public ProcessPaymentRequest(String requestId) {
        this.requestId = requestId;
        this.moneySource = "wallet";
        this.CSC = null;
        this.extAuthSuccessURI = null;
        this.extAuthFailURI = null;
    }
}
