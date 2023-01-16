package model.enumerations;

public enum Instruction {
    MOVE("A"),
    RIGHT("D"),
    LEFT("G");

    private String instruction;

    Instruction(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return this.instruction;
    }
}
