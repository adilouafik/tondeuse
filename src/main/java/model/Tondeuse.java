package model;

import model.enumerations.Rules;

public class Tondeuse {
    Position position;
    Pelouse pelouse;

    public Tondeuse(Position position, Pelouse pelouse) {
        this.position = position;
        this.pelouse = pelouse;
    }

    public Position move(Position  position, String instruction) {
        Position newPosition = new Position(position.getX(),
                position.getY(),
                position.getOrientation());

        Rules.executeInstruction(instruction, position.getOrientation())
                .accept(newPosition);

        return newPosition;
    }

    public boolean isOutOfBound(int x, int y, Pelouse pelouse) {
        return x > pelouse.dimenssionA || y > pelouse.dimenssionB  ||  x < 0  || y < 0;
    }
}
