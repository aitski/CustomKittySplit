import java.util.*;

public class ConvertTable {

    private HashMap<Integer, Double> giversSums = new HashMap<>();
    private HashMap<Integer, Double> receiversSums = new HashMap<>();

    //метод для расчета долга и создания финальной таблицы
    public LinkedHashMap<Integer, ArrayList<Double>> finalTable(TreeMap<Integer, ArrayList<Double>> debtsMap) {
        calculateSums(debtsMap);
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

    public void calculateSums(TreeMap<Integer, ArrayList<Double>> debtsMap) {
        for (int i=0;i<debtsMap.size();i++) {
            ArrayList<Double> list = debtsMap.get(i);
            double recieverSum = 0;
            for (int j = 0;j<list.size();j++) {
                recieverSum += list.get(j);//добавляем сумму для получателя
                double giverSum=0;
                if(i!=0) {
                     giverSum = giversSums.get(j);
                }
                giversSums.put(j, giverSum + list.get(j));//добавляем сумму для должника в мапу
            }
            receiversSums.put(i, recieverSum);
        }
    }
}
