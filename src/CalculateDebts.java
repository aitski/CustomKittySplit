import java.util.*;

public class CalculateDebts {

    public LinkedHashMap<Integer, ArrayList<Double>> finalTable(double[][] matrix) {
        HashMap<Integer, Double> giversSums = new HashMap<>();
        HashMap<Integer, Double> receiversSums = new HashMap<>();
        LinkedHashMap<Integer, ArrayList<Double>> debts = new LinkedHashMap<>();
        int max = 0;

        for (int i = 0; i < matrix[i].length; i++) {
            double giverSum = 0;
            double recieverSum = 0;
            ArrayList<Double> list = new ArrayList<>();
            for (int j = 0; j < matrix[i].length; j++) {
                recieverSum += matrix[i][j];
                giverSum += matrix[j][i];
                list.add(0.0);
            }
            debts.put(i, list);
            receiversSums.put(i, recieverSum);
            giversSums.put(i, giverSum);
        }

        for (int i = 0; i < giversSums.size(); i++) {
            if (receiversSums.get(i) > giversSums.get(i)) {
                max = i;
            } else if (receiversSums.get(i) < giversSums.get(i)) {
                ArrayList<Double> list = debts.get(i);
                list.set(max, giversSums.get(i) - receiversSums.get(i));
                debts.put(i, list);
            }
        }
        return debts;
    }
}
