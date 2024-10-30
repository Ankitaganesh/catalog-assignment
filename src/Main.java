import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Point {
    int x;
    BigInteger y;

    Point(int x, BigInteger y) {
        this.x = x;
        this.y = y;
    }
}

class Keys {
    int n;
    int k;

    Keys(int n, int k) {
        this.n = n;
        this.k = k;
    }
}

class PointData {
    String base;
    String value;

    PointData(String base, String value) {
        this.base = base;
        this.value = value;
    }
}

public class Main {
    private static BigInteger convertFromBase(String value, int base) {
        return new BigInteger(value, base);
    }

    private static double[] gaussianElimination(double[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            double maxEl = Math.abs(matrix[i][i]);
            int maxRow = i;

            for (int k = i + 1; k < n; k++) {
                if (Math.abs(matrix[k][i]) > maxEl) {
                    maxEl = Math.abs(matrix[k][i]);
                    maxRow = k;
                }
            }

            for (int k = i; k < n + 1; k++) {
                double tmp = matrix[maxRow][k];
                matrix[maxRow][k] = matrix[i][k];
                matrix[i][k] = tmp;
            }

            for (int k = i + 1; k < n; k++) {
                double c = -matrix[k][i] / matrix[i][i];
                for (int j = i; j < n + 1; j++) {
                    if (i == j) {
                        matrix[k][j] = 0;
                    } else {
                        matrix[k][j] += c * matrix[i][j];
                    }
                }
            }
        }

        double[] x = new double[n];
        for (int i = n - 1; i > -1; i--) {
            x[i] = matrix[i][n] / matrix[i][i];
            for (int k = i - 1; k > -1; k--) {
                matrix[k][n] -= matrix[k][i] * x[i];
            }
        }
        return x;
    }

    private static BigInteger findSecret(Map<String, Object> testCase) {
        Keys keys = (Keys) testCase.get("keys");
        List<Point> points = new ArrayList<>();

        for (int i = 1; i <= keys.n; i++) {
            String key = String.valueOf(i);
            if (testCase.containsKey(key)) {
                PointData pd = (PointData) testCase.get(key);
                BigInteger y = convertFromBase(pd.value, Integer.parseInt(pd.base));
                points.add(new Point(i, y));
            }
        }

        double[][] matrix = new double[keys.k][keys.k + 1];
        for (int i = 0; i < keys.k; i++) {
            Point p = points.get(i);
            for (int j = 0; j < keys.k; j++) {
                matrix[i][j] = Math.pow(p.x, keys.k - 1 - j);
            }
            matrix[i][keys.k] = p.y.doubleValue();
        }

        double[] coefficients = gaussianElimination(matrix, keys.k);
        return BigInteger.valueOf(Math.round(coefficients[keys.k - 1]));
    }

    public static void main(String[] args) {
        Map<String, Object> testCase1 = new HashMap<>();
        Keys keys1 = new Keys(4, 3);
        testCase1.put("keys", keys1);
        testCase1.put("1", new PointData("10", "4"));
        testCase1.put("2", new PointData("2", "111"));
        testCase1.put("3", new PointData("10", "12"));
        testCase1.put("6", new PointData("4", "213"));

        Map<String, Object> testCase2 = new HashMap<>();
        Keys keys2 = new Keys(10, 7);
        testCase2.put("keys", keys2);
        testCase2.put("1", new PointData("6", "13444211440455345511"));
        testCase2.put("2", new PointData("15", "aed7015a346d63"));
        testCase2.put("3", new PointData("15", "6aeeb69631c227c"));
        testCase2.put("4", new PointData("16", "e1b5e05623d881f"));
        testCase2.put("5", new PointData("8", "316034514573652620673"));
        testCase2.put("6", new PointData("3", "2122212201122002221120200210011020220200"));
        testCase2.put("7", new PointData("3", "20120221122211000100210021102001201112121"));
        testCase2.put("8", new PointData("6", "20220554335330240002224253"));
        testCase2.put("9", new PointData("12", "45153788322a1255483"));
        testCase2.put("10", new PointData("7", "1101613130313526312514143"));

        System.out.println("Secret for test case 1: " + findSecret(testCase1));
        System.out.println("Secret for test case 2: " + findSecret(testCase2));
    }
}