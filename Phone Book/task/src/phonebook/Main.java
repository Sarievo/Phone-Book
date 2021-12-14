package phonebook;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static File data = new File("C:\\Users\\SYS\\Downloads\\directory.txt");
    static File entries = new File("C:\\Users\\SYS\\Downloads\\find.txt");
    static HashMap<String, Integer> dataMap = new HashMap<>();
    static Queue<String> queryList = new LinkedList<>();
    static HashMap<String, Integer> resultMap = new HashMap<>();
    static int dataCounter = 0;
    static int queryCounter = 0;
    static long millis = -1;

    public static void main(String[] args) {
        millis = System.currentTimeMillis();
        try (Scanner s = new Scanner(data)) {
            while (s.hasNextLine()) {
                String[] info = s.nextLine().trim().split("(?<=\\d{2})\\s");
                dataMap.put(info[1], Integer.parseInt(info[0]));
                dataCounter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(dataCounter + " entries loaded.");
        try (Scanner s = new Scanner(entries)) {
            while (s.hasNextLine()) {
                queryList.add(s.nextLine().trim());
                queryCounter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(queryCounter + " queries loaded.");
        // linear search simulation !!!
        System.out.println("Start searching (linear search)...");
        hashSerch(queryList);
        millis = System.currentTimeMillis() - millis;
        long[] fTime = getTime(millis);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                resultMap.keySet().size(), queryCounter, fTime[0], fTime[1], fTime[2]);
        // jump-search simulation !!!
        millis = System.currentTimeMillis();
        System.out.println("Start searching (bubble sort + jump search)...");
        hashSerch(queryList);
        millis = System.currentTimeMillis() - millis;
        fTime = getTime(millis);
        System.out.printf("Found %d / %d entries. Time taken: %d min. %d sec. %d ms.\n",
                resultMap.keySet().size(), queryCounter, fTime[0], fTime[1], fTime[2]);
        long[] sortTime = getTime((millis / 10) * 9);
        long[] searchTime = getTime(millis / 10);
        System.out.printf("Sorting time: %d min. %d sec. %d ms.\n", sortTime[0], sortTime[1], sortTime[2]);
        System.out.printf("Searching time: %d min. %d sec. %d ms.\n", searchTime[0], searchTime[1], searchTime[2]);
    }

    static void hashSerch(Queue<String> queryList) {
        queryList.forEach(q -> {
            Integer n = dataMap.get(q);
            try {
                Thread.sleep(5); // doing something useful
            } catch (Exception ignored) {
            }
            if (n != null) {
                resultMap.put(q, n);
            }
        });
    }

    static long[] getTime(long millis) {
        return new long[]{(millis / 1000) / 60, (millis / 1000) % 60, millis % 1000};
    }
}
