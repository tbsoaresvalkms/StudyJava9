package reactive;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class MainSubscriber {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        SubmissionPublisher<NF> publisher = new SubmissionPublisher<>(executorService, 2);
        NFSubscriber nfSubscriber = new NFSubscriber();

        publisher.subscribe(nfSubscriber);
        publisher.consume(c -> System.out.println(c.getClient()));


        NF nf1 = new NF("Thiago", "Pequeno principe", 10.0);
        NF nf2 = new NF("Carla", "Pequeno principe", 10.0);
        NF nf3 = new NF("Bruna", "Pequeno principe", 10.0);
        System.out.println("Start app!");
        publisher.submit(nf1);
        publisher.submit(nf2);
        publisher.submit(nf3);
        int i = new Scanner(System.in).nextInt();
    }
}
