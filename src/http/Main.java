package http;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;
import jdk.incubator.http.WebSocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        URI uri = new URI("https://sim3.alphapoint.com:8400/ajax/v1/GetTrades");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(uri)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyProcessor.fromString("{\"ins\": \"BTCUSD\", \"startIndex\": -1, \"count\": 10}"))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());

        System.out.println(response.version());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        HttpRequest requestGoogle = HttpRequest
                .newBuilder()
                .uri(new URI("https://google.com/"))
                .GET()
                .build();

        HttpResponse<String> responseGoogleWithoutRedirect = client.send(requestGoogle, HttpResponse.BodyHandler.asString());

        System.out.println(responseGoogleWithoutRedirect.version());
        System.out.println(responseGoogleWithoutRedirect.statusCode());
        System.out.println(responseGoogleWithoutRedirect.body());


        HttpClient clientGoogle = HttpClient
                .newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpResponse<String> responseGoogle = clientGoogle.send(requestGoogle, HttpResponse.BodyHandler.asString());

        System.out.println(responseGoogle.version());
        System.out.println(responseGoogle.statusCode());
        System.out.println(responseGoogle.body());


        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(new URI("https://turini.github.io/livro-java-9/books.csv"))
                .GET()
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandler.asString());

        Stream<String> pharses = Stream.of(httpResponse.body().split("\n"));
        Stream<String> word = pharses.flatMap(c -> Stream.of(c.split(",")));
        word.forEach(System.out::println);

        httpClient = HttpClient.newHttpClient();
        httpRequest = HttpRequest
                .newBuilder()
                .uri(new URI("https://turini.github.io/livro-java-9/books.csv"))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> responseAsync = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandler.asString());
        responseAsync.whenComplete((r, t) -> System.out.println(r.body()));

        System.out.println("Acabou");

        httpClient.send(httpRequest, HttpResponse.BodyHandler.asFile(Paths.get("books.csv")));
        int scanner = new Scanner(System.in).nextInt();
    }
}

class WSListener implements WebSocket.Listener {
    @Override
    public CompletionStage<?> onText(WebSocket webSocket,
                                     CharSequence message, WebSocket.MessagePart part) {
        webSocket.request(1);
        return CompletableFuture.completedFuture(message).thenAccept(System.out::println);
    }
}