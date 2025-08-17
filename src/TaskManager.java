import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;


public class TaskManager {
    private List<Task> taskList;
    private int nextID;


    TaskManager(){
        taskList = new ArrayList<>();
        nextID = 1;

    }

    public int getNextID(){
        return nextID++;
    }

    public void addTask(String description){
        int id = getNextID();
        String status = "todo";
        LocalDateTime dateTime = LocalDateTime.now();

        Task newTask = new Task(id, description, status, dateTime, dateTime);
        taskList.add(newTask);

    }

    public void updateTaskDescription(int id, String newDescription){
        boolean found = false;
        for(Task task: taskList){
            if(task.getId() == id){
                task.setDescription(newDescription);
                task.setUpdatedAt(LocalDateTime.now());
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No task found with ID " + id);
        }
        else{
            System.out.println("Task updated successfully.");
        }
    }

    public void updateTaskStatus(int id, String newStatus){
        boolean found = false;
        if(!newStatus.equalsIgnoreCase("todo") && !newStatus.equalsIgnoreCase("in-progress") && !newStatus.equalsIgnoreCase("done")){
            System.out.println("Allowed status: todo, in-progress, done");
            return;
        }
        for (Task task : taskList) {
            if (task.getId() == id) {
                task.setStatus(newStatus);
                task.setUpdatedAt(LocalDateTime.now());

                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No task found with ID " + id);
        }
        else{
            System.out.println("Status updated successfully.");
        }

    }

    public void deleteTask(int id){
        boolean found = false;
        for(int i = 0; i < taskList.size(); i++){
            if(taskList.get(i).getId() == id){
                taskList.remove(i);
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("No task found with ID " + id);
        }
        else{
            System.out.println("Task deleted successfully.");
        }
    }

    public void listTasksByStatus(String status){
        boolean found = false;
        for(Task task : taskList){
            if(task.getStatus().equalsIgnoreCase(status)){
                System.out.println(task);
                found = true;
            }
        }
        if(!found){
            System.out.println("No task found with status " + status);
        }
    }

    public void listTask(){
        if(taskList.isEmpty()){
            System.out.println("No task available");
        }
        for(Task task: taskList){
            System.out.println(task);
        }
    }

    public void saveTasksToFile(){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
            FileWriter writeFile = new FileWriter("tasks.json");
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(int i = 0; i < taskList.size(); i++){
                Task task = taskList.get(i);
                sb.append("{");
                sb.append("\"id\":").append(task.getId()).append(",");
                sb.append("\"description\": \"").append(task.getDescription()).append("\", ");
                sb.append("\"status\": \"").append(task.getStatus()).append("\", ");
                sb.append("\"created at\": \"").append(task.getCreatedAt().format(formatter)).append("\", ");
                sb.append("\"updated at\": \"").append(task.getUpdatedAt().format(formatter)).append("\"");
                sb.append("}");

                if(i < taskList.size() - 1)
                    sb.append(",");
            }

            sb.append("]");
            writeFile.write(sb.toString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //?
    private String getValue(String json, String key) {
        String[] parts = json.split("\"" + key + "\"\\s*:\\s*");
        if (parts.length < 2) return "";

        String valuePart = parts[1].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")[0]; // safe split on comma
        valuePart = valuePart.trim();

        // Remove quotes if string
        if (valuePart.startsWith("\"") && valuePart.endsWith("\"")) {
            valuePart = valuePart.substring(1, valuePart.length() - 1);
        }

        return valuePart;
    }


    //?
    public void loadTasksFromFile() {
        String fileName = "tasks.json";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);

            }

            String jsonString = json.toString().trim();
            if (jsonString.isEmpty() || jsonString.equals("[]")) {
                return;
            }

            int start = jsonString.indexOf("[");
            int end = jsonString.indexOf("]");
            if (start == -1 || end == -1) return;

            String arrayData = jsonString.substring(start + 1, end).trim();
            if (arrayData.isEmpty()) return;

            String[] rawTasks = arrayData.split("\\},\\{");
            taskList.clear();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");

            for (int i = 0; i < rawTasks.length; i++) {
                try {
                    String taskStr = rawTasks[i].trim();

                    if (!taskStr.startsWith("{")) taskStr = "{" + taskStr;
                    if (!taskStr.endsWith("}")) taskStr = taskStr + "}";

                    int id = Integer.parseInt(getValue(taskStr, "id"));
                    String description = getValue(taskStr, "description");
                    String status = getValue(taskStr, "status");
                    String createdAtStr = getValue(taskStr, "created at").replace("\"", "").replace("}", "").trim();
                    String updatedAtStr = getValue(taskStr, "updated at").replace("\"", "").replace("}", "").trim();

                    if (createdAtStr.isEmpty() || updatedAtStr.isEmpty()) {
                        System.out.println("WARNING: Skipping task with missing dates.");
                        continue;
                    }

                    LocalDateTime createdAt = LocalDateTime.parse(createdAtStr, formatter);
                    LocalDateTime updatedAt = LocalDateTime.parse(updatedAtStr, formatter);

                    Task task = new Task(id, description, status, createdAt, updatedAt);
                    taskList.add(task);
                } catch (Exception e) {
                    System.out.println("WARNING: Skipping invalid task in JSON file.");
                }
            }

            for (Task tasks : taskList) {
                if (tasks.getId() >= nextID) {
                    nextID = tasks.getId() + 1;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
