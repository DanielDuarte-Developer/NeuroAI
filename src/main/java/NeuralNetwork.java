import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeuralNetwork {
    private static final String CONECTION_SYMBOL = "->";
    private static final String ATRIBUTION_SYMBOL = "=";
    private static final String COMMENT_SYMBOL = "#";
    private static final int ORIGIN_INDEX = 0;
    private static final int DESTINATION_INDEX = 1;
    private static final int MAGIC_VALUE_INDEX = 1;

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
    public static NeuralNetwork fromFile(Path filePath) throws IOException {
        NeuralNetwork network = new NeuralNetwork();

        Files.readAllLines(filePath).forEach((line) -> {
            if (line.startsWith(COMMENT_SYMBOL) || line.isBlank())
                return;

            if (line.contains(CONECTION_SYMBOL)) {
                String[] values = line.split(CONECTION_SYMBOL);
                Integer origin = Integer.valueOf(values[ORIGIN_INDEX]);
                Integer destination = Integer.valueOf(values[DESTINATION_INDEX]);

                if (!network.neuronMap.containsKey(origin))
                    throw new RuntimeException("origin neuron not defined in line: {" + line + "}");
                if (!network.neuronMap.containsKey(destination))
                    throw new RuntimeException("destination neuron not defined in line: {" + line + "}");

                network.neuronMap.get(origin).nextIds.add(destination);
            } else if (line.contains(ATRIBUTION_SYMBOL)) {
                String[] values = line.split(ATRIBUTION_SYMBOL);

                Integer neuronId = Integer.valueOf(values[ORIGIN_INDEX]);
                Double magic_value = Double.valueOf(values[MAGIC_VALUE_INDEX]);

                if (network.neuronMap.containsKey(neuronId)) {
                    throw new RuntimeException("neuron already defined in line {" + line + "}");
                }

                network.neuronMap.put(neuronId, new Neuron(magic_value));

            } else {
                throw new RuntimeException("bad formated line: " + line);
            }

        });

        return network;
    }
}