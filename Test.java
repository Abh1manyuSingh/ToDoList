import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Runs basic tests for the application.
 */
public class Test {

    /**
     * Runs all tests and saves results to file.
     */
    public void testAll() {
        StringBuilder results = new StringBuilder();
        results.append(testTaskCreation());
        results.append(testAddTaskToList());
        results.append(testFindTask());
        results.append(testMarkDone());

        // Write to file
        try (FileWriter fw = new FileWriter("testsResults.txt")) {
            fw.write(results.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print to screen
        System.out.println(results);
    }

    private String testTaskCreation() {
        Task t = new Task("Test");
        return t.getName().equals("Test") ? "Task creation test passed\n" : "Task creation test failed\n";
    }

    private String testAddTaskToList() {
        ToDoList list = new ToDoList("Work");
        boolean added = list.addTask(new Task("Email"));
        return added ? "Add task test passed\n" : "Add task test failed\n";
    }

    private String testFindTask() {
        ToDoList list = new ToDoList("Home");
        Task t = new Task("Cook");
        list.addTask(t);
        return list.findTask("Cook") != null ? "Find task test passed\n" : "Find task test failed\n";
    }

    private String testMarkDone() {
        Task t = new Task("Laundry");
        t.markDone();
        return t.isDone() ? "Mark done test passed\n" : "Mark done test failed\n";
    }
}