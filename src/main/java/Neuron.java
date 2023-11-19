import java.util.ArrayList;
import java.util.List;

public class Neuron {
    public final int id;
    public final Integer nextId;
    private final List<Double> list = new ArrayList<>();
    private final double magicValue;

    public Neuron(int id, Integer nextId, double magicValue) {
        this.id = id;
        this.nextId = nextId;
        this.magicValue = magicValue;
    }

    public void addInput(double value){
        list.add(value);
    }
    public double operation(){
        return magicValue * (sumElements() % 1);
    }

    private Double sumElements(){
        Double r = 0d;
        for (Double v: list) {
            r += v;
        }
        return r;
    }

    @Override
    public String toString() {
        return "Neuron{" +
                "id=" + id +
                ", nextId=" + nextId +
                ", list=" + list +
                ", magicValue=" + magicValue +
                '}';
    }
}
