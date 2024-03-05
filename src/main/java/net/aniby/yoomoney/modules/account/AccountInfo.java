package net.aniby.yoomoney.modules.account;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class AccountInfo {
    @SerializedName("account")
    private final String account;
    @SerializedName("balance")
    private final double balance;
    @SerializedName("currency")
    private final String currency;
    @SerializedName("account_status")
    private final String accountStatus;
    @SerializedName("account_type")
    private final String accountType;
    @SerializedName("balance_details")
    private final BalanceDetails balanceDetails;
    @SerializedName("cards_linked")
    private final List<LinkedCard> cardsLinked;
}
