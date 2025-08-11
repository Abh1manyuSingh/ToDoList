import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ToDoList {

    String topic;
    List<Task> tasks = new ArrayList<>();

    public ToDoList(String topicName) {
        topic = topicName;
    }

    public void createTask() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter task name");
        String name = sc.nextLine();
        System.out.println("Please enter employee name");
        String empname = sc.nextLine();
        System.out.println("Please enter due date in format dd/MM/yyyy");
        Date d = null;
        try {
            d = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Task t = new Task();
        t.setName(name);
        t.setDeadLine(d);
        t.setEmployee(empname);
        t.setDone(false);
        tasks.add(t);
        System.out.println("Task added successfully.");
    }

    public boolean assignTask(String taskName, String employeeName) {
        for (Task t : tasks) {
            if (t.getName().equalsIgnoreCase(taskName)) {
                t.setEmployee(employeeName);
                return true;
            }
        }
        return false;
    }

    public boolean assignDeadLine(String taskName, Date deadline) {
        for (Task t : tasks) {
            if (t.getName().equalsIgnoreCase(taskName)) {
                t.setDeadLine(deadline);
                return true;
            }
        }
        return false;
    }

    public void display() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            for (Task t : tasks) {
                System.out.println(t);
            }
        }
    }

    public boolean markAsDone(String taskName) {
        for (Task t : tasks) {
            if (t.getName().equalsIgnoreCase(taskName)) {
                t.setDone(true);
                return true;
            }
        }
        return false;
    }

    public boolean removeTask(String taskName) {
        return tasks.removeIf(t -> t.getName().equalsIgnoreCase(taskName));
    }

    public boolean renameTask(String oldName, String newName) {
        for (Task t : tasks) {
            if (t.getName().equalsIgnoreCase(oldName)) {
                t.setName(newName);
                return true;
            }
        }
        return false;
    }
}