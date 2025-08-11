import java.util.*;

/**
 * Menu for managing to-do lists and tasks.
 */
public class Menu {

    private List<ToDoList> lists = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Test test = new Test();
        test.testAll();

        Menu menu = new Menu();
        menu.run();
    }

    /** Runs the menu loop. */
    public void run() {
        while (true) {
            System.out.println("\n--- TO-DO LIST MENU ---");
            System.out.println("1. Create new to-do list");
            System.out.println("2. Add task to list");
            System.out.println("3. Edit task");
            System.out.println("4. Mark task as done");
            System.out.println("5. Display tasks");
            System.out.println("0. Quit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1": createList(); break;
                case "2": addTaskToList(); break;
                case "3": editTask(); break;
                case "4": markTaskDone(); break;
                case "5": displayTasks(); break;
                case "0": return;
                default: System.out.println("Invalid option!");
            }
        }
    }

    private void createList() {
        System.out.print("Enter topic: ");
        String topic = scanner.nextLine();
        for (ToDoList l : lists) {
            if (l.getTopic().equalsIgnoreCase(topic)) {
                System.out.println("List already exists!");
                return;
            }
        }
        lists.add(new ToDoList(topic));
        System.out.println("List created.");
    }

    private void addTaskToList() {
        ToDoList list = chooseList();
        if (list == null) return;

        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        Task task = new Task(name);

        System.out.print("Add deadline? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            task.setDeadline(new Date()); // for simplicity, set current date
        }

        System.out.print("Assign employee? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter employee name: ");
            task.setEmployee(scanner.nextLine());
        }

        if (list.addTask(task)) {
            System.out.println("Task added.");
        } else {
            System.out.println("Task name already exists in this list!");
        }
    }

    private void editTask() {
        ToDoList list = chooseList();
        if (list == null) return;

        System.out.print("Enter task name to edit: ");
        Task task = list.findTask(scanner.nextLine());
        if (task == null) {
            System.out.println("Task not found!");
            return;
        }

        System.out.print("New name (leave blank to keep): ");
        String newName = scanner.nextLine();
        if (!newName.isEmpty()) task.setName(newName);

        System.out.print("Change deadline? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            task.setDeadline(new Date());
        }

        System.out.print("Change employee? (y/n): ");
        if (scanner.nextLine().equalsIgnoreCase("y")) {
            System.out.print("Enter employee: ");
            task.setEmployee(scanner.nextLine());
        }
        System.out.println("Task updated.");
    }

    private void markTaskDone() {
        ToDoList list = chooseList();
        if (list == null) return;

        System.out.print("Enter task name: ");
        Task task = list.findTask(scanner.nextLine());
        if (task != null) {
            task.markDone();
            System.out.println("Task marked as done.");
        } else {
            System.out.println("Task not found.");
        }
    }

    private void displayTasks() {
        ToDoList list = chooseList();
        if (list != null) {
            for (Task t : list.getTasks()) {
                System.out.println(t);
            }
        }
    }

    private ToDoList chooseList() {
        if (lists.isEmpty()) {
            System.out.println("No lists available!");
            return null;
        }
        System.out.println("Available lists:");
        for (int i = 0; i < lists.size(); i++) {
            System.out.println((i + 1) + ". " + lists.get(i).getTopic());
        }
        System.out.print("Choose list: ");
        int index;
        try {
            index = Integer.parseInt(scanner.nextLine()) - 1;
            return (index >= 0 && index < lists.size()) ? lists.get(index) : null;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
            return null;
        }
    }
}