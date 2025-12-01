package dec01;

import framework.InputLoaderParent;

import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<Integer> loadCommands() {
        return loadLines().stream().map(line -> {
            char direction = line.charAt(0);
            int value = Integer.valueOf(line.substring(1));
            if (direction == 'L') {
                value *= -1;
            }
            return value;
        }).toList();
    }
}
