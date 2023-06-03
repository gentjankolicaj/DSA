package org.dsa.dining_philosophers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Philosopher implements Runnable, Human {

  private final int id;
  private final Chopstick left;
  private final Chopstick right;

  @Override
  public void run() {
    log.info("Thread {} active.", id);
    long startTime = System.currentTimeMillis();
    int counter = 0;
    while ((System.currentTimeMillis() - startTime) <= Constants.SIMULATION_RUNTIME) {
      boolean acquiredLeft = false;
      boolean acquiredRight = false;
      try {
        acquiredLeft = left.pick();
        acquiredRight = right.pick();

        if (acquiredLeft && acquiredRight) {
          eat(new Meal(counter, "Meal number" + counter));
          counter++;
        }else{
          log.warn("Thread {} couldn't acquire both chopsticks.Left {} , right {}",Thread.currentThread(),acquiredLeft,acquiredRight);
        }
        Thread.sleep(100);
      } catch (InterruptedException e) {
        log.error("Interrupting thread because of error ", e);
        Thread.currentThread().interrupt();
      } finally {
        if (acquiredLeft) {
          left.drop();
        }
        if (acquiredRight) {
          right.drop();
        }
      }
    }
  }


  @Override
  public void eat(Meal meal) throws InterruptedException {
    log.info("Both chopsticks acquired, left {}, right{}");
    log.info("Eating meal {} ...", meal);
    Thread.sleep(200);
    log.info("Finished meal {}.", meal);
  }
}
