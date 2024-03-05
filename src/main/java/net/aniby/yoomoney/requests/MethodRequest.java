package net.aniby.yoomoney.requests;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.lang.reflect.Field;

public class MethodRequest {
    public RequestBody build() {
        FormBody.Builder builder = new FormBody.Builder();

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BodyRequestField.class)) {
                field.setAccessible(true);
                BodyRequestField requestField = field.getAnnotation(BodyRequestField.class);

                String name = requestField.name();
                try {
                    builder.add(name, String.valueOf(field.get(this)));
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.build();
    }
}
