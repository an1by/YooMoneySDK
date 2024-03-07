package net.aniby.yoomoney.modules.notifications;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface NotificationField {
    String value();
}
