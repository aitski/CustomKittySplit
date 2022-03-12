import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class WriteCSV {

    //метод для записи текста в файл
    public void writeString(String input, String path)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(path));
        writer.write(input);
        writer.close();
    }

    //метод для конвертации мапы с долгами в текст
    public String textOutput(LinkedHashMap<Integer, ArrayList<Double>> finalTable, ArrayList<String> headersHorizontal) {
        String text = "," + toStringList(headersHorizontal) + "\n";
        for (int i : finalTable.keySet()) {
            ArrayList<Double> list = finalTable.get(i);
            String line = headersHorizontal.get(i) + "," + toStringList(list);    //собираем строку из заголовка и массива долгов
            text = text + line + "\n"; //собираем финальный текст из строк
        }
        return text;
    }

    //метод для превщаения списка в текст без [ ]
    public static <T> String toStringList(ArrayList<T> a) {
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

