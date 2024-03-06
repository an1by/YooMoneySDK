package net.aniby.yoomoney.modules.operations;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Operation {
    @SerializedName("operation_id")
    private String operationId;
    @SerializedName("status")
    private String status;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("title")
    private String title;
    @SerializedName("patternId")
    private String patternId;
    @SerializedName("direction")
    private String direction;
    @SerializedName("amount")
    private double amount;
    @SerializedName("label")
    private String label;
    @SerializedName("type")
    private String type;
}
