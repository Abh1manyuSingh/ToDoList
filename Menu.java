import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {

    static List<ToDoList> topicTodoLIst = new ArrayList<>();
    static ToDoList currentList;

    public static void main(String[] args) {
        // Run tests first
        new Test().testAll();
        System.out.println("Press Enter to start menu...");
        new Scanner(System.in).nextLine();
        run();
    }

    private static void run() {
        boolean quit = false;
        String mainMenu = "" +
                "1. Add a topic\n" +
                "2. Select a topic\n" +
                "3. Assign Task\n" +
                "4. Assign deadline\n" +
                "5. Create a task\n" +
                "6. Mark as done\n" +
                "7. Remove task\n" +
                "8. Rename task\n" +
                "9. Display\n" +
                "10. Quit\n";
        Scanner input = new Scanner(System.in);
        while (!quit) {
            System.out.println(mainMenu);
            int choice = input.nextInt();
            input.nextLine(); // consume newline
            switch (choice) {
                case 1: addTopic(); break;
                case 2: selectTopic(input); break;
                case 3: assignTask(input); break;
                case 4: assignDeadline(input); break;
                case 5: if (currentList != null) currentList.createTask(); break;
                case 6: markAsDone(input); break;
                case 7: removeTask(input); break;
                case 8: renameTask(input); break;
                case 9: if (currentList != null) currentList.display(); break;
                case 10: quit = true; break;
                default: System.out.println("Invalid entry try again");
            }
        }
    }

    private static void addTopic() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter topic name");
        String name = sc.nextLine();
        for (ToDoList t : topicTodoLIst) {
            if (t.topic.equals(name)) {
                System.out.println("Topic already exists. Try again");
                return;
            }
        }
        topicTodoLIst.add(new ToDoList(name));
        System.out.println("Topic added successfully");
    }

    private static void selectTopic(Scanner sc) {
        System.out.println("Enter topic name:");
        String name = sc.nextLine();
        for (ToDoList t : topicTodoLIst) {
            if (t.topic.equalsIgnoreCase(name)) {
                currentList = t;
                System.out.println("Topic selected.");
                return;
            }
        }
        System.out.println("Topic not found.");
    }

    private static void assignTask(Scanner sc) {
        if (currentList == null) { System.out.println("Select a topic first."); return; }
        System.out.println("Enter task name:");
        String tname = sc.nextLine();
        System.out.println("Enter employee name:");
        String ename = sc.nextLine();
        if (currentList.assignTask(tname, ename)) {
            System.out.println("Task assigned.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void assignDeadline(Scanner sc) {
        if (currentList == null) { System.out.println("Select a topic first."); return; }
        System.out.println("Enter task name:");
        String tname = sc.nextLine();
        System.out.println("Enter deadline (dd/MM/yyyy):");
        try {
            Date d = new SimpleDateFormat("dd/MM/yyyy").parse(sc.nextLine());
            if (currentList.assignDeadLine(tname, d)) {
                System.out.println("Deadline assigned.");
            } else {
                System.out.println("Task not found.");
            }
        } catch (ParseException e) {
            System.out.println("Invalid date format.");
        }
    }

    private static void markAsDone(Scanner sc) {
        if (currentList == null) { System.out.println("Select a topic first."); return; }
        System.out.println("Enter task name:");
        String tname = sc.nextLine();
        if (currentList.markAsDone(tname)) {
            System.out.println("Task marked as done.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void removeTask(Scanner sc) {
        if (currentList == null) { System.out.println("Select a topic first."); return; }
        System.out.println("Enter task name:");
        String tname = sc.nextLine();
        if (currentList.removeTask(tname)) {
            System.out.println("Task removed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private static void renameTask(Scanner sc) {
        if (currentList == null) { System.out.println("Select a topic first."); return; }
        System.out.println("Enter old task name:");
        String oldName = sc.nextLine();
        System.out.println("Enter new task name:");
        String newName = sc.nextLine();
        if (currentList.renameTask(oldName, newName)) {
            System.out.println("Task renamed.");
        } else {
            System.out.println("Task not found.");
        }
    }
}