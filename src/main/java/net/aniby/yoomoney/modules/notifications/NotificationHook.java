package net.aniby.yoomoney.modules.notifications;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NotificationHook implements HttpHandler {
    private final Consumer<IncomingNotification> consumer;

    public NotificationHook(Consumer<IncomingNotification> consumer) {
        this.consumer = consumer;
    }

    private List<String> toStringList(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        return br.lines().collect(Collectors.toList());
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!exchange.getRequestMethod().equals("POST"))
            return;
        Headers headers = exchange.getRequestHeaders();
        if (!headers.containsKey("Content-Type"))
            return;
        String contentType = !headers.get("Content-Type").isEmpty() ? headers.get("Content-Type").get(0) : null;
        if (!Objects.equals(contentType, "application/x-www-form-urlencoded"))
            return;
        InputStream stream = exchange.getRequestBody();
        if (stream == null)
            return;
        List<String> list;
        try {
            list = toStringList(stream);
        } catch (IOException e) {
            return;
        }
        Map<String, String> body = list.stream()
                .map(s -> s.split("=", 2))
                .filter(e -> e.length == 2)
                .collect(Collectors.toMap(
                e -> e[0], e -> e[1]
        ));

        try {
            consumer.accept(IncomingNotification.get(body));
        } catch (IllegalAccessException e) {
            return;
        }
    }
}
