// En klass som representerar en sida (en linje mellan två punkter) för att användas i Mountain-fraktalen.

package mountain;

public class Side {
    private Point p1, p2; // Två punkter som definierar sidan.

    // Konstruktor för att skapa en sida med två givna punkter.
    public Side(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    // Överskuggning av hashCode-metoden för att generera ett unikt hashvärde för varje sida.
    @Override
    public int hashCode() {
        return p1.hashCode() + p2.hashCode();
    }

    // Överskuggning av equals-metoden för att jämföra två sidor.
    @Override
    public boolean equals(Object a) {
        // Konvertera objektet till en Side.
        Side b = (Side) a;
        
        // Jämför punkterna i den här sidan med punkterna i den andra sidan (ordningen spelar ingen roll).
        return (p1 == b.p1 && p2 == b.p2 || p1 == b.p2 && p2 == b.p1);
    }
}