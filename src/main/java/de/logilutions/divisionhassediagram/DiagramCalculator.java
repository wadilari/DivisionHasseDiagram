package main.java.de.logilutions.divisionhassediagram;

import java.util.ArrayList;
import java.util.Arrays;

public class DiagramCalculator {
    private HasseDiagram diagram;
    private int a;

    public static void main(String[] args) {
        DiagramCalculator calculator = new DiagramCalculator(24909);
        calculator.calculate();
        System.out.println(calculator.printDiagram());
    }

    public DiagramCalculator(int a) {
        this.a = a;
        this.diagram = new HasseDiagram();
        diagram.add(1);
    }

    public void calculate() {
        int[] primeFactors = getPrimeFactors(a);
        diagram.add(primeFactors);
        for (int i = 1; i <= 5; i++) {
            diagram.add(dividingMultiples(diagram.getRows(1, i)));
        }

        diagram.add(a);
    }

    public int[] dividingMultiples(int[] numbers) {
        ArrayList<Integer> multiples = new ArrayList<>();
        for (int number : numbers) {
            for (int i : numbers) {
                multiples.add(number * i);
            }
        }
        return multiples.stream().mapToInt(i -> i).filter(i -> a % i == 0).distinct().toArray();
    }

    public static int[] getPrimeFactors(int a) {
        ArrayList<Integer> factorList = new ArrayList<>();
        for (int i = 2; i <= a; i++ ) {
            if (a % i == 0) {
                factorList.add(i);
                a = a/i;
                i = 1;
            }
        }
        return factorList.stream().mapToInt(i -> i).distinct().toArray();
    }

    public static int[] multiples(int[] numbers) {
        ArrayList<Integer> multiples = new ArrayList<>();
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length; j++) {
                multiples.add(numbers[i] * numbers[j]);
            }
        }
        return multiples.stream().mapToInt(i -> i).distinct().toArray();
    }


    public String printDiagram() {
        return diagram.toString();
    }
}
