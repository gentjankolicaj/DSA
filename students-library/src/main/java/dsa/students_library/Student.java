package dsa.students_library;

import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class Student implements Runnable {

  private final int id;
  private final Random random = new Random();
  private final int maxReadingIteration = random.nextInt(10);
  private boolean finishedReading;

  @Override
  public void run() {
    log.info("Thread {} | for student {}", Thread.currentThread(), id);
    try {
      int counter = 0;
      while (!finishedReading) {
        int bookId = random.nextInt(Constants.BOOKS_NUMBER);
        Book choosenBook = BookShelf.getBook(bookId);
        log.info("Chosen book {}", choosenBook);
        choosenBook.pickup(this, arg -> {
          log.info("Book processed | {}", arg);
        });
        pause();
        finishedReading = counter >= maxReadingIteration;
        counter++;
      }
    } catch (InterruptedException e) {
      log.error("Interrupting thread because of error ", e);
      Thread.currentThread().interrupt();
    }
  }

  private void pause() throws InterruptedException {
    Thread.sleep(200);
    log.info("Paused 200 millis");
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", maxReadingIteration=" + maxReadingIteration +
        ", finishedReading=" + finishedReading +
        '}';
  }
}
