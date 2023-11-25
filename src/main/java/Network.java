import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Network {
    private final String SPLITTER = "->";
    private final int ORIGIN_INDEX = 0;
    private final int DESTINATION_INDEX = 1;

    // TODO: add layers
    public final Map<Integer, Neuron> neuronMap = new HashMap<Integer, Neuron>();

    public Network() {
    }

    public Double compute(List<Double> inputs) {

        return null;
    }

    public Network fromFile(Path filePath) throws IOException {
        Network network = new Network();

        Files.readAllLines(filePath).forEach((line) -> {
            String[] values = line.split(SPLITTER);
            Integer origin = Integer.valueOf(values[ORIGIN_INDEX]);
            Integer destination = Integer.valueOf(values[DESTINATION_INDEX]);

            if (!network.neuronMap.containsKey(origin)) {
                neuronMap.put(origin, new Neuron());
            }
            if (!network.neuronMap.containsKey(destination)) {
                neuronMap.put(destination, new Neuron());
            }

            neuronMap.get(origin).nextIds.add(destination);

        });

        return network;
    }
}