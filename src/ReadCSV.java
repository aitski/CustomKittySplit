import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ReadCSV {

    public ArrayList<String> headersHorizontal = new ArrayList<>();
    public ArrayList<String> headersVertical = new ArrayList<>();

    //метод для считываения файла в текст
    public String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл. Возможно, он не находится в нужной директории.");
            return null;
        }
    }

    //метод для конвертации текста в двумерный массив
    public double[][] textToArray(String fileContents) {
        //разбивка текста на массив строк
        String[] linesArray = fileContents.split("\r\n");
        //разбивка 1 строки в массив для заголовка
        String[] workingHeader = linesArray[0].split(",");
        //создание списка горизонтального заголовка
        for (int i = 2; i < workingHeader.length; i++) {
            headersHorizontal.add(workingHeader[i]);
        }
        //размеры двумерного массива долгов без заголовков
        int sizeHor = headersHorizontal.size();
        int sizeVert = linesArray.length - 1;
        double[][] matrix = new double[sizeVert][sizeHor];
        //проходим по массиву долгов начиная со 2 строки,т.к 1 строка - заголовок
        for (int i = 1; i < linesArray.length; i++) {
            //разбиваем текущую строку в массив (некоторые строки выходят короче, если в конце запятая,
            // поэтому делаем длину строки, равную заголовку
            String[] lineArray = linesArray[i].split(",", workingHeader.length);
            //проходим по каждому словку строки, начиная с 3 столбца
            for (int j = 2; j < lineArray.length; j++) {
                //если ячейка с долгом пустая, оставляем долг 0,
                // иначе конвертивуем в double и записываем в переменную
                double debt = 0;
                boolean isDebtZero = lineArray[j].equals("");
                if (!isDebtZero) {
                    debt = Double.parseDouble(lineArray[j]);
                }
                //если строка не первая и такое имя уже было, то суммируем соответствующие долги
                if (i != 1 && headersVertical.contains(lineArray[0])) {
                    //индекс строки с таким же именем
                    int index = headersVertical.indexOf(lineArray[0]);
                    //[j-2] потому что нужно начать с начала массива матрицы долгов
                    // (с нулевого индекса, а не со второго)
                    matrix[index][j - 2] += debt;
                    debt = 0;//то суммируем долги от тех же должников
                }
                //записываем долг в массив
                matrix[i - 1][j - 2] = debt;
            }
            //записываем имена в список, для проверки на повторение
            headersVertical.add(lineArray[0]);
        }
        return matrix;
    }
}
