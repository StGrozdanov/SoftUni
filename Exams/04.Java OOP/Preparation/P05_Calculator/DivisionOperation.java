package ExamPreparation.P05_Calculator;

public class DivisionOperation extends MultiplicationOperation{

    @Override
    public void addOperand(int operand) {
        super.getOperands().add(operand);
        if (super.isCompleted()) {
            super.setResult(super.getOperands().get(0) / super.getOperands().get(1));
        }
    }
}
