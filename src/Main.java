
import java.util.Scanner;

public class Main {
    public static void main(final String[] args) {
        Scanner s = new Scanner(System.in);
        File f = new File();
        System.out.println("""
                Wybierz funkcje:
                 (0) Odbiornik
                 (1) Nadajnik
                Wybor:\s""");
        int funkcja = Integer.parseInt(s.next());
        if (funkcja == 0) {
            f.gniazda(null, 8080, null, funkcja);
        } else {
            String wiadomosc = f.odczyt("Wiadomosc");
            f.gniazda("localhost", 8080, wiadomosc, funkcja);
        }

    }
}
