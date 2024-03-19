package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

public class ScheduledTask  {
    private Runnable task;
    private LocalDateTime executionTime;

    private Long period;
    private Long delay;

    private TaskType type;
    private ChronoUnit unit;

    public ScheduledTask(Runnable task, LocalDateTime executionTime, Long period, Long delay, TaskType type, ChronoUnit unit) {
        this.task = task;
        this.executionTime = executionTime;
        this.period = period;
        this.delay = delay;
        this.type = type;
        this.unit = unit;
    }

    public Runnable getTask() {
        return task;
    }

    public void setTask(Runnable task) {
        this.task = task;
    }

    public LocalDateTime getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(LocalDateTime executionTime) {
        this.executionTime = executionTime;
    }

    public Long getPeriod() {
        return period;
    }

    public void setPeriod(Long period) {
        this.period = period;
    }

    public Long getDelay() {
        return delay;
    }

    public void setDelay(Long delay) {
        this.delay = delay;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public ChronoUnit getUnit() {
        return unit;
    }

    public void setUnit(ChronoUnit unit) {
        this.unit = unit;
    }

//    @Override
//    public int compare(Object o1, Object o2) {
//        ScheduledTask s1 = (ScheduledTask) o1;
//        ScheduledTask s2 = (ScheduledTask) o2;
//
//        return s1.getExecutionTime().compareTo(s2.getExecutionTime());

//    }
}
