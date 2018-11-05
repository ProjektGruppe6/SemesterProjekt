package worldofzuul;

public enum CommandWord
{
    GO("Go"), QUIT("Quit"), HELP("GetHelp"), MATERIALS("GetMaterial"), INSTRUCTOR("GetInstructor"),TEST("GetTest"), NOTES("GetNotes"),COURSEINFO("GetCourseInfo"),UNKNOWN("?");
    
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
