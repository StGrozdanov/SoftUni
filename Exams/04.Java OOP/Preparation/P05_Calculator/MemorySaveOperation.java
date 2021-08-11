package ExamPreparation.P05_Calculator;

import java.util.ArrayDeque;

public class MemorySaveOperation implements Operation {

    private static ArrayDeque<Integer> memory = new ArrayDeque<>();

    @Override
    public void addOperand(int operand) {
        memory.push(operand);
    }

    public static ArrayDeque<Integer> getMemory() {
        return memory;
    }

    @Override
    public int getResult() {
        return memory.peek();
    }

    @Override
    public boolean isCompleted() {
        return false;
    }
}
