package reactive;

import java.util.Scanner;
import java.util.concurrent.SubmissionPublisher;

public class MainReactive {
    public static void main(String[] args) {
        System.out.println("Start project: " + Thread.currentThread().getName());
        NF nf = new NF("Thiago", "Pequeno principe", 10.0);

        SubmissionPublisher<NF> publisher = new SubmissionPublisher<>();
        publisher.consume(WSPrefeituraSync::emit);
        publisher.submit(nf);

        int i = new Scanner(System.in).nextInt();
    }
}
