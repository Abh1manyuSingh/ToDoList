import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    ToDoList list = new ToDoList("TestTopic");

    public static void main(String[] args) {
        Test t = new Test();
        t.testAll();
    }

    private void testCreateTask() {
        int before = list.tasks.size();
        Task t = new Task();
        t.setName("Task1");
        t.setEmployee("John");
        t.setDeadLine(new Date());
        list.tasks.add(t);
        if (list.tasks.size() == before + 1) {
            log("Create Task test passed");
        } else {
            log("Create Task test failed");
        }
    }

    private void testAssignTask() {
        list.assignTask("Task1", "Mike");
        if ("Mike".equals(list.tasks.get(0).getEmployee())) {
            log("Assign Task test passed");
        } else {
            log("Assign Task test failed");
        }
    }

    private void testAssignDeadLine() {
        Date d = new Date();
        list.assignDeadLine("Task1", d);
        if (d.equals(list.tasks.get(0).getDeadLine())) {
            log("Assign Deadline test passed");
        } else {
            log("Assign Deadline test failed");
        }
    }

    private void testMarkAsDone() {
        list.markAsDone("Task1");
        if (list.tasks.get(0).isDone()) {
            log("Mark As Done test passed");
        } else {
            log("Mark As Done test failed");
        }
    }

    private void testRemoveTask() {
        list.removeTask("Task1");
        if (list.tasks.isEmpty()) {
            log("Remove Task test passed");
        } else {
            log("Remove Task test failed");
        }
    }

    private void testRenameTask() {
        Task t = new Task();
        t.setName("OldName");
        list.tasks.add(t);
        list.renameTask("OldName", "NewName");
        if ("NewName".equals(list.tasks.get(0).getName())) {
            log("Rename Task test passed");
        } else {
            log("Rename Task test failed");
        }
    }

    public void testAll() {
        try {
            writer = new FileWriter("testsResults.txt");
            testCreateTask();
            testAssignTask();
            testAssignDeadLine();
            testMarkAsDone();
            testRemoveTask();
            testRenameTask();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static FileWriter writer;

    private static void log(String message) {
        System.out.println(message);
        try {
            if (writer != null) {
                writer.write(message + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}