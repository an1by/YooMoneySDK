package net.aniby.yoomoney.requests;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Builder
@Getter
public class ShopPaymentRequest extends MethodRequest {
    @BodyRequestField("pattern_id")
    private final String patternId = "scid";
    @BodyRequestField("formObjectMap")
    private final HashMap<String, String> map;
}