import java.util.*;

public class CalculateDebts {

    //метод для расчета долга и создания финальной таблицы
    public LinkedHashMap<Integer, ArrayList<Double>> finalTable(HashMap<Integer, Double> giversSums, HashMap<Integer, Double> receiversSums) {
        LinkedHashMap<Integer, ArrayList<Double>> finalTable = new LinkedHashMap<>();
        HashMap<Integer, Double> debts = new HashMap<>(); //здесь храним ненулевые финальные долги
        int max = 0;
        for (int i = 0; i < giversSums.size(); i++) {
            double debt = giversSums.get(i) - receiversSums.get(i);
            ArrayList<Double> list = new ArrayList<>(Collections.nCopies(giversSums.size(), 0.0)); //заполняем список нулями
            finalTable.put(i, list);
            if (debt < 0) { //долг меньше нуля, присваеваем номер человека переменной, чтобы потом положить долг в нужную колонку
                max = i;
            } else if (debt > 0) {
                debts.put(i, debt);
            }
        }
        for (int debtor : debts.keySet()) {
            ArrayList<Double> list = finalTable.get(debtor);
            list.set(max, debts.get(debtor)); //добавляем долг в фин список в необх колонку
            finalTable.put(debtor, list);
        }
        return finalTable;
    }
}
