package net.aniby.yoomoney.modules.notifications;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    private boolean codePro;
    @NotificationField("label")
    private String label;
    @NotificationField("sha1_hash")
    private String sha1Hash;
    @NotificationField("test_notification")
    private boolean testNotification;
    @NotificationField("unaccepted")
    private boolean unaccepted;

    // Only HTTPS
    @NotificationField("lastname")
    public String lastName;
    @NotificationField("firstname")
    public String firstName;
    @NotificationField("fathersname")
    public String fathersName;

    @NotificationField("email")
    public String email;

    @NotificationField("phone")
    public String phone;

    @NotificationField("city")
    public String city;
    @NotificationField("street")
    public String street;
    @NotificationField("building")
    public String building;
    @NotificationField("suite")
    public String suite;
    @NotificationField("flat")
    public String flat;
    @NotificationField("zip")
    public String zip;

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
                field.set(notification, map.get(name));
            }
        }

        return notification;
    }
}
