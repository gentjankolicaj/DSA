package dsa.students_library;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class Book {

  private final Lock lock = new ReentrantLock(true);
  private int id;
  private List<String> paragraphs;

  public void pickup(Student student, Action action) throws InterruptedException {
    log.info("Trying pickup book {}", this);
    if (lock.tryLock(Constants.BOOK_PICKUP_TIME, Constants.BOOK_PICKUP_TIMEUNIT)) {
      log.info("Book {} picked up by {}", this, student);
      action.perform(this);
      //release the lock acquired on if condition
      lock.unlock();
    } else {
      log.info("Book {} not picked up by {}", this, student);
    }
  }

  @Override
  public String toString() {
    return "Book{" +
        ", id=" + id +
        ", paragraphs=" + paragraphs +
        '}';
  }
}
