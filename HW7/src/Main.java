import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    static ArrayList<Integer> dataPointsAddX, dataPointsSearchX;
    static ArrayList<Long> dataPointsAddY, dataPointsSearchY;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <input_file>");
            return;
        }

        dataPointsAddX = new ArrayList<>();
        dataPointsAddY = new ArrayList<>();
        dataPointsSearchX = new ArrayList<>();
        dataPointsSearchY = new ArrayList<>();

        String inputFile1 = args[0];
        String inputFile2 = args[1];
        String inputFile3 = args[2];
        String inputFile4 = args[3];
        String inputFile5 = args[4];
        String inputFile6 = args[5];
        String inputFile7 = args[6];
        String inputFile8 = args[7];
        String inputFile9 = args[8];
        String inputFile10 = args[9];

        StockDataManager manager1 = new StockDataManager();
        StockDataManager manager2 = new StockDataManager();
        StockDataManager manager3 = new StockDataManager();
        StockDataManager manager4 = new StockDataManager();
        StockDataManager manager5 = new StockDataManager();
        StockDataManager manager6 = new StockDataManager();
        StockDataManager manager7 = new StockDataManager();
        StockDataManager manager8 = new StockDataManager();
        StockDataManager manager9 = new StockDataManager();
        StockDataManager manager10 = new StockDataManager();

        FileReader(inputFile1, manager1);
        FileReader(inputFile2, manager2);
        FileReader(inputFile3, manager3);
        FileReader(inputFile4, manager4);
        FileReader(inputFile5, manager5);
        FileReader(inputFile6, manager6);
        FileReader(inputFile7, manager7);
        FileReader(inputFile8, manager8);
        FileReader(inputFile9, manager9);
        FileReader(inputFile10, manager10);

        performPerformanceAnalysisSearch(manager1);
        PerformPerformanceAnalysisAdd(manager1);
        performPerformanceAnalysisSearch(manager2);
        PerformPerformanceAnalysisAdd(manager2);
        performPerformanceAnalysisSearch(manager3);
        PerformPerformanceAnalysisAdd(manager3);
        performPerformanceAnalysisSearch(manager4);
        PerformPerformanceAnalysisAdd(manager4);
        performPerformanceAnalysisSearch(manager5);
        PerformPerformanceAnalysisAdd(manager5);
        performPerformanceAnalysisSearch(manager6);
        PerformPerformanceAnalysisAdd(manager6);
        performPerformanceAnalysisSearch(manager7);
        PerformPerformanceAnalysisAdd(manager7);
        performPerformanceAnalysisSearch(manager8);
        PerformPerformanceAnalysisAdd(manager8);
        performPerformanceAnalysisSearch(manager9);
        PerformPerformanceAnalysisAdd(manager9);
        performPerformanceAnalysisSearch(manager10);
        PerformPerformanceAnalysisAdd(manager10);


        SwingUtilities.invokeLater(() -> {
            String plotType = "line"; // Change to "scatter" for scatter plot
            GUIVisualization frameAdd = new GUIVisualization(plotType, dataPointsAddX, dataPointsAddY); // Create a new instance of GUIVisualization
            frameAdd.setTitle("Performance Analysis - ADD Operation");
            frameAdd.setVisible(true); // Make the frame visible

            GUIVisualization frameSearch = new GUIVisualization(plotType, dataPointsSearchX, dataPointsSearchY); // Create a new instance of GUIVisualization
            frameSearch.setTitle("Performance Analysis - SEARCH Operation");
            frameSearch.setVisible(true); // Make the frame visible
        });

        System.out.println("Add Operation input sizes: " + dataPointsAddX + " Average Execution Times: " + dataPointsAddY);
        System.out.println("SEARCH operation input sizes: " + dataPointsSearchX + "  Average Execution Times: " + dataPointsSearchY);
    }


    private static void FileReader(String inputFile, StockDataManager manager) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                processCommand(line, manager);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void processCommand(String line, StockDataManager manager) {
        String[] tokens = line.split(" ");
        String command = tokens[0];

        switch(command) {
            case "ADD" ->
                    manager.addOrUpdateStock(tokens[1], Double.parseDouble(tokens[2]), Long.parseLong(tokens[3]), Long.parseLong(tokens[4]));
            case "REMOVE" -> manager.removeStock(tokens[1]);
            case "SEARCH" -> {
                Stock stock = manager.searchStock(tokens[1]);
                if(stock != null) {
                    System.out.println(stock);
                } else {
                    System.out.println("Stock not found: " + tokens[1]);
                }
            }
            case "UPDATE" ->
                    manager.updateStock(tokens[1], Double.parseDouble(tokens[3]), Long.parseLong(tokens[4]), Long.parseLong(tokens[5]));
            default -> System.out.println("Unknown command: " + command);
        }

    }

    private static void performPerformanceAnalysisSearch(StockDataManager manager) {

        // Warm up the JVM
        for (int i = 0; i < 100; i++) {
            manager.searchStock("SYM" + i);
        }
        int size = manager.getSize();

        long startTime, endTime, totalTime = 0;
        // Measure time for SEARCH operation
        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            manager.searchStock("SYM" + i);
        }
        endTime = System.nanoTime();
        totalTime += (endTime - startTime);
        long averageSearchTime = totalTime / 100;
        System.out.println("Average SEARCH time: " + averageSearchTime + " ns");
        dataPointsSearchX.add(size);
        dataPointsSearchY.add(averageSearchTime);
    }

    private static void PerformPerformanceAnalysisAdd(StockDataManager manager){

        // Warm up the JVM
        for (int i = 0; i < 100; i++) {
            manager.searchStock("SYM" + i);
        }
        int size = manager.getSize();

        // Measure time for ADD operation
        long startTime, endTime;
        startTime = System.nanoTime();
        for (int i = 0; i < 100; i++) {
            String symbol = "SYM" + i;
            manager.addOrUpdateStock(symbol, i, i*1000, i*1000000);
            //manager.removeStock(symbol);
        }
        endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        long averageAddTime = totalTime / 100;
        System.out.println("Average ADD time: " + averageAddTime + " ns");
        dataPointsAddX.add(size);
        dataPointsAddY.add(averageAddTime);

    }
}
