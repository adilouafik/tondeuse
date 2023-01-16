package model.enumerations;

import model.Position;

import java.util.Arrays;
import java.util.function.Consumer;
import static model.enumerations.Instruction.*;
import static model.enumerations.Orientation.*;

public enum Rules {
    MOVE_NORD(MOVE.getInstruction(), NORD.getOrientation(), position -> position.setY(position.getY() + 1)),
    MOVE_SUD(MOVE.getInstruction(), SUD.getOrientation(), position -> position.setY(position.getY() - 1)),
    MOVE_EST(MOVE.getInstruction(), EST.getOrientation(), position -> position.setX(position.getX() + 1)),
    MOVE_OUEST(MOVE.getInstruction(), OUEST.getOrientation(), position -> position.setX(position.getX() - 1)),

    CHANGE_DIRECTION_RIGHT_NORD(RIGHT.getInstruction(), NORD.getOrientation(), position -> position.setOrientation(EST.getOrientation())),
    CHANGE_DIRECTION_RIGHT_SUD(RIGHT.getInstruction(), SUD.getOrientation(), position -> position.setOrientation(OUEST.getOrientation())),
    CHANGE_DIRECTION_RIGHT_EST(RIGHT.getInstruction(), EST.getOrientation(), position -> position.setOrientation(SUD.getOrientation())),
    CHANGE_DIRECTION_RIGHT_OUEST(RIGHT.getInstruction(), OUEST.getOrientation(), position -> position.setOrientation(NORD.getOrientation())),

    CHANGE_DIRECTION_LEFT_NORD(LEFT.getInstruction(), NORD.getOrientation(), position -> position.setOrientation(OUEST.getOrientation())),
    CHANGE_DIRECTION_LEFT_SUD(LEFT.getInstruction(), SUD.getOrientation(), position -> position.setOrientation(EST.getOrientation())),
    CHANGE_DIRECTION_LEFT_EST(LEFT.getInstruction(), EST.getOrientation(), position -> position.setOrientation(NORD.getOrientation())),
    CHANGE_DIRECTION_LEFT_OUEST(LEFT.getInstruction(), OUEST.getOrientation(), position -> position.setOrientation(SUD.getOrientation()));

    private String instruction;
    private String orientation;
    private Consumer<Position> operation;

    Rules(String instruction, String orientation, Consumer<Position> operation) {
        this.instruction = instruction;
        this.orientation = orientation;
        this.operation = operation;
    }

    public static Consumer<Position> executeInstruction(String instruction, String orientation){
        return Arrays.stream(Rules.values())
                .filter(v -> v.instruction.equalsIgnoreCase(instruction))
                .filter(v -> v.orientation.equalsIgnoreCase(orientation))
                .findAny()
                .get().operation;
    }
}
