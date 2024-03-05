package net.aniby.yoomoney.modules.notification;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SecuredIncomingNotification extends IncomingNotification {
    // Only HTTPS
    @SerializedName("lastname")
    public String lastName;
    @SerializedName("firstname")
    public String firstName;
    @SerializedName("fathersname")
    public String fathersName;
    @SerializedName("email")
    public String email;
    @SerializedName("phone")
    public String phone;

    @SerializedName("city")
    public String city;
    @SerializedName("street")
    public String street;
    @SerializedName("building")
    public String building;
    @SerializedName("suite")
    public String suite;
    @SerializedName("flat")
    public String flat;
    @SerializedName("zip")
    public String zip;

    public SecuredIncomingNotification(String notificationType, String operationId, double amount, double withdrawAmount, String currency, String datetime, String sender, boolean codepro, String label, String sha1Hash, boolean test_notification, boolean unaccepted, String lastName, String firstName, String fathersName, String email, String phone, String city, String street, String building, String suite, String flat, String zip) {
        super(notificationType, operationId, amount, withdrawAmount, currency, datetime, sender, codepro, label, sha1Hash, test_notification, unaccepted);
        this.lastName = lastName;
        this.firstName = firstName;
        this.fathersName = fathersName;
        this.email = email;
        this.phone = phone;
        this.city = city;
        this.street = street;
        this.building = building;
        this.suite = suite;
        this.flat = flat;
        this.zip = zip;
    }
}
