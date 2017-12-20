package reactive;

import java.util.concurrent.Flow.*;
import java.util.concurrent.SubmissionPublisher;

public class NFFilterProcessor extends SubmissionPublisher<NF> implements Processor<NF, NF> {
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(NF item) {
        if (item.hasValidAmount()) {
            submit(item);
        } else {
            System.out.println("Valor negativo");
        }
        this.subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {
        close();
    }
}
