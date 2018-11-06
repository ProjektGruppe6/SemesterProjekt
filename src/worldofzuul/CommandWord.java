package worldofzuul;

public enum CommandWord
{
    GO("go"), QUIT("quit"), HELP("help"), MATERIALS("material"), UNKNOWN("?"), MAP("map");
    
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
