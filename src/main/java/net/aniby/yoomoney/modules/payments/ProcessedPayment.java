package net.aniby.yoomoney.modules.payments;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import net.aniby.yoomoney.modules.goods.DigitalGoods;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ProcessedPayment {
    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String error;
    @SerializedName("payment_id")
    private String paymentId;
    @SerializedName("balance")
    private double balance;
    @SerializedName("invoice_id")
    private String invoiceId;
    @SerializedName("payer")
    private String payer;
    @SerializedName("payee")
    private String payee;
    @SerializedName("credit_amount")
    private double creditAmount;
    @SerializedName("account_unblock_uri")
    private String accountUnblockURI;
    @SerializedName("acs_uri")
    private String acsURI;
    @SerializedName("acs_params")
    private JsonObject acsParams;
    @SerializedName("next_retry")
    private long nextRetry;
    @SerializedName("digital_goods")
    private DigitalGoods digitalGoods;
}
