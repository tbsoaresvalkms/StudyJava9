package reactive;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SubmissionPublisher;

public class MainReactiveExecutors {
    public static void main(String[] args) {
        System.out.println("Start project: " + Thread.currentThread().getName());
        NF nf1 = new NF("Thiago", "Pequeno principe", 10.0);
        NF nf2 = new NF("Carla", "Pequeno principe", 10.0);
        NF nf3 = new NF("Bruna", "Pequeno principe", 10.0);

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        SubmissionPublisher<NF> publisher = new SubmissionPublisher<>(executorService, 2);
        publisher.consume(WSPrefeituraSync::emit);
        publisher.consume(WSPrefeituraSync::emit);
        publisher.consume(WSPrefeituraSync::emit);
        publisher.submit(nf1);
        publisher.submit(nf2);
        publisher.submit(nf3);

        int i = new Scanner(System.in).nextInt();
    }
}
