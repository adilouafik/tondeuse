package model;

public class Tondeuse {
    Position position;
    Pelouse pelouse;

    public Tondeuse(Position position, Pelouse pelouse) {
        this.position = position;
        this.pelouse = pelouse;
    }

    private Position move(Position  position, String instruction) {
        return null;
    }

    private boolean isOutOfBound(int x, int y, Pelouse pelouse) {
        return false;
    }
}
