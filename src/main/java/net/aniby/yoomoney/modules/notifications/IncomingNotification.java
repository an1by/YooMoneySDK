package net.aniby.yoomoney.modules.notifications;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IncomingNotification {
    // HTTP / HTTPS
    @SerializedName("notification_type")
    private String notificationType;
    @SerializedName("operation_id")
    private String operationId;
    @SerializedName("amount")
    private double amount;
    @SerializedName("withdraw_amount")
    private double withdrawAmount;
    @SerializedName("currency")
    private String currency;
    @SerializedName("datetime")
    private String datetime;
    @SerializedName("sender")
    private String sender;
    @SerializedName("codepro")
    private boolean codePro;
    @SerializedName("label")
    private String label;
    @SerializedName("sha1_hash")
    private String sha1Hash;
    @SerializedName("test_notification")
    private boolean testNotification;
    @SerializedName("unaccepted")
    private boolean unaccepted;

    public boolean verify(String notificationSecret) {
        String string = String.format(
                "%s&%s&%s&%s&%s&%s&%s&%s&%s",
                notificationType, operationId, amount, currency, datetime, sender, codePro, notificationSecret, label
        );
        return Objects.equals(sha1Hash, DigestUtils.sha1Hex(string));
    }
}
