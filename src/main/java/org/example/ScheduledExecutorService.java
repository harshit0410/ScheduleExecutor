package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ScheduledExecutorService {
    static PriorityQueue <ScheduledTask> queue;
    static ExecutorService executorService;

    public ScheduledExecutorService() {
        queue = new PriorityQueue<>((a,b) -> a.getExecutionTime().compareTo(b.getExecutionTime()));
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public  void schedule (Runnable command, long delay, ChronoUnit unit) {
        LocalDateTime t = LocalDateTime.now().plus(delay, unit);
        ScheduledTask task = new ScheduledTask(command, t, null, null, TaskType.NONE, unit);
        queue.add(task);
    }

    public void scheduleAtFixedRate (Runnable command , long initialDelay, long period, ChronoUnit unit) {
        LocalDateTime t = LocalDateTime.now().plus(initialDelay, unit);
        ScheduledTask task = new ScheduledTask(command, t, period, null, TaskType.FIXEDRATE, unit);
        queue.add(task);
    }

    public void scheduleAtFixedDelay (Runnable command , long initialDelay, long delay, ChronoUnit unit) {
        LocalDateTime t = LocalDateTime.now().plus(initialDelay, unit);
        ScheduledTask task = new ScheduledTask(command, t, null, delay, TaskType.FIXEDDELAY, unit);
        queue.add(task);
    }

    public void start (){
        try {
            while (!queue.isEmpty()) {
                boolean processingNeeded = false;
                ScheduledTask task = queue.peek();
                if (task.getExecutionTime().isBefore(LocalDateTime.now())) processingNeeded = true;
//                else {
//                    Duration duration = Duration.between(LocalDateTime.now(), task.getExecutionTime());
//                    if(duration.getSeconds() < 1) processingNeeded = true;
//                }

                if (processingNeeded) {
                    task = queue.poll();
                    LocalDateTime newTime;
                    ScheduledTask newTask;
                    switch (task.getType()) {
                        case NONE :
                            executorService.submit(task.getTask());
                            break;
                        case FIXEDRATE:
                            newTime = task.getExecutionTime().plus(task.getPeriod(), task.getUnit());
                            executorService.submit(task.getTask());
                            newTask = new ScheduledTask(task.getTask(), newTime, task.getPeriod(), task.getDelay(), task.getType(), task.getUnit());
                            queue.add(newTask);
                            break;
                        case FIXEDDELAY:
                            Future <?> future = executorService.submit(task.getTask());
                            future.get();
                            newTime = LocalDateTime.now().plus(task.getDelay(), task.getUnit());
                            newTask = new ScheduledTask(task.getTask(), newTime, task.getPeriod(), task.getDelay(), task.getType(), task.getUnit());
                            queue.add(newTask);
                            break;
                    }
                }

                Thread.sleep(500);
            }
        }
        catch (Exception e) {
            System.out.println("some thing wrong in start");
            e.printStackTrace();
        }

    }


}
