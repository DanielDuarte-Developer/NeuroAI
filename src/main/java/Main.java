import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        final double ACESO = 0.99999999;
        final double APAGADO = 0.0;

        Neuron neuron1 = new Neuron(Arrays.asList(4), 0.1f);
        Neuron neuron2 = new Neuron(Arrays.asList(4), 1f);
        Neuron neuron3 = new Neuron(Arrays.asList(4), 0.1f);
        Neuron neuronF = new Neuron(null, 1f);

        neuron1.addInput(ACESO);
        Double value1 = neuron1.operation();
        System.out.println(value1);
        neuronF.addInput(value1);

        neuron2.addInput(APAGADO);
        Double value2 = neuron2.operation();
        System.out.println(value2);
        neuronF.addInput(value2);

        neuron3.addInput(ACESO);
        Double value3 = neuron3.operation();
        System.out.println(value3);
        neuronF.addInput(value3);

        System.out.println(neuronF.operation());
        System.out.println(neuronF.operation() > 0.5 ? "Apenas o do meio" : "outros");

        // neuroList.forEach(System.out::println);
    }
}
