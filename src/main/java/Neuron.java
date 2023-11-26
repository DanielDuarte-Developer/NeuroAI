import java.util.ArrayList;
import java.util.List;

public class Neuron {

    public final List<Integer> nextIds;
    private final List<Double> inValues = new ArrayList<>();
    private final double magicValue;

    public Neuron(List<Integer> nextIds, double magicValue) {
        this.nextIds = nextIds;
        this.magicValue = magicValue;
    }

    public Neuron(double magicValue) {
        this.nextIds = new ArrayList<>();
        this.magicValue = magicValue;
    }

    public void addInput(double value) {
        inValues.add(value);
    }

    public double operation() {
        return magicValue * (sumElements() % 1);
    }

    private Double sumElements() {
        Double r = 0d;
        for (Double v : inValues) {
            r += v;
        }
        return r;
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "nextIds=" + nextIds +
                ", inValues=" + inValues +
                ", magicValue=" + magicValue +
                '}';
    }
}
