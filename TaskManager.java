package TaskManager;

import java.util.ArrayList;
public class TaskManager {
    private ArrayList<Task> tasks;
    public TaskManager(){
        tasks = new ArrayList<>();
        loadFromFile();
    }
    public void addTask(Task task){
        tasks.add(task);
    }
    int nextId = 1;
    public int getNextId(){
        int id = nextId;
        nextId++;
        return id;
    }
    public void completeTask(int id){
        for(int i = 0;i<tasks.size();i++){
            Task task = tasks.get(i);
            if(task.getId() == id){
                task.setCompleted();
                return;
            }
        }
    }
    public void removeTask(int id){
        boolean removed = tasks.removeIf( task -> task.getId() == id);
        if(removed){
            reindexTasks();
            System.out.println("Task removed");
        }
        else{
            System.out.println("Task with this ID not found");
        }
    }
    private void reindexTasks(){
        for(int i =0;i<tasks.size();i++){
            tasks.get(i).setId(i+1);
        }
        nextId = tasks.size()+1;
    }

    public void showCompleted(){
        tasks.stream()
                .filter(task -> task.isCompleted())
                .forEach(task -> System.out.println(task.getName()));
    }
    public void showTask(){
        if(tasks.isEmpty()){
            System.out.println("No tasks yet");
            return;
        }
        for(int i = 0;i < tasks.size();i++){
            Task task = tasks.get(i);
            System.out.println(task.getId() + " | " + task.getName() + " | " + task.getDescription());
        }
    }
    public void saveToFile(){
        try(java.io.FileWriter writer = new java.io.FileWriter("tasks.txt")){
            for(Task task : tasks){
                String line = task.getId() + " | " + task.getName() + " | " + task.getDescription() + " | " + task.isCompleted() + "\n";
                writer.write(line);
            }
        }
        catch(java.io.IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    public void loadFromFile(){
        java.io.File file = new java.io.File("tasks.txt");
        if(!file.exists()){
            return;
        }
        try(java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(file))){
            String line;
            while((line = reader.readLine()) !=null){
                String[] parts = line.split(" \\| ");
                if(parts.length == 4){
                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String description = parts[2];
                    boolean completed = Boolean.parseBoolean(parts[3]);
                    Task task = new Task(id,title, description);
                    if(completed){
                        task.setCompleted();
                    }
                    tasks.add(task);
                    if(id>=nextId){
                        nextId = id+1;
                    }
                }
            }
            System.out.println("Tasks loaded successfully!");
        }
        catch(java.io.IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
