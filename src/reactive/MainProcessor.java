package reactive;

import java.util.Scanner;
import java.util.concurrent.SubmissionPublisher;

public class MainProcessor {
    public static void main(String[] args) {
        SubmissionPublisher<NF> publisher = new SubmissionPublisher<>();
        NFFilterProcessor nfFilterProcessor = new NFFilterProcessor();
        NFSubscriber nfSubscriber = new NFSubscriber();

        publisher.subscribe(nfFilterProcessor);
        nfFilterProcessor.subscribe(nfSubscriber);

        NF nf1 = new NF("Thiago", "Pequeno principe", -10.0);
        NF nf2 = new NF("Carla", "Pequeno principe", 10.0);
        NF nf3 = new NF("Bruna", "Pequeno principe", 10.0);

        publisher.submit(nf1);
        publisher.submit(nf2);
        publisher.submit(nf3);

        publisher.close();

        int i = new Scanner(System.in).nextInt();
    }
}
