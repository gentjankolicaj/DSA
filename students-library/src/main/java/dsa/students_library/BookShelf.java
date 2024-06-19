package dsa.students_library;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookShelf {

  public static final Map<Integer, Book> BOOK_SHELF = getBooks();

  public static final Map<Integer, Book> getBooks() {
    Map<Integer, Book> map = new HashMap<>();
    for (int i = 0; i < Constants.BOOKS_NUMBER; i++) {
      map.put(i, new Book(i, List.of("First paragraph " + i, "Second paragraph " + i)));
    }
    return map;
  }

  /**
   * In future if map/map content is changed , it is recommended to be changed to ConcurrentHashMap
   *
   * @param bookId
   * @return
   */
  public static final Book getBook(int bookId) {
    return BOOK_SHELF.get(bookId);
  }

}
