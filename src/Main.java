import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {

    public static void main(String[] args) throws IOException {

        ReadCSV read = new ReadCSV();
        WriteCSV write = new WriteCSV();
        CalculateDebts calc = new CalculateDebts();
        String input = read.readFileContentsOrNull("C:\\Users\\leoni\\IdeaProjects\\CustomKittySplit\\src\\CSVs\\input.csv");
        double[][] matrix = read.textToArray(input);
        LinkedHashMap<Integer, ArrayList<Double>> finalTable = calc.finalTable(matrix);
        String output = write.textOutput(finalTable, read.headersHorizontal);
        write.writeString(output, "C:\\Users\\leoni\\IdeaProjects\\CustomKittySplit\\src\\CSVs\\output.csv");
    }


}
