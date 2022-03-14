import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException {

        String inputPath = "C:\\Users\\leoni\\IdeaProjects\\CustomKittySplit\\src\\CSVs\\input2.csv";
        String outputPath = "C:\\Users\\leoni\\IdeaProjects\\CustomKittySplit\\src\\CSVs\\output.csv";
        ReadCSV read = new ReadCSV();
        WriteCSV write = new WriteCSV();
        ConvertTable convert = new ConvertTable();
        TreeMap<Integer, ArrayList<Double>> debtsMap = read.textToMap(inputPath);
        LinkedHashMap<Integer, ArrayList<Double>> finalTable = convert.finalTable(debtsMap);
        write.writeString(finalTable, read.getHeadersHorizontal(), outputPath);
    }


}
