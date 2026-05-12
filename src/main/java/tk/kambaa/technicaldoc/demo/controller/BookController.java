package tk.kambaa.technicaldoc.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;
import tk.kambaa.technicaldoc.demo.Book;
import tk.kambaa.technicaldoc.demo.PagedResponse;
import tk.kambaa.technicaldoc.demo.ServiceResponse;
import tk.kambaa.technicaldoc.demo.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Book CRUD API")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "List all books", description = "Returns a list of all books in the catalog")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List of books retrieved successfully")
    })
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/paged")
    @Operation(summary = "List books with pagination", description = "Returns a paginated list of books")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paginated list of books retrieved successfully")
    })
    public ServiceResponse<PagedResponse<Book>> getAllPaged(
            @RequestParam(defaultValue = "0") @Schema(description = "Page number (0-based)", example = "0") int page,
            @RequestParam(defaultValue = "10") @Schema(description = "Number of items per page", example = "10") int size) {
        return bookService.findAllPaged(page, size);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a book by ID", description = "Returns a single book by its unique identifier")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book found"),
        @ApiResponse(responseCode = "404", description = "Book not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public Book getById(@PathVariable Long id) {
        return bookService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new book", description = "Adds a new book to the catalog")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Book created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public Book create(@Valid @RequestBody Book book) {
        return bookService.create(book);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing book", description = "Updates the details of an existing book by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book updated successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public Book update(@PathVariable Long id, @Valid @RequestBody Book book) {
        return bookService.update(id, book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete a book", description = "Removes a book from the catalog by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found",
            content = @Content(schema = @Schema(implementation = ProblemDetail.class)))
    })
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
