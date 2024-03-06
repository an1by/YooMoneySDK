package net.aniby.yoomoney.requests;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class P2PPaymentRequest extends MethodRequest {
    @BodyRequestField("pattern_id")
    private final String patternId = "p2p";
    @BodyRequestField("to")
    private String to;
    @BodyRequestField("amount")
    private boolean amount;
    @BodyRequestField("amount_due")
    private boolean amountDue;
    @BodyRequestField("comment")
    private String comment;
    @BodyRequestField("message")
    private String message;
    @BodyRequestField("label")
    private String label = null;
}