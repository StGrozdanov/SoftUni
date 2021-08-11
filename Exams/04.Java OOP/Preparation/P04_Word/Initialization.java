package ExamPreparation.P04_Word;

public class Initialization {
    public static CommandInterface buildCommandInterface(StringBuilder text) {
        CommandImpl cmd = new CommandImpl(text);
        cmd.init();
        return cmd;
    }
}
