public class TaskCLI {

    public static void printHelp(){
            System.out.println("Task Tracker - Command Line Usage Guide");
            System.out.println("----------------------------------------");
            System.out.println("add \"description\"           - Add a new task");
            System.out.println("update <id> \"description\"   - Update the description of a task");
            System.out.println("delete <id>                   - Delete a task by ID");
            System.out.println("mark <id> <status>            - Change the status (todo, in-progress, done)");
            System.out.println("list                          - List all tasks");
            System.out.println("list <status>                 - List tasks with a specific status");
            System.out.println("help                          - Show this help message");
            System.out.println();
            System.out.println("Tips:");
            System.out.println("- To enter tasks with multiple words, wrap them in double quotes.");
            System.out.println("  Example: add \"Buy milk and bread\"");
            System.out.println("- When marking tasks, use one of these statuses: todo, in-progress, or done.");
            System.out.println("  Example: mark 3 done");

    }
    public static void main(String[] args) {

        if(args.length == 0) {
            System.out.println("No command provided. Use \"help\" for valid commands and their usage.");
            return;
        }

        TaskManager manager = new TaskManager();
        manager.loadTasksFromFile();

        String command = args[0];
        switch(command){
            case "add":
                if(args.length < 2) {
                    System.out.println("Use \"help\" for valid commands and their usage.");
                    return;
                }
                else {
                    String description = args[1];
                    manager.addTask(description);
                    manager.saveTasksToFile();
                    System.out.println("Task added successfully.");
                }
                break;
            case "update":
                if(args.length < 3) {
                    System.out.println("Use \"help\" for valid commands and their usage.");
                    return;
                }
                else {
                    try{
                        int id = Integer.parseInt(args[1]);
                        String newDescription = args[2];
                        manager.updateTaskDescription(id, newDescription);
                        manager.saveTasksToFile();
                    }
                    catch(NumberFormatException e){
                        System.out.println("Invalid Task ID. Please enter a numeric ID.");
                    }
                }
                break;
            case "delete":
                if(args.length < 2){
                    System.out.println("Use \"help\" for valid commands and their usage.");
                    return;
                }
                else{
                    try{
                        int id = Integer.parseInt(args[1]);
                        manager.deleteTask(id);
                        manager.saveTasksToFile();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Task ID. Please enter a numeric ID.");
                    }
                }
                break;
            case "mark":
                if (args.length < 3){
                    System.out.println("Use \"help\" for valid commands and their usage.");
                    return;
                }
                else{
                    try{
                        int id = Integer.parseInt(args[1]);
                        String newStatus = args[2];
                        manager.updateTaskStatus(id, newStatus);
                        manager.saveTasksToFile();
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Task ID. Please enter a numeric ID.");
                    }
                }
                break;

            case "list":
                if(args.length == 1){
                    manager.listTask();
                    return;
                }
                else{
                    String status = args[1];
                    manager.listTasksByStatus(status);
                }
                break;
            case "help":
                printHelp();
                break;
            default:
                System.out.println("Unknown command: \"" + command + "\"");
                System.out.println("Use \"help\" for valid commands and their usage.");
                break;
        }
    }
}