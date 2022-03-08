import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        ReadCSV read = new ReadCSV();
        WriteCSV write = new WriteCSV();
        String input = read.readFileContentsOrNull("/Users/irinakvan/IdeaProjects/CustomKittySplit/src/CSVs/input.csv");
        double[][] matrix = read.textToArray(input);
        String output = write.textOutput(matrix, read.headersHorizontal, read.headersVertical);
        write.writeString(output, "/Users/irinakvan/IdeaProjects/CustomKittySplit/src/CSVs/output.csv");
    }


}
