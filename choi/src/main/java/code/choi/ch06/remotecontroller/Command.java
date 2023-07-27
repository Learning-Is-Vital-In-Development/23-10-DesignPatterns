package code.choi.ch06.remotecontroller;

public interface Command {
    public void execute();

    public void undo();
}
