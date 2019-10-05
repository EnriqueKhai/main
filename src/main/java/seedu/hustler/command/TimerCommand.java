package seedu.hustler.command;

import seedu.hustler.Hustler;
import seedu.hustler.ui.Ui;
import seedu.hustler.ui.timer.*;

public class TimerCommand extends Command {
    /**
     * Contains task type and description.
     */
    private String[] taskInfo;

    /**
     * Initializes taskInfo.
     *
     * @param taskInfo the info of the task to add.
     */
    public TimerCommand(String[] taskInfo) {
        this.taskInfo = taskInfo;
    }

    public void execute() {
        if (this.taskInfo.length == 1) {
            Ui ui = new Ui();
            ui.empty_description_error();
            return;
        }

        timerManager Jackie = new timerManager();
        Jackie.setTimer(taskInfo[1]);
        Jackie.startTimer();
    }
}
