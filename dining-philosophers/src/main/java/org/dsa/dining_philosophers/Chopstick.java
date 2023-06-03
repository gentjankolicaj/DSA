package org.dsa.dining_philosophers;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Chopstick {
  private final int id;
  private Lock lock=new ReentrantLock(true);

  public boolean pick() {
    return lock.tryLock();
  }

  public void drop(){
    lock.unlock();
  }

}
