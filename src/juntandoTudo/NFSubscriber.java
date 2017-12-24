package juntandoTudo;

import java.util.concurrent.Flow;

public class NFSubscriber implements Flow.Subscriber<NF> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(1);
    }

    @Override
    public void onNext(NF item) {
        try {
            WSPrefeitura.emit(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.subscription.request(1);
        }

    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Todas notas fiscais foram emitidas");
    }
}
