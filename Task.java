package TaskManager;

public class Task {
     private int id;
     private String title;
     private String description;
     private boolean completed;
     public Task(int id, String title, String description){
         this.id = id;
         this.title = title;
         this.description = description;
         this.completed = false;
     }
    public void setCompleted() {
        completed = true;
    }
    public boolean isCompleted(){
         return completed;
    }
    public int getId(){
         return id;
    }
    public void setId(int id){
         this.id = id;
    }
    public String getName(){
         return title;
    }
    public String getDescription(){
         return description;
    }

}
