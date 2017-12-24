package juntandoTudo;

public class NF {
    private final String client;
    private final String book;
    private final double amount;

    public NF(String client, String book, double amount) {
        this.client = client;
        this.book = book;
        this.amount = amount;
    }

    public boolean hasvalidAmount() {
        return 0 < amount;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", client, book);
    }
}
