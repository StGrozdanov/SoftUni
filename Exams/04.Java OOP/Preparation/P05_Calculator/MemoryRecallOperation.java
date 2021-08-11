package ExamPreparation.P05_Calculator;

public class MemoryRecallOperation implements Operation {

    @Override
    public void addOperand(int operand) {
    }

    @Override
    public int getResult() {
        return MemorySaveOperation.getMemory().pop();
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
