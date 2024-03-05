package net.aniby.yoomoney.modules.operation;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperationDetails {
    @SerializedName("error")
    private String error;
    @SerializedName("operation_id")
    private String operationId;
    @SerializedName("status")
    private String status;
    @SerializedName("patternId")
    private String patternId;
    @SerializedName("direction")
    private String direction;
    @SerializedName("amount")
    private double amount;
    @SerializedName("amount_due")
    private double amountDue;
    @SerializedName("fee")
    private double fee;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("title")
    private String title;
    @SerializedName("sender")
    private String sender;
    @SerializedName("recipient")
    private String recipient;
    @SerializedName("recipient_type")
    private String recipient_type;
    @SerializedName("message")
    private String message;
    @SerializedName("comment")
    private String comment;
    @SerializedName("label")
    private String label;
    @SerializedName("details")
    private String details;
    @SerializedName("type")
    private String type;
    @SerializedName("digital_goods")
    private JsonObject digitalGoods;
}
