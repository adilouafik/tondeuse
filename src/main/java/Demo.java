import model.Constants;
import model.Pelouse;
import model.Position;
import model.Tondeuse;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {


    public static void main(String[] args) {

        try {
            final List<String> lines = Files.readAllLines(
                    Paths.get(ClassLoader.getSystemResource(Constants.FILE_NAME)
                            .toURI()));

            Map<String, String> map = getInstructionsFromLines(lines);
            Pelouse pelouse = getPelouse(lines);

            map.forEach((lineOfPosition, instruction) -> {
                Position initialPosition = getIntialPosition(lineOfPosition);
                Tondeuse tondeuse = new Tondeuse(initialPosition, pelouse);

                instruction.chars().forEach(i -> {
                    Position nextPosition = tondeuse.move(initialPosition, String.valueOf((char)i));
                    if(!tondeuse.isOutOfBound(nextPosition.getX(),
                            nextPosition.getY(),
                            pelouse)) {
                        initialPosition.setX(nextPosition.getX());
                        initialPosition.setY(nextPosition.getY());
                        initialPosition.setOrientation(nextPosition.getOrientation());
                    }
                });

                System.out.println(initialPosition);
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private static Position getIntialPosition(String lineOfPosition) {
        Position initialPosition = new Position(Integer.parseInt(lineOfPosition.split(Constants.ESPACE)[0]),
                Integer.parseInt(lineOfPosition.split(Constants.ESPACE)[1]),
                lineOfPosition.split(Constants.ESPACE)[2]);
        return initialPosition;
    }

    private static Pelouse getPelouse(List<String> lines) {
        String lineOne = lines.get(0);
        Pelouse pelouse = new Pelouse(Integer.parseInt(lineOne.split(Constants.ESPACE)[0]),
                Integer.parseInt(lineOne.split(Constants.ESPACE)[1]));
        return pelouse;
    }

    private static Map<String, String> getInstructionsFromLines(List<String> lines) {
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i < lines.size(); i+=2) {
            map.put(lines.get(i), lines.get(i+1));
        }
        return map;
    }
}
