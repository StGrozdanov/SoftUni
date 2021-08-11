package ExamPreparation.P04_Word;

import java.util.ArrayDeque;

public class ToCutTransform implements TextTransform {

    private static ArrayDeque<StringBuilder> memory = new ArrayDeque<>();

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        memory.clear();
        memory.push(new StringBuilder().append(text.substring(startIndex, endIndex)));
        text.delete(startIndex, endIndex);
    }

    public static ArrayDeque<StringBuilder> getMemory() {
        return memory;
    }
}
