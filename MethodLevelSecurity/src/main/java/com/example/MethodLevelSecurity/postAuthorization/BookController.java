package com.example.MethodLevelSecurity.postAuthorization;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class BookController {
    private final BookService bookService;

    @GetMapping("/book/details/{name}")
    public Employee getEmployee(@PathVariable String name) {
        return bookService.getEmployee(name);
    }
}
