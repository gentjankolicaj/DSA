package dsa.dining_philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class Chopstick {

  private final int id;
  private Lock lock;
  private State state;

  public Chopstick(int id) {
    this.id = id;
    this.lock = new ReentrantLock(true);
  }

  public boolean pick(Philosopher philosopher, State state) throws InterruptedException {
    if (lock.tryLock(Constants.CHOPSTICK_TRY_LOCK_TIME, Constants.CHOPSTICK_TRY_LOCK_TIME_UNIT)) {
      this.state = state;
      log.info("Philosopher {} locked {}", philosopher, this);
      return true;
    }
    return false;
  }

  public void drop(Philosopher philosopher) {
    lock.unlock();
    log.info("Philosopher {} unlocked {}", philosopher, this);
  }

  @Override
  public String toString() {
    return "Chopstick{" +
        "id=" + id +
        ", state=" + state +
        '}';
  }
}
