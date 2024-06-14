package app.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Component
public class Todo {
    Integer id;
    @NotBlank(message = "title.notblank")
    String title;
    @NotBlank(message = "priority.notblank")
    String priority;
    private LocalDateTime createdAt;

    public Todo(String title, String priority) {
        this.title = title;
        this.priority = priority;
        this.createdAt = LocalDateTime.now();
    }

    public Todo(String title, String priority, Integer id) {
        this.id = 1;
        this.title = title;
        this.priority = priority;
        this.createdAt = LocalDateTime.now();
    }

    public String getFormattedDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return this.createdAt.format(formatter);
    }

}
