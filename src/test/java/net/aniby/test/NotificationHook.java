package net.aniby.test;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import net.aniby.yoomoney.modules.notifications.IncomingNotification;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class NotificationHook implements HttpHandler {
    private final Consumer<IncomingNotification> consumer;

    public NotificationHook(Consumer<IncomingNotification> consumer) {
        this.consumer = consumer;
    }

    private Map<String, String> toMap(InputStream is) throws MalformedURLException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        String url = "http://localhost/" + br.lines().collect(Collectors.joining(System.lineSeparator()));
        String decoded = URLDecoder.decode(url, StandardCharsets.UTF_8).replace("http://localhost/", "");
        return Arrays.stream(decoded.split("&"))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(
                        e -> e[0], e -> e[1]
                ));
    }

    @Override
    public void handle(HttpExchange exchange) {
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
        try {
            Map<String, String> body = toMap(stream);
            consumer.accept(IncomingNotification.get(body));

            exchange.sendResponseHeaders(200, 0);
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }
}
