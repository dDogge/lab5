// Yo! Kolla in den här koden, det är som en berg- och dalbana fast i kodform.

package mountain;

import java.util.HashMap;
import java.util.Map;

import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
    private int length;
    private Map<Side, Point> triangleMap = new HashMap<Side, Point>();

    // Konstruktor för Mountain - instansvariabeln "length" sätts här.
    public Mountain(int length) {
        super();
        this.length = length;
    }

    /**
     * Hämtar titeln.
     * 
     * @return titeln
     */
    @Override
    public String getTitle() {
        return "Mountain";
    }

    /**
     * Ritar fjället.
     * 
     * @param turtle turtle-grafikobjektet
     */
    @Override
    public void draw(TurtleGraphics turtle) {
        // Flytta pennan till startpositionen för fjället.
        turtle.moveTo(turtle.getWidth() / 2.0 - length / 2.0, turtle.getHeight() / 2.0 + Math.sqrt(3.0) * length / 4.0);
        
        // Skapa några punkter för att kicka igång fjället.
        Point a = new Point(100, 500);
        Point b = new Point(800, 550);
        Point c = new Point(368, 20);
        
        // Starta rekursionen för att rita fjället.
        fractalMountain(turtle, order, length, a, b, c, 50);
    }

    /*
     * Rekursiv metod: Ritar en rekursiv linje för triangeln.
     */
    private void fractalMountain(TurtleGraphics turtle, int order, double length, Point a, Point b, Point c,
            double dev) {
        if (order == 0) {
            // Basfall: Rita en linje med längden "length" mellan triangelns hörn.
            turtle.moveTo(a.getX(), a.getY());
            turtle.forwardTo(c.getX(), c.getY());
            turtle.forwardTo(b.getX(), b.getY());
            turtle.forwardTo(a.getX(), a.getY());
        } else {
            // Beräkna ett slumpmässigt offset för variation.
            int offset = (int) randFunc(dev);
            
            // Skapa punkter för sidorna med det slumpmässiga offsetet.
            Point ab = createPoint(a, b, offset);
            Point bc = createPoint(b, c, offset);
            Point ca = createPoint(c, a, offset);
            
            // Skapa sidor för triangeln.
            Side side1 = new Side(a, b);
            Side side2 = new Side(b, c);
            Side side3 = new Side(c, a);

            // Använd Side-klassen för att jämföra sidor och skapa punkter vid behov.
            fractalMountain(turtle, order - 1, length / 2, ab, bc, ca, dev / 2);
            fractalMountain(turtle, order - 1, length / 2, ab, bc, b, dev / 2);
            fractalMountain(turtle, order - 1, length / 2, c, bc, ca, dev / 2);
            fractalMountain(turtle, order - 1, length / 2, ab, a, ca, dev / 2);
        }
    }

    // Hjälpmetod för att skapa en punkt mellan två befintliga punkter med ett offset.
    private Point createPoint(Point a, Point b, int offset) {
        Side temp = new Side(a, b);

        if (triangleMap.containsKey(temp)) {
            // Om sidan redan har en punkt, använd den.
            Point p = triangleMap.get(temp);
            triangleMap.remove(temp);
            return p;
        } else {
            // Annars skapa en ny punkt mellan a och b med ett slumpmässigt offset.
            int x = (a.getX() + b.getX()) / 2;
            int y = (a.getY() + b.getY()) / 2 + offset;
            Point p = new Point(x, y);
            triangleMap.put(temp, p);
            return p;
        }
    }

    // En slumpgeneratorfunktion för att skapa variation i fjället.
    public static double randFunc(double dev) {
        double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
        if (Math.random() < 0.5) {
            t = -t;
        }
        return t;
    }
}