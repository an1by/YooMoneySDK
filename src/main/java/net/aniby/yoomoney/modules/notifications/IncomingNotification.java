package net.aniby.yoomoney.modules.notifications;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.codec.digest.DigestUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class IncomingNotification {
    // HTTP / HTTPS
    @NotificationField("notification_type")
    private String notificationType;
    @NotificationField("operation_id")
    private String operationId;
    @NotificationField("amount")
    private double amount;
    @NotificationField("withdraw_amount")
    private double withdrawAmount;
    @NotificationField("currency")
    private String currency;
    @NotificationField("datetime")
    private String datetime;
    @NotificationField("sender")
    private String sender;
    @NotificationField("codepro")
    private boolean codePro = false;
    @NotificationField("label")
    private String label = "";
    @NotificationField("sha1_hash")
    private String sha1Hash;
    @NotificationField("test_notification")
    private boolean testNotification = false;
    @NotificationField("unaccepted")
    private boolean unaccepted = false;

    // Only HTTPS
    @NotificationField("lastname")
    public String lastName = null;
    @NotificationField("firstname")
    public String firstName = null;
    @NotificationField("fathersname")
    public String fathersName = null;

    @NotificationField("email")
    public String email = null;

    @NotificationField("phone")
    public String phone = null;

    @NotificationField("city")
    public String city = null;
    @NotificationField("street")
    public String street = null;
    @NotificationField("building")
    public String building = null;
    @NotificationField("suite")
    public String suite = null;
    @NotificationField("flat")
    public String flat = null;
    @NotificationField("zip")
    public String zip = null;

    public boolean isSecured() {
        return lastName != null || email != null || phone != null || city != null;
    }

    public boolean verify(String notificationSecret) {
        String string = String.format(
                "%s&%s&%s&%s&%s&%s&%s&%s&%s",
                notificationType, operationId, amount, currency, datetime, sender, codePro, notificationSecret, label
        );
        return Objects.equals(sha1Hash, DigestUtils.sha1Hex(string));
    }

    public static IncomingNotification get(Map<String, String> map) throws IllegalAccessException {
        IncomingNotification notification = new IncomingNotification();

        Field[] fields = notification.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotificationField.class)) {
                field.setAccessible(true);
                String name = field.getAnnotation(NotificationField.class).value();
                if (map.containsKey(name)) {
                    field.set(notification, map.get(name));
                }
            }
        }

        return notification;
    }
}
