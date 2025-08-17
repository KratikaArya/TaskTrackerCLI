import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private int id;
    private String description;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    Task(int id, String description, String status, LocalDateTime createdAt, LocalDateTime updatedAt){
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public void setDescription(String description){
        this.description = description;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setUpdatedAt(LocalDateTime updatedAt){
        this.updatedAt = updatedAt;
    }

    public int getId(){
        return id;
    }
    public String getDescription(){
        return description;
    }
    public String getStatus(){
        return status;
    }
    public LocalDateTime getCreatedAt(){
        return createdAt;
    }
    public LocalDateTime getUpdatedAt(){
        return updatedAt;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        return id + ". " + description + "\nStatus: " + status + "\nCreated: " + createdAt.format(formatter) + "\nUpdated: " + updatedAt.format(formatter);
    }
}
