package org.example;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ScheduledExecutorService schedulerService = new ScheduledExecutorService();
//        Runnable task1 = getRunnableTask("Task1");
//        schedulerService.schedule(task1, 1, ChronoUnit.SECONDS);
        Runnable task2 = getRunnableTask("Task2");
        schedulerService.scheduleAtFixedRate(task2,1, 5, ChronoUnit.SECONDS);
//        Runnable task3 = getRunnableTask("Task3");
//        schedulerService.scheduleAtFixedDelay(task3,1,10,ChronoUnit.SECONDS);
//        Runnable task4 = getRunnableTask("Task4");
//        schedulerService.scheduleAtFixedRate(task4,1, 2, ChronoUnit.SECONDS);

        schedulerService.start();
    }

    public static  Runnable getRunnableTask (String s) {
        return () -> {
            System.out.println(s + "started at " + LocalDateTime.now());
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(s + "ended at " + LocalDateTime.now());
        };
    }
}