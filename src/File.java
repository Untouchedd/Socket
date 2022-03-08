import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class File {

    String sciezka = System.getProperty("user.dir");

    public String odczyt(String nazwa) {
        try {
            StringBuilder zawartosc = new StringBuilder();
            BufferedReader plik = new BufferedReader(new FileReader(sciezka + "/" + nazwa + ".txt"));
            while(plik.ready()) {
                zawartosc.append(plik.readLine());
            }
            return zawartosc.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void zapis(String nazwa, String dane) {
        try {
            FileWriter plik = new FileWriter(sciezka + "/" + nazwa + ".txt");
            plik.write(dane);
            plik.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gniazda(String ip, int port, String plik, int tryb) {
        try {
            Socket soc;
            byte [] bajtyDanych;
            if(tryb == 1) {
                byte [] dane = plik.getBytes(StandardCharsets.UTF_8);
                soc = new Socket(ip, port);
                DataOutputStream wyslanie = new DataOutputStream(soc.getOutputStream());
                wyslanie.write(dane, 0, dane.length);
                wyslanie.close();
            } else {
                ServerSocket ssoc = new ServerSocket(port);
                soc = ssoc.accept();
                DataInputStream odbior = new DataInputStream(soc.getInputStream());
                bajtyDanych = odbior.readAllBytes();
                ssoc.close();
                odbior.close();
                zapis("Wynik", new String(bajtyDanych));
            }
            soc.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}