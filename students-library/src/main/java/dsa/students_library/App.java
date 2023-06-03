package dsa.students_library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    List<Student> students=getStudents(Constants.STUDENT_NUMBER);

    //Create executor & submit threads
    ExecutorService executor = Executors.newFixedThreadPool(students.size());

    for (int i = 0, len = students.size(); i < len; i++) {
      executor.execute(students.get(i));
    }

    //Initial orderly shutdown.No new tasks/threads can be submitted.
    executor.shutdown();
  }



  private static List<Student> getStudents(int studentNumber) {
    if (studentNumber <= 0) {
      throw new IllegalArgumentException("Number of students must be > 0");
    }
    List<Student> list = new ArrayList<>();
    for (int i = 0; i < studentNumber; i++) {
      list.add(new Student(i));
    }
    return list;
  }
}
