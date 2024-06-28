import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class InputFileGenerator {
    private File file;
    private int numOfAdd, numOfRemove, numOfSearch, numOfUpdate;
    private static final String[] OPERATIONS = {"ADD", "REMOVE", "SEARCH", "UPDATE"};
    private static final Random RANDOM = new Random();
    private ArrayList<String> symbols;

    public InputFileGenerator(String fileName, int numOfAdd, int numOfRemove, int numOfSearch, int numOfUpdate) {
        this.file = new File(fileName);
        this.numOfAdd = numOfAdd;
        this.numOfRemove = numOfRemove;
        this.numOfSearch = numOfSearch;
        this.numOfUpdate = numOfUpdate;
        this.symbols = new ArrayList<>();
    }

    public void generate() {
        ArrayList<String> operations = new ArrayList<>();
        for (int i = 0; i < numOfAdd; i++) operations.add("ADD");
        for (int i = 0; i < numOfRemove; i++) operations.add("REMOVE");
        for (int i = 0; i < numOfSearch; i++) operations.add("SEARCH");
        for (int i = 0; i < numOfUpdate; i++) operations.add("UPDATE");

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            while (!operations.isEmpty()) {
                int randomIndex = RANDOM.nextInt(operations.size());
                String selectedOperation = operations.get(randomIndex);
                String symbol = "SYM" + RANDOM.nextInt(operations.size());
                operations.remove(randomIndex);

                switch(selectedOperation) {
                    case "ADD" -> writer.write(generateAddCommand(symbol));
                    case "REMOVE" -> writer.write(generateRemoveCommand(symbol));
                    case "SEARCH" -> writer.write(generateSearchCommand(symbol));
                    case "UPDATE" -> writer.write(generateUpdateCommand(symbol));
                }
                writer.newLine();
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String generateAddCommand(String symbol) {
        String operation = "ADD";
        //randomly generate a symbol
        symbols.add(symbol);
        //randomly generate price, volume, and marketCap
        double price = RANDOM.nextDouble() * 1000;
        long volume = RANDOM.nextInt(1000000);
        long marketCap = RANDOM.nextInt(1000000000);
        return operation + " " + symbol + " " + price + " " + volume + " " + marketCap;
    }

    private String generateRemoveCommand(String symbol) {
        String operation = "REMOVE";
        //randomly pick a symbol from the list of symbols
        if(symbols.isEmpty()) {
            return "";
        }
        return operation + " " + symbol;
    }

    private String generateSearchCommand(String symbol) {
        String operation = "SEARCH";
        //randomly pick a symbol from the list of symbols
        return operation + " " + symbol;
    }

    private String generateUpdateCommand(String symbol) {
        String operation = "UPDATE";
        //randomly pick a symbol from the list of symbols
        //randomly generate new price, volume, and marketCap, symbol
        String newSymbol = "STOCK" + RANDOM.nextInt(1000);
        double price = RANDOM.nextDouble() * 1000;
        long volume = RANDOM.nextInt(1000000);
        long marketCap = RANDOM.nextInt(1000000000);


        return operation + " " + symbol + " " + newSymbol + " " + price + " " + volume + " " + marketCap;
    }

   public static void main(String[] args) {
       InputFileGenerator generator1 = new InputFileGenerator("input1.txt", 500, 100, 200, 200);
       InputFileGenerator generator2 = new InputFileGenerator("input2.txt", 750, 350, 450, 450);
       InputFileGenerator generator3 = new InputFileGenerator("input3.txt", 1000, 600, 700, 700);
       InputFileGenerator generator4 = new InputFileGenerator("input4.txt", 1250, 850, 950, 950);
       InputFileGenerator generator5 = new InputFileGenerator("input5.txt", 1500, 1100, 1200, 1200);
       InputFileGenerator generator6 = new InputFileGenerator("input6.txt", 1750, 1350, 1450, 1450);
       InputFileGenerator generator7 = new InputFileGenerator("input7.txt", 2000, 1600, 1700, 1700);
       InputFileGenerator generator8 = new InputFileGenerator("input8.txt", 2250, 1850, 1950, 1950);
       InputFileGenerator generator9 = new InputFileGenerator("input9.txt", 2500, 2100, 2200, 2200);
       InputFileGenerator generator10 = new InputFileGenerator("input10.txt", 2750, 2350, 2450, 2350);


        generator1.generate();
        generator2.generate();
        generator3.generate();
        generator4.generate();
        generator5.generate();
        generator6.generate();
        generator7.generate();
        generator8.generate();
        generator9.generate();
        generator10.generate();

    }
}