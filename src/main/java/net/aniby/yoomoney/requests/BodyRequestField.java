package net.aniby.yoomoney.requests;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface BodyRequestField {
    String value();
}