/**
 * Created by michalsvacha on 23/04/17.
 */

public class TestCase {
    private int total;
    private int red, orange, yellow, green, blue, violet;

    public TestCase(int total, int red, int orange, int yellow, int green, int blue, int violet) {
        this.total = total;
        this.red = red;
        this.orange = orange;
        this.yellow = yellow;
        this.green = green;
        this.blue = blue;
        this.violet = violet;
    }

    public int getTotal() {
        return total;
    }

    public int getRed() {
        return red;
    }

    public int getOrange() {
        return orange;
    }

    public int getYellow() {
        return yellow;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    public int getViolet() {
        return violet;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "total=" + total +
                ", red=" + red +
                ", orange=" + orange +
                ", yellow=" + yellow +
                ", green=" + green +
                ", blue=" + blue +
                ", violet=" + violet +
                '}';
    }

    private int[] getArrayRepresentation() {
        int[] counts = new int[6];
        counts[0] = this.getRed();
        counts[1] = this.getOrange();
        counts[2] = this.getYellow();
        counts[3] = this.getGreen();
        counts[4] = this.getBlue();
        counts[5] = this.getViolet();
        return counts;
    }

    public String getRingPlacement() {
        String subSolution = this.getNeighbor(-1, this.getArrayRepresentation(), "", "IMPOSSIBLE");
        return this.getPrettyOutput(subSolution);
    }

    private String getNeighbor(int lastIndex, int[] counts, String placement, String bestPlacement) {
        if (placement.length() == this.getTotal()) {
            return this.isValidSolution(placement) ? placement : bestPlacement;
        }

        for (int i = 0; i < 6; i++) {
            if (counts[i] > 0 && isGoodNeighbor(lastIndex, i)) {
                counts[i] = counts[i] - 1;
                String candidateSolution = this.getNeighbor(i, counts, placement + i, bestPlacement);
                if (candidateSolution != "IMPOSSIBLE") {
                    return candidateSolution;
                }
                counts[i] = counts[i] + 1;
            }
        }

        return bestPlacement;
    }

    private boolean isGoodNeighbor(int placed, int candidate) {
        if (placed == -1) {
            return true;
        } else if (placed == candidate) {
            return false;
        } else {
            return true;
        }
    }

    private String getPrettyOutput(String solution) {
        if (solution == "IMPOSSIBLE") {
            return solution;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < solution.length(); i++) {
            int index = Character.getNumericValue(solution.charAt(i));
            sb.append(this.getStringRepresentation(index));
        }
        return sb.toString();
    }

    private String getStringRepresentation(int index) {
        switch (index) {
            case 0:
                return "R";
            case 1:
                return "O";
            case 2:
                return "Y";
            case 3:
                return "G";
            case 4:
                return "B";
            case 5:
                return "V";
            default:
                return "";
        }
    }

    private boolean isValidSolution(String solution) {
        int first = Character.getNumericValue(solution.charAt(0));
        int last = Character.getNumericValue(solution.charAt(this.getTotal() - 1));
        return this.isGoodNeighbor(first, last);
    }
}
