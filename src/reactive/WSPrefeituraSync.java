package reactive;

public class WSPrefeituraSync {
    public static void emit(NF nf) {
        try {
            System.out.println("emitindo..." + Thread.currentThread().getName());
            System.out.println("Cliente: " + nf.getClient());
            Thread.sleep(5000);
            System.out.println("emitido!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("falhou!");
        }
    }
}
