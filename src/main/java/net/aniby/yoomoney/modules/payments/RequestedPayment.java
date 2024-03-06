package net.aniby.yoomoney.modules.payments;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class RequestedPayment {
    @SerializedName("status")
    private String status;
    @SerializedName("error")
    private String error;
    @SerializedName("money_source")
    private MoneySource moneySource;
    @SerializedName("request_id")
    private String requestId;
    @SerializedName("contract_amount")
    private double contractAmount;
    @SerializedName("balance")
    private double balance;
    @SerializedName("recipient_account_status")
    private String recipientAccountStatus;
    @SerializedName("recipient_account_type")
    private String recipientAccountType;
    @SerializedName("account_unblock_uri")
    private String accountUnblockURI;
    @SerializedName("ext_action_uri")
    private String extActionURI;
}
