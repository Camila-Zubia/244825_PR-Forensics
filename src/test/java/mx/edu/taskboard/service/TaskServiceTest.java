package mx.edu.taskboard.service;

import mx.edu.taskboard.model.Priority;
import mx.edu.taskboard.model.Task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskServiceTest {

    @Test
    void storesTasks() {
        var service = new TaskService();

        service.add(new Task(
                "Prepare demo",
                "Ana",
                Priority.MEDIUM,
                LocalDate.of(2026, 9, 10),
                false
        ));

        assertEquals(1, service.all().size());
    }

    @Test
    void returnsOnlyOverdueIncompleteTasks() {
        var service = new TaskService();
        var today = LocalDate.of(2026, 9, 10);

        service.add(new Task("Late", "Ana", Priority.HIGH, today.minusDays(2), false));
        service.add(new Task("Done", "Ana", Priority.MEDIUM, today.minusDays(1), true));
        service.add(new Task("Future", "Ana", Priority.LOW, today.plusDays(1), false));

        List<Task> result = service.overdue(today);

        assertEquals(1, result.size());
        assertEquals("Late", result.getFirst().title());
    }

    @Test
    void trimsAssigneeWhenAddingTask() {
        var service = new TaskService();

        service.add(new Task(
                "Review PR",
                "  Luis  ",
                Priority.HIGH,
                LocalDate.of(2026, 9, 12),
                false
        ));

        assertEquals("Luis", service.all().getFirst().assignee());
    }

    @Test
    void rejectsBlankAssignee() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Task(
                        "Review PR",
                        "   ",
                        Priority.HIGH,
                        LocalDate.of(2026, 9, 12),
                        false
                )
        );
    }

    @Test
    void sortsTasksByPriority() {
        var service = new TaskService();

        service.add(new Task("Low", "Ana", Priority.LOW, LocalDate.of(2026, 9, 12), false));
        service.add(new Task("High", "Ana", Priority.HIGH, LocalDate.of(2026, 9, 12), false));

        assertEquals(Priority.HIGH, service.all().getFirst().priority());
    }
}
