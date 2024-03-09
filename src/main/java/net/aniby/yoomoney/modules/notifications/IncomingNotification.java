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
    public String notificationType;
    @NotificationField("operation_id")
    public String operationId;
    @NotificationField("amount")
    public double amount;
    @NotificationField("withdraw_amount")
    public double withdrawAmount;
    @NotificationField("currency")
    public String currency;
    @NotificationField("datetime")
    public String datetime;
    @NotificationField("sender")
    public String sender;
    @NotificationField("codepro")
    public boolean codePro = false;
    @NotificationField("label")
    public String label = "";
    @NotificationField("sha1_hash")
    public String sha1Hash;
    @NotificationField("test_notification")
    public boolean testNotification = false;
    @NotificationField("unaccepted")
    public boolean unaccepted = false;

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

                Class<?> type = field.getType();
                if (map.containsKey(name) && !map.get(name).isEmpty()) {
                    String value = map.get(name);
                    if (type == boolean.class)
                        field.set(notification, Boolean.parseBoolean(value));
                    else if (type == double.class)
                        field.set(notification, Double.parseDouble(value));
                    else if (type == String.class)
                        field.set(notification, value);
                } else {
                    if (type == boolean.class)
                        field.set(notification, false);
                    else if (type == double.class)
                        field.set(notification, 0);
                    else if (type == String.class)
                        field.set(notification, "");
                }
            }
        }
        return notification;
    }
}
