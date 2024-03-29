package net.aniby.yoomoney.requests;

import okhttp3.FormBody;
import okhttp3.RequestBody;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class MethodRequest {
    public RequestBody buildRequest() {
        FormBody.Builder builder = new FormBody.Builder();

        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BodyRequestField.class)) {
                field.setAccessible(true);
                BodyRequestField requestField = field.getAnnotation(BodyRequestField.class);

                String name = requestField.value();
                try {
                    Object value = field.get(this);
                    if (value !=  null) {
                        if (Objects.equals("formObjectMap", name)) {
                            ((HashMap<String, String>) value).forEach(builder::add);
                        } else if (value instanceof List<?> list) {
                            list.forEach(v -> builder.add(
                                    name, String.valueOf(v)
                            ));
                        }else {
                            builder.add(name, String.valueOf(value));
                        }
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.build();
    }
}
