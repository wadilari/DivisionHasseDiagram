package main.java.de.logilutions.divisionhassediagram;

import java.util.ArrayList;

public class HasseDiagram {
    private ArrayList<DiagramRow> rows = new ArrayList<>();

    public void add(int value) {
        add(-1, value);
    }
    public void add(int row, int value) {
        for (int a :
                getRows(1,rows.size()-2)) {
            if (a == value) return;
        }
        if (row != -1 && row >= rows.size()) {
            throw new IndexOutOfBoundsException("Can't insert into row " + row + " as far as there are only " + rows.size() + "existing!");
        }
        if (row == -1) {
            rows.add(new DiagramRow());
            row = rows.size()-1;
        }
        rows.get(row).add(value);
    }

    public void add(int[] values) {
        rows.add(new DiagramRow());
        add(rows.size()-1, values);
    }

    public void add(int row, int[] values) {
        for (int value : values) {
            add(row, value);
        }
    }

    public int[] getRow(int row) {
        return rows.get(row).getValues();
    }

    public int[] getRows(int min, int max) {
        ArrayList<Integer> output = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            output.addAll(rows.get(i).content);
        }

        return output.stream().mapToInt(i -> i).toArray();
    }

    private class DiagramRow {
        private ArrayList<Integer> content;

        DiagramRow () {
            content = new ArrayList<>();
        }

        DiagramRow(int[] values) {
            content = new ArrayList<>();
            for (int value : values) {
                content.add(value);
            }
        }

        private void add(int a) {
            content.add(a);
        }

        private int[] getValues() {
            return content.stream().mapToInt(i -> i).toArray();
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            for (int value :
                    content) {
                output.append("[" + value + "]  ");
            }
            return output.toString();
        }
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(" ---==[ HasseDiagram ]==---\n");
        int i = 0;
        for (DiagramRow row :
                rows) {
            output.append(" ").append(i++).append(": ").append(row.toString()).append("\n");
        }
        return output.toString();
    }
}
