package commandline;

public class CommandExecutor {
    private commands command;

    public void setCommand(commands command) {
        this.command = command;
    }

    public void executeCommand() throws Exception {
        command.execute();
    }
}
