import java.util.Date;

/**
 * Represents a task with a name, deadline, and assigned employee.
 */
public class Task {
    private String name;
    private Date deadline;
    private String employee;
    private boolean done;

    /**
     * Creates a new Task.
     * @param name the task name
     */
    public Task(String name) {
        this.name = name;
        this.deadline = null;
        this.employee = null;
        this.done = false;
    }

    /** @return the task name */
    public String getName() {
        return name;
    }

    /** @param name the new task name */
    public void setName(String name) {
        this.name = name;
    }

    /** @return the deadline */
    public Date getDeadline() {
        return deadline;
    }

    /** @param deadline the due date */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /** @return the assigned employee */
    public String getEmployee() {
        return employee;
    }

    /** @param employee the person assigned to the task */
    public void setEmployee(String employee) {
        this.employee = employee;
    }

    /** Marks the task as done. */
    public void markDone() {
        this.done = true;
    }

    /** @return true if the task is completed */
    public boolean isDone() {
        return done;
    }

    /** Displays task details as a string. */
    public String toString() {
        String deadlineStr = (deadline != null) ? deadline.toString() : "No deadline";
        String employeeStr = (employee != null) ? employee : "Unassigned";
        String statusStr = done ? "Done" : "Pending";
        return name + " | " + deadlineStr + " | " + employeeStr + " | " + statusStr;
    }
}