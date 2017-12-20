package reactive;

public class MainSync {
    public static void main(String[] args) {
        System.out.println("Gerando a nota");
        NF nf = new NF("Thiago", "Pequeno principe", 10.0);
        WSPrefeituraSync.emit(nf);
        System.out.println("Parabens pela sua compra");
    }
}
