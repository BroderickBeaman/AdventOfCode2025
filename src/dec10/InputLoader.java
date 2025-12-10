package dec10;

import framework.InputLoaderParent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Machine> loadMachines() {
        List<Machine> machines = new ArrayList<>();

        for (String line : loadLines()) {
            String[] parts = line.split(" ");

            Boolean[] indicators = new Boolean[parts[0].length() - 2];
            for (int i = 0; i < indicators.length; i++) {
                indicators[i] = parts[0].charAt(i + 1) == '#';
            }

            List<List<Integer>> buttons = new ArrayList<>();
            for (int i = 1; i < parts.length - 1; i++) {
                String button = parts[i];
                button = button.substring(1, button.length() - 1);
                String[] buttonParts = button.split(",");
                List<Integer> buttonNums = Arrays.stream(buttonParts).map(Integer::parseInt).toList();
                buttons.add(buttonNums);
            }

            String joltageString = parts[parts.length - 1];
            joltageString = joltageString.substring(1, joltageString.length() - 1);
            String[] joltageParts = joltageString.split(",");
            List<Integer> joltage = Arrays.stream(joltageParts).map(Integer::parseInt).toList();

            machines.add(new Machine(indicators, buttons, joltage));
        }

        return machines;
    }
}
