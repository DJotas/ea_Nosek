package cz.mendelu.EAprojek.utils.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;


import java.util.List;
import java.util.function.Function;

@Data
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> items;
    private int page;
    private int Size;
    private long totalItems;
    private int totalPages;
    

      public static <T, R> PageResponse<R> of(Page<T> page, Function<T, R> mapper) {
        return new PageResponse<>(
                page.getContent().stream().map(mapper).toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
    
}
