package business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
/**

 @author    Hilal Yazici, My Khanh Phan, Souman Qadeer und Zonera Iqbal
 Diese Klasse beschäftigt sich mit der Generierung von den zufälligen Zhalen die in der MinispielView
 und in der MinispielLoeseView gebraucht werden. Sie fungieren als zweites Leben, um das Spiel
 fortzusetzen.
 **/

public class RandomZahlenManager {
    protected static final SimpleIntegerProperty a = new SimpleIntegerProperty();
    protected static final SimpleIntegerProperty b = new SimpleIntegerProperty();
    protected static final SimpleIntegerProperty c = new SimpleIntegerProperty();
    protected static final SimpleIntegerProperty counter = new SimpleIntegerProperty();


    public static SimpleIntegerProperty randomZahlenPropertyA() {
        return a;
    }
    public static SimpleIntegerProperty randomZahlenPropertyB() {
        return b;
    }
    public static SimpleIntegerProperty randomZahlenPropertyC() {
        return c;
    }

    public static void randomWerte() {
        System.out.println("MINIGAMEVIEWCONTROLLER");
        List<Integer> numbers = generateUniqueNumbers(0, 9, 3); // Generiere 3 eindeutige Zahlen von 0 bis 9
        a.set(numbers.get(0));
        b.set(numbers.get(1));
        c.set(numbers.get(2));
        System.out.println("a: " + a + ", b: " + b + ", c: " + c);

    }
    private static List<Integer> generateUniqueNumbers(int min, int max, int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);

        return numbers.subList(0, count);
    }
    public static void counterErhöhen() {
        counter.set(counter.get() + 1);
    }

    public static SimpleIntegerProperty counterProperty() {
        return counter;
    }
    public static void counterAufNull() {
        counter.set(0);
    }
}
