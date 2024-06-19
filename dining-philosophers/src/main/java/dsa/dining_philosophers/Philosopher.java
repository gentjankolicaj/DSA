package dsa.dining_philosophers;

import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@ToString
public class Philosopher implements Runnable, Human {

  private final int id;
  private final Chopstick left;
  private final Chopstick right;
  private volatile boolean isFull;
  private int mealCounter;

  @Override
  public void run() {
    log.info("Thread {} active , philosopher {}", Thread.currentThread(), id);
    long startTime = System.currentTimeMillis();
    while (!isFull) {
      boolean acquiredLeft = false;
      boolean acquiredRight = false;
      try {
        acquiredLeft = left.pick(this, State.LEFT);
        acquiredRight = right.pick(this, State.RIGHT);

        if (acquiredLeft && acquiredRight) {
          eat(new Meal(mealCounter, "Meal number" + mealCounter));
          mealCounter++;
        } else {
          log.warn("Thread {} couldn't acquire both chopsticks.Left {} , right {}",
              Thread.currentThread(),
              acquiredLeft, acquiredRight);
        }
        think();
        isFull = (System.currentTimeMillis() - startTime) > Constants.SIMULATION_RUNTIME;
      } catch (InterruptedException e) {
        log.error("Interrupting thread because of error ", e);
        Thread.currentThread().interrupt();
      } finally {
        if (acquiredLeft) {
          left.drop(this);
        }
        if (acquiredRight) {
          right.drop(this);
        }
      }
    }
    log.info("Thread {} terminating , philosopher {} is full {}", Thread.currentThread(), id,
        isFull());
  }

  public boolean isFull() {
    return isFull;
  }

  public void eat(Meal meal) throws InterruptedException {
    log.info("Both chopsticks acquired");
    log.info("Eating meal {} ...", meal);
    Thread.sleep(200);
    log.info("Finished meal {}.", meal);
  }

  public void think() throws InterruptedException {
    Thread.sleep(100);
  }
}
