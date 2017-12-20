package reactive;


import java.util.concurrent.Flow.*;

public class NFSubscriber implements Subscriber<NF> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        System.out.println("onSubscription: " + Thread.currentThread().getName());
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(NF item) {
        System.out.println("onNext: " + Thread.currentThread().getName() + " -- " + item.getClient());
        WSPrefeituraSync.emit(item);
        WSPrefeituraSync.emit(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Salvando o erro: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Todas as notas foram emitidas");
    }
}
