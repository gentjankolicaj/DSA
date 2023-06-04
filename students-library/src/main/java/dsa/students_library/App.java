package dsa.students_library;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App {

  public static void main(String[] args) {
    List<Student> students = getStudents();

    //Create executor & submit threads
    ExecutorService executor = Executors.newFixedThreadPool(students.size());

    for (Student student : students) {
      executor.execute(student);
    }

    //Initial orderly shutdown.No new tasks/threads can be submitted.
    executor.shutdown();
  }


  private static List<Student> getStudents() {
    if (Constants.STUDENT_NUMBER <= 0) {
      throw new IllegalArgumentException("Number of students must be > 0");
    }
    List<Student> list = new ArrayList<>();
    for (int i = 0; i < Constants.STUDENT_NUMBER; i++) {
      list.add(new Student(i));
    }
    return list;
  }
}
