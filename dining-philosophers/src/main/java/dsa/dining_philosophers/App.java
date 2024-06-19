package dsa.dining_philosophers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

/**
 * Purpose of this algorithm impl is to avoid thread starvation.
 * <br>1.All thread are going to run
 * <br>2.Deadlocks are avoided.
 */
@Slf4j
public class App {

  public static void main(String[] args) {
    List<Chopstick> chopsticks = getChopsticks();
    List<Philosopher> philosophers = getPhilosophers(chopsticks);

    //Create executor & submit threads
    ExecutorService executor = null;
    try {
      executor = Executors.newFixedThreadPool(philosophers.size());
      for (Philosopher philosopher : philosophers) {
        executor.execute(philosopher);
      }
    } finally {
      if (Objects.nonNull(executor)) {
        //Initial orderly shutdown.No new tasks/threads can be submitted.
        executor.shutdown();
      }
    }
  }

  private static List<Philosopher> getPhilosophers(List<Chopstick> chopsticks) {
    if (Constants.NUMBER_OF_PHILOSOPHERS <= 0) {
      throw new IllegalArgumentException("Number of philosophers must be > 0");
    }
    if (Constants.NUMBER_OF_PHILOSOPHERS != chopsticks.size()) {
      throw new IllegalArgumentException("Number of philosophers must be equal to chopsticks.");
    }

    List<Philosopher> philosophers = new ArrayList<>();

    //assign philosophers a left & right chopstick reference
    for (int i = 0, chopstickNum = chopsticks.size(); i < chopstickNum; i++) {
      //about (i+1)%number => check modulus effect & position shift with +1
      Philosopher philosopher = new Philosopher(i, chopsticks.get(i),
          chopsticks.get((i + 1) % Constants.NUMBER_OF_PHILOSOPHERS));
      philosophers.add(philosopher);
    }
    return philosophers;
  }

  private static List<Chopstick> getChopsticks() {
    if (Constants.NUMBER_OF_CHOPSTICKS <= 0) {
      throw new IllegalArgumentException("Number of chopsticks must be > 0");
    }
    List<Chopstick> list = new ArrayList<>();
    for (int i = 0; i < Constants.NUMBER_OF_CHOPSTICKS; i++) {
      list.add(new Chopstick(i));
    }
    return list;
  }
}
