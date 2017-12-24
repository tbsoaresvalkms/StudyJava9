package juntandoTudo;

import java.util.concurrent.SubmissionPublisher;

public class NFEmissor {
    private final SubmissionPublisher<NF> publisher;

    public NFEmissor() {
        this.publisher = new SubmissionPublisher<NF>();
        this.publisher.subscribe(new NFSubscriber());
    }

    public void emit(String client, Book book) {
        NF nf = new NF(client, book.getName(), 10);
        this.publisher.submit(nf);
    }

    public void close() {
        this.publisher.close();
    }
}
