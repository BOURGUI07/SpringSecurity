package com.example.MethodLevelSecurity.permissions;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("/documents/{code}")
    public Document getDocument(@PathVariable String code) {
        return documentService.getDocument(code);
    }
}
