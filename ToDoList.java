import java.util.ArrayList;
import java.util.List;

/**
 * Represents a to-do list with a topic and tasks.
 */
public class ToDoList {
    private String topic;
    private List<Task> tasks;

    /**
     * Creates a new to-do list.
     * @param topic the name of the list
     */
    public ToDoList(String topic) {
        this.topic = topic;
        this.tasks = new ArrayList<>();
    }

    /** @return the topic name */
    public String getTopic() {
        return topic;
    }

    /** @param topic sets a new topic */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /** Adds a new task to the list. */
    public boolean addTask(Task task) {
        for (Task t : tasks) {
            if (t.getName().equalsIgnoreCase(task.getName())) {
                return false; // duplicate name
            }
        }
        tasks.add(task);
        return true;
    }

    /** @return the list of tasks */
    public List<Task> getTasks() {
        return tasks;
    }

    /** Finds a task by name. */
    public Task findTask(String name) {
        for (Task t : tasks) {
            if (t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }
}