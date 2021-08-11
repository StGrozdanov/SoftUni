package ExamPreparation.P04_Word;

public class ToPasteTransform implements TextTransform {
    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {

        String memorySequence = ToCutTransform.getMemory().peek().toString();

        if (startIndex == endIndex) {
            text.insert(startIndex, memorySequence);
        } else {
            text.replace(startIndex, endIndex, memorySequence);
        }
    }
}
