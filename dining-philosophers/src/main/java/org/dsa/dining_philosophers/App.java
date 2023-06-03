package org.dsa.dining_philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    List<Chopstick> chopsticks = getChopsticks(Constants.NUMBER_OF_CHOPSTICKS);
    List<Philosopher> philosophers = getPhilosophers(Constants.NUMBER_OF_PHILOSOPHERS, chopsticks);

    //Create executor & submit threads
    ExecutorService executor = Executors.newFixedThreadPool(philosophers.size());

    for (int i = 0, len = philosophers.size(); i < len; i++) {
      executor.execute(philosophers.get(i));
    }

    //Initial orderly shutdown.No new tasks/threads can be submitted.
    executor.shutdown();

  }

  private static List<Philosopher> getPhilosophers(int numberOfPhilosophers, List<Chopstick> chopsticks) {
    if (numberOfPhilosophers <= 0) {
      throw new IllegalArgumentException("Number of philosophers must be > 0");
    }
    if (numberOfPhilosophers != chopsticks.size()) {
      throw new IllegalArgumentException("Number of philosophers must be equal to chopsticks.");
    }

    List<Philosopher> philosophers = new ArrayList<>();

    //assign philosophers a left & right chopstick reference
    for (int i = 0, chopstickNum = chopsticks.size(); i < chopstickNum; i++) {
      if (i + 1 == chopstickNum) {
        Philosopher philosopher = new Philosopher(i, chopsticks.get(i), chopsticks.get(0));
        philosophers.add(philosopher);
      } else {
        Philosopher philosopher = new Philosopher(i, chopsticks.get(i), chopsticks.get(i + 1));
        philosophers.add(philosopher);
      }
    }
    return philosophers;
  }

  private static List<Chopstick> getChopsticks(int numberOfChopsticks) {
    if (numberOfChopsticks <= 0) {
      throw new IllegalArgumentException("Number of chopsticks must be > 0");
    }
    List<Chopstick> list = new ArrayList<>();
    for (int i = 0; i < numberOfChopsticks; i++) {
      list.add(new Chopstick(i));
    }
    return list;
  }
}
