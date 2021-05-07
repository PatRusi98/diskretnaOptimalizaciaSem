import java.io.*;
import java.util.*;

public class main {
    private static final int K = 12000;
    private static final int N = 500;
    private static final int R = 400;

    public static void main(String[] args) throws IOException {
        BufferedReader readerA = new BufferedReader(new FileReader("src/H2_a.txt"));
        BufferedReader readerC = new BufferedReader(new FileReader("src/H2_c.txt"));
        Writer writer = new FileWriter("src/riesenie.txt");
        String lineA;
        String lineC;
        ArrayList<Integer> A = new ArrayList<Integer>();
        ArrayList<Integer> C = new ArrayList<Integer>();
        ArrayList<Double> pomer = new ArrayList<Double>();
        ArrayList<Boolean> alRiesenia = new ArrayList<Boolean>();
        int z = 0;
        int kap = K;
        int cena = 0;


        while((lineA = readerA.readLine()) != null) {
            lineC = readerC.readLine();
            A.add(Integer.parseInt(lineA));
            C.add(Integer.parseInt(lineC));
            pomer.add((double) (Integer.parseInt(lineC)) / (double) (Integer.parseInt(lineA)));
            alRiesenia.add(false);
        }

        /*for(int j = 0; j < A.size(); j++) {
            System.out.println(A.get(j) + " " + C.get(j) + " " + pomer.get(j));
        }*/

        for (int i = 0; i < N; i++) {
            if (z > R - 1 || kap <= 0) {
                break;
            }

            double koe = 0;
            int ind = 0;

            for (int j = 0; j < N; j++) {
                if (!alRiesenia.get(j) && koe < pomer.get(j)) {
                    koe = pomer.get(j);
                    ind = j;
                }
            }

            if (!alRiesenia.get(ind) && kap - A.get(ind) >= 0) {
                kap -= A.get(ind);
                alRiesenia.set(ind, true);
                cena += C.get(ind);
                z++;
            }
        }

        /*System.out.println("Voľná Kapacita: " + kap);
        System.out.println("Hodnota účelovej funkcie:" + "\n");
        for (int i = 0; i < N; i++) {
            System.out.println("A: " + A.get(i) + " C: " + C.get(i) + " Pomer: " + pomer.get(i) + " Riešenie: " + alRiesenia.get(i));
        }*/

        try {
            writer.write("Voľná Kapacita: " + kap + "\n");
            writer.write("Hodnota účelovej funkcie: " + cena + "\n\n");
            for (int i = 0; i < N; i++) {
                writer.write("A: " + A.get(i) + " C: " + C.get(i) + " Pomer: " + pomer.get(i) + " Riešenie: " + alRiesenia.get(i) + "\n");
            }
        } finally {
            writer.close();
        }
    }
}