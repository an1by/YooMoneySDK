package net.aniby.yoomoney.modules.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class BalanceDetails {
    @SerializedName("total")
    private final double total;
    @SerializedName("available")
    private final double available;
    @SerializedName("deposition_pending")
    private final double depositionPending;
    @SerializedName("blocked")
    private final double blocked;
    @SerializedName("debt")
    private final double debt;
    @SerializedName("hold")
    private final double hold;
}
