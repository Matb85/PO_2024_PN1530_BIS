package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private CountDownLatch latch;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);
    private final List<Thread> simulationThreads = new ArrayList<>();

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        latch = new CountDownLatch(simulations.size());
        for (Simulation simulation : simulations) {
            new Thread(() -> {
                try {
                    simulation.run();
                } finally {
                    latch.countDown();
                }
            }).start();
        }
    }

    public void runAsyncInThreadPool() {
        latch = new CountDownLatch(simulations.size());
        for (Simulation simulation : simulations) {
            Runnable task = () -> {
                try {
                    simulation.run();
                } finally {
                    latch.countDown();
                }
            };
            executorService.submit(task);
        }
    }

    public void awaitSimulationsEnd() {
        try {
            if (latch != null) {
                latch.await();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
                executorService.shutdown();
                try {
                    if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                        executorService.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    executorService.shutdownNow();
                    Thread.currentThread().interrupt();
                }
        }
    }

    public void runAsyncWithoutWaitingForFinish() {
        for (Simulation simulation : simulations) {
            Thread thread = new Thread(simulation);
            simulationThreads.add(thread);
            thread.start();
        }
    }
}