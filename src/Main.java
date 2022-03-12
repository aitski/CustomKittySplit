import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {

        ReadCSV read = new ReadCSV();
        WriteCSV write = new WriteCSV();
        CalculateSums sums = new CalculateSums();
        CalculateDebts calc = new CalculateDebts();
        String input = read.readFileContentsOrNull("C:\\Users\\leoni\\IdeaProjects\\CustomKittySplit\\src\\CSVs\\input.csv");
        sums.textToMap(input);
        LinkedHashMap<Integer, ArrayList<Double>> finalTable = calc.finalTable(sums.giversSums, sums.receiversSums);
        String output = write.textOutput(finalTable, sums.headersHorizontal);
        write.writeString(output, "C:\\Users\\leoni\\IdeaProjects\\CustomKittySplit\\src\\CSVs\\output.csv");
    }


}
