package dec03;

import framework.InputLoaderParent;

import java.util.Arrays;
import java.util.List;

public class InputLoader extends InputLoaderParent {

    public static List<List<Integer>> loadBanks() {
        List<String> lines = loadLines();
        return lines.stream().map(line -> Arrays.stream(line.split("")).map(Integer::parseInt).toList())
                .toList();
    }

}
