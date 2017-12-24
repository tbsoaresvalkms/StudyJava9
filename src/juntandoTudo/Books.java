package juntandoTudo;

import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import jdk.incubator.http.HttpResponse;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Books {
    public static List<Book> all() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://turini.github.io/livro-java-9/books.csv"))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandler.asString());

        String body = response.body();

        return Stream.of(body.split("\n"))
                .map(Books::create)
                .collect(Collectors.toList());
    }

    private static Book create(String line) {
        String[] split = line.split(",");
        return new Book(split[0], split[2], Category.valueOf(split[3]));
    }

    public static Optional<Book> findSimilar(Book book) throws IOException, InterruptedException {
        return Books.all()
                .stream()
                .filter(b -> b.getCategories().equals(book.getCategories()))
                .filter(b -> !b.getAuthor().equals(book.getAuthor()))
                .findAny();
    }
}
