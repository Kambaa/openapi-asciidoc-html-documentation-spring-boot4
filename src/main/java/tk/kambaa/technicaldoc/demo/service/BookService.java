package tk.kambaa.technicaldoc.demo.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import tk.kambaa.technicaldoc.demo.Book;
import tk.kambaa.technicaldoc.demo.BookNotFoundException;
import tk.kambaa.technicaldoc.demo.PagedResponse;
import tk.kambaa.technicaldoc.demo.ServiceResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BookService {

    private final ConcurrentHashMap<Long, Book> store = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public List<Book> findAll() {
        return List.copyOf(store.values());
    }

    public ServiceResponse<PagedResponse<Book>> findAllPaged(int page, int size) {
        List<Book> all = List.copyOf(store.values());
        int total = all.size();
        int from = page * size;
        int to = Math.min(from + size, total);
        List<Book> content = from >= total ? List.of() : all.subList(from, to);
        PagedResponse<Book> paged = new PagedResponse<>(page, size, total, content);
        return ServiceResponse.success("Books retrieved successfully", paged);
    }

    public Book findById(Long id) {
        Book book = store.get(id);
        if (book == null) throw new BookNotFoundException(id);
        return book;
    }

    public Book create(@Valid Book book) {
        book.setId(idCounter.getAndIncrement());
        book.setCreatedAt(LocalDateTime.now());
        store.put(book.getId(), book);
        return book;
    }

    public Book update(Long id, @Valid Book updated) {
        if (!store.containsKey(id)) throw new BookNotFoundException(id);
        updated.setId(id);
        updated.setCreatedAt(store.get(id).getCreatedAt());
        store.put(id, updated);
        return updated;
    }

    public void delete(Long id) {
        if (store.remove(id) == null) throw new BookNotFoundException(id);
    }
}
