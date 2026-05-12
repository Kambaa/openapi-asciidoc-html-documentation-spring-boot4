package tk.kambaa.technicaldoc.demo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Represents a book in the catalog")
public class Book {

    @Schema(description = "Unique identifier", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Schema(description = "Book title", example = "The Hobbit", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank
    @Schema(description = "Author name", example = "J.R.R. Tolkien", requiredMode = Schema.RequiredMode.REQUIRED)
    private String author;

    @Positive
    @Schema(description = "Price in USD", example = "29.99", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

    @Schema(description = "Book category/genre", example = "Fantasy")
    private String category;

    @Schema(description = "Timestamp when the book was added", example = "2026-05-12T18:00:00",
        accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;
}
