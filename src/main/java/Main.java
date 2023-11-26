import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        final double ACESO = 0.99999999;
        final double APAGADO = 0.0;

        NeuralNetwork neuralN = null;
        neuralN =  NeuralNetwork.fromFile(Path.of("network_sample.txt"));
        double value = neuralN.compute(List.of(APAGADO,ACESO,APAGADO));
        System.out.println(value);

    }
}
