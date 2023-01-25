public enum CommandWord
{
    //add commands here (functionality in another class)
    GO("go"), QUIT( "quit"), HELP("help"), UNKNOWN("?"), LOOK("look"), GRAB("grab"), DROP("drop"), APPLY("apply"), READ("read");

    private String commandString;

    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    public String toString()
    {
        return commandString;
    }
}
