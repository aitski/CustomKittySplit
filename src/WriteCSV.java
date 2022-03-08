import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteCSV {

    //метод для записи текста в файл
    public void writeString(String input, String path)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(input);
        writer.close();
    }


    //метод для конвертации двумерного массива с долгами в текст
    public String textOutput(double[][] matrix, ArrayList<String> headersHorizontal, ArrayList<String> headersVertical) {
        int sizeHor = matrix[0].length;
        int sizeVert = matrix.length;
        //новый массив может быть короче старого по вертикали из-за повторений в списке имен
        int sizeDiff = headersVertical.size() - headersHorizontal.size();
        double[][] newMatrix = new double[sizeVert - sizeDiff][sizeHor];
        //создаем первую строку финального текста
        String text = "," + toStringList(headersHorizontal) + "\n";
        for (int i = 0; i < sizeVert - sizeDiff; i++) {
            for (int j = 0; j < sizeHor; j++) {
                //если должен сам себе, то ноль
                if (i == j) {
                    newMatrix[i][j] = 0;
                } else {
                    //взаимозачет между должником и покупателем и замена местами долгов покупателя и дожника
                    if (matrix[i][j] > matrix[j][i]) {
                        newMatrix[i][j] = 0;
                    } else {
                        newMatrix[i][j] = matrix[j][i] - matrix[i][j];
                    }
                }
            }
            //собираем строку из заголовка и массива долгов
            String line = headersHorizontal.get(i) + "," + toStringArray(newMatrix[i]);
            //собираем финальный текст из строк
            text = text + line + "\n";
        }
        return text;
    }

    //метод для превщаения массива в текст без [ ]
    public static String toStringArray(double[] a) {
        if (a == null)
            return "null";
        int iMax = a.length - 1;
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            if (a[i] != 0) {
                b.append(a[i]);
            }
            if (i == iMax)
                return b.toString();
            b.append(", ");
        }
    }

    //метод для превщаения списка в текст без [ ]
    public static String toStringList(ArrayList<String> a) {
        if (a == null)
            return "null";
        int iMax = a.size() - 1;
        StringBuilder b = new StringBuilder();
        for (int i = 0; ; i++) {
            b.append(a.get(i));
            if (i == iMax)
                return b.toString();
            b.append(", ");
        }
    }


}

