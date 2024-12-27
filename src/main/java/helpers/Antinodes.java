package helpers;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Slf4j
public class Antinodes {
    private final Grid grid;
    @Getter
    private final HashMap<Character, List<Coordinate>> antennas = new HashMap<>();
    @Getter
    private final HashSet<Coordinate> antinodes = new HashSet<>();

    private static final char EMPTY_SYMBOL = '.';
    private static final char NODE_SYMBOL = '#';


    public Antinodes(Grid grid) {
        this.grid = grid;
        findAntennas();
    }

    public Antinodes(List<String> lines) {
        this.grid = new Grid(lines);
        findAntennas();
    }

    public Antinodes(String input) {
        this.grid = new Grid(input);
        findAntennas();
    }

    private void findAntennas() {
        for (int y = 0; y < this.grid.getHeight(); y++) {
            for (int x = 0; x < this.grid.getWidth(); x++) {
                char symbol = this.grid.get(x, y);
                if (symbol != EMPTY_SYMBOL && symbol != NODE_SYMBOL) {
                    antennas.putIfAbsent(symbol, new ArrayList<>());
                    antennas.get(symbol).add(new Coordinate(x, y));
                }
            }
        }
    }

    public void findAntinodes() {
        this.antennas.keySet().forEach(antennas -> {
            int numberOfAntennas = this.antennas.get(antennas).size();
            if (numberOfAntennas > 1) {
                for (int i = 0; i < numberOfAntennas - 1; i++) {
                    for (int j = i + 1; j < numberOfAntennas; j++) {
                        Coordinate first = this.antennas.get(antennas).get(i);
                        Coordinate second = this.antennas.get(antennas).get(j);
                        Coordinate delta = second.minus(first);

                        Coordinate firstAntinode = first.minus(delta);
                        Coordinate secondAntinode = second.plus(delta);


                        if (!this.grid.isPositionOutOfBounds(firstAntinode)) {
                            this.antinodes.add(firstAntinode);
                        }

                        if (!this.grid.isPositionOutOfBounds(secondAntinode)) {
                            this.antinodes.add(secondAntinode);
                        }
                    }
                }
            }
        });
    }

    public void findManyAntinodes() {
        this.antennas.keySet().forEach(antennas -> {
            int numberOfAntennas = this.antennas.get(antennas).size();
            if (numberOfAntennas > 1) {
                for (int i = 0; i < numberOfAntennas - 1; i++) {
                    for (int j = i + 1; j < numberOfAntennas; j++) {
                        Coordinate first = this.antennas.get(antennas).get(i);
                        Coordinate second = this.antennas.get(antennas).get(j);
                        Coordinate delta = second.minus(first);

                        Coordinate negative = new Coordinate(first);
                        Coordinate positive = new Coordinate(second);

                        do {
                            this.antinodes.add(negative);
                            negative = negative.minus(delta);
                        } while (!this.grid.isPositionOutOfBounds(negative));

                        do {
                            this.antinodes.add(positive);
                            positive = positive.plus(delta);
                        } while (!this.grid.isPositionOutOfBounds(positive));
                    }
                }
            }
        });
    }
}
