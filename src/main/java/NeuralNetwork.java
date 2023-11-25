import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeuralNetwork {
    private final String SPLITTER = "->";
    private final String COMMENT_SYMBOL = "#";
    private final int ORIGIN_INDEX = 0;
    private final int DESTINATION_INDEX = 1;

    // TODO: add layers
    public final Map<Integer, Neuron> neuronMap = new HashMap<Integer, Neuron>();

    public NeuralNetwork() {
    }

    /**
     * Uses the input values to compute an output value with the network.
     * ASSUMING IDs are [1..size] and order of execution is by the order of the ids.
     * Doesnt allow multiple outputs
     */
    public Double compute(List<Double> inputs) {
        // Insert inputs into input neurons
        for (int i = 0; i < inputs.size(); i++) {
            neuronMap.get(i + 1).addInput(inputs.get(i));
        }

        // Computes
        for (int i = 1; i < neuronMap.size(); i++) {
            Double result = neuronMap.get(i).operation();
            neuronMap.get(i).nextIds.forEach(id -> neuronMap.get(id).addInput(result));
        }

        // Computes final value
        return neuronMap.get(neuronMap.size()).operation();
    }

    /**
     * Constructs a Neural network from a file.
     * 
     * @throws IOException
     */
    public NeuralNetwork fromFile(Path filePath) throws IOException {
        NeuralNetwork network = new NeuralNetwork();

        Files.readAllLines(filePath).forEach((line) -> {
            if (line.startsWith(COMMENT_SYMBOL))
                return;

            String[] values = line.split(SPLITTER);
            Integer origin = Integer.valueOf(values[ORIGIN_INDEX]);
            Integer destination = Integer.valueOf(values[DESTINATION_INDEX]);

            if (!network.neuronMap.containsKey(origin)) {
                neuronMap.put(origin, new Neuron());
            }
            if (!network.neuronMap.containsKey(destination)) {
                neuronMap.put(destination, new Neuron());
            }

            network.neuronMap.get(origin).nextIds.add(destination);

        });

        return network;
    }
}