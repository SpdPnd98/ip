package duke;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Event are Tasks that have an attached date as the time the Task will happen.
 */
public class Event extends Task{
    private Date date;
    protected static final String EVENT_LABEL = "E";

    /**
     * Creates an Event instance.
     *
     * @param str the commands used to initialise an Event instance.
     * @throws DukeDateParseException when there is an error parsing the date provided.
     * @throws DukeArgumentException when there is insufficient/incorrect argument(s) provided.
     */
    public Event(String str) throws DukeDateParseException, DukeArgumentException {
        super(str.split(" /", 2)[0]);
        String[] command = str.split(" /", 2);
        if (command.length == 1) {
            throw new DukeArgumentException("No commands specified for task 'deadline'!");
        }
        String[] commandAndDate = command[1].split(" ", 2);
        if(!commandAndDate[0].equals("at")) {
            throw new DukeArgumentException("Unknown command provided to duke.Event! did you use '/at'?");
        } else if (commandAndDate.length == 1) {
            throw new DukeArgumentException("No date specified!");
        }
        try {
            this.date = new SimpleDateFormat("dd/mm/yyyy").parse(commandAndDate[1]);
        } catch (ParseException e) {
            throw new DukeDateParseException(e);
        }
    }

    @Override
    protected String getTaskType() {
        return EVENT_LABEL;
    }

    @Override
    protected String getDate() {
        return this.date.toString();
    }

    @Override
    public String toString() {
        return "[" + EVENT_LABEL + "]" + super.toString() + " (at: " + date + ")";
    }
}
