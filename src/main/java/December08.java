import helpers.Antinodes;
import helpers.Coordinate;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;

@Slf4j
public class December08 {
    public static void main(String[] args) {
        December08 december08 = new December08();

        int resultA = december08.runA("December08.txt");
        System.out.println(resultA);

        int resultB = december08.runB("December08.txt");
        System.out.println(resultB);
    }

    private int runA(String input) {
        Antinodes antinodes = new Antinodes(input);
        antinodes.findAntinodes();

        return antinodes.getAntinodes().size();
    }

    private int runB(String input) {
        Antinodes antinodes = new Antinodes(input);
        antinodes.findManyAntinodes();

        return antinodes.getAntinodes().size();
    }

}
