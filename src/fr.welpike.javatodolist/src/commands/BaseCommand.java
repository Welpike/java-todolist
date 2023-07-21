package commands;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class BaseCommand {
    protected String name;
    protected Pattern regexPattern;

    public BaseCommand(String name, String regex) {
        this.name = name;
        this.regexPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public void run(String args) {
        System.out.println(this.name + ": " + args);
    }

    /**
     * regex
     * NewCommand : new.\{.*\}
     * EditCommand : edit.\{.*\}.[0-9]+
     * DeleteCommand : delete.[0-9]+
     * HelpCommand : help.\{.*\}
     */
    protected boolean verifyArgs(String args) {
        Matcher matcher = this.regexPattern.matcher(args);
        return matcher.matches();
    }
}
