package juntandoTudo;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        NFEmissor nfEmissor = new NFEmissor();
        List<Book> books = Books.all();
        IntStream.range(0, books.size())
                .forEach(i -> System.out.printf("Index: %d Book: %s\n", i, books.get(i).getName()));

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nDigite o numero do livro:");

            int number = scanner.nextInt();
            if (number == -1) break;

            Book book = books.get(number);

            nfEmissor.emit("Thiago", book);

            Books.findSimilar(book)
                    .ifPresentOrElse(Main::ofertaLivro, Main::finalizarCompra);
        }

    }

    private static void finalizarCompra() {
        System.out.println("Compra realizada com sucesso, vc recebera um email ");
    }

    private static void ofertaLivro(Book book) {
        System.out.println("Ja pensou em comprar o livro " + book.getName());
    }
}
