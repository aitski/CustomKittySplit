import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class ReadCSV {

    private ArrayList<String> headersHorizontal = new ArrayList<>();

    public ArrayList<String> getHeadersHorizontal() {
        return headersHorizontal;
    }

    //метод для считываения файла в текст
    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно, он не находится в нужной директории.");
            return null;
        }
    }

    //метод для конвертации текста в мапу и вычисления сумм должников и получателей
    public TreeMap<Integer, ArrayList<Double>> textToMap(String path) {

        String fileContents = readFileContentsOrNull(path);
        TreeMap<Integer, ArrayList<Double>> debtsMap = new TreeMap<>();
        String[] linesArray;
        if (fileContents.contains("\r")) {
            linesArray = fileContents.split("\r\n"); //разбивка текста на массив строк
        } else {
            linesArray = fileContents.split("\n"); //разбивка текста на массив строк
        }
        String[] workingHeader = linesArray[0].split(","); //разбивка 1 строки в массив для заголовка
        for (int i = 2; i < workingHeader.length; i++) { //создание списка горизонтального заголовка
            headersHorizontal.add(workingHeader[i]);
        }
        for (int i = 1; i < linesArray.length; i++) {  //проходим по массиву долгов начиная со 2 строки,т.к 1 строка - заголовок
            ArrayList<Double> list = new ArrayList<>();
            String[] lineArray = linesArray[i].split(",", workingHeader.length); //разбиваем текущую строку в массив (некоторые строки выходят короче, если в конце запятая,
            // поэтому делаем длину строки, равную заголовку
            int payer = headersHorizontal.indexOf(lineArray[0]); //payer это ключ к мапе, он равен порядковому номеру человека из горизонтального списка
            for (int j = 2; j < lineArray.length; j++) { //проходим по каждому словку строки, начиная с 3 столбца
                double debt = 0;
                boolean isDebtZero = lineArray[j].equals(""); //проверка на пустаю строку
                boolean isSamePerson = payer == (j - 2);//проверка на долг самому себе
                if (!isDebtZero && !isSamePerson) {
                    debt = Double.parseDouble(lineArray[j]);
                }
                if (i != 1 && debtsMap.containsKey(payer)) { //проверка на повторяющееся имя в столбце
                    ArrayList<Double> repeatedList = debtsMap.get(payer);
                    double oldDebt = repeatedList.get(j - 2);
                    double totalDebt = oldDebt + debt;
                    list.add(totalDebt);
                } else {
                    list.add(debt);
                }
            }
            debtsMap.put(payer, list);
        }
        return debtsMap;
    }


}
