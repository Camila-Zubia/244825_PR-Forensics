package mx.edu.taskboard.model;

import java.time.LocalDate;
import java.util.Objects;

public record Task(
        String title,
        String assignee,
        Priority priority,
        LocalDate dueDate,
        boolean completed
) {
    public Task {
        Objects.requireNonNull(title);
        Objects.requireNonNull(assignee);
        Objects.requireNonNull(priority);
        Objects.requireNonNull(dueDate);

        if (title.isBlank()) {
            throw new IllegalArgumentException("title must not be blank");
        }
    }
}
