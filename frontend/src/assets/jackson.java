package jackson;

import java.io.File;
//import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
//import java.util.Collections;

public class jackson {
    public static void main(String[] args) throws FileNotFoundException {
        int[] r = new int[100];
        int[] p = new int[100];
        int tmp = 0;
        int time = 1;
        int tasks = 0;
        int k = 0;
        File data = new File("test2.txt");
        Scanner read_data = new Scanner(data);
        tasks = read_data.nextInt();
        while (read_data.hasNextLine()) {
            r[k] = read_data.nextInt();
            p[k] = read_data.nextInt();
            k++;
        }
        for (int i = 0; i < tasks; i++) {
            for (int j = 1; j < tasks; j++) {
                // Collections.swap(Arrays.asList(r), j - 1, j);
                // Collections.swap(Arrays.asList(p), j - 1, j);
                if (r[j - 1] > r[j]) {
                    tmp = r[j - 1];
                    r[j - 1] = r[j];
                    r[j] = tmp;
                    tmp = p[j - 1];
                    p[j - 1] = p[j];
                    p[j] = tmp;
                }
                if (r[j - 1] == r[j]) {
                    // Collections.swap(Arrays.asList(r), j - 1, j);
                    // Collections.swap(Arrays.asList(p), j - 1, j);
                    if (p[j - 1] < p[j]) {
                        tmp = r[j - 1];
                        r[j - 1] = r[j];
                        r[j] = tmp;
                        tmp = p[j - 1];
                        p[j - 1] = p[j];
                        p[j] = tmp;
                    }
                }
            }
        }
        for (int j = 1; j < tasks + 1; j++) {
            System.out.println("Task " + j + ": r " + r[j - 1] + " p " + p[j - 1]);
            time += p[j - 1];
        }
        System.out.println("Elapsed time: " + time);

        read_data.close();
    }
}
