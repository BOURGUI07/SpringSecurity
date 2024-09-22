package com.example.MethodLevelSecurity.permissions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepo documentRepo;

    @PostAuthorize("hasPermission(returnObject,'ROLE_admin')")
    public Document getDocument(String documentId) {
        log.info("GENERATING DOCUMENT FOR CODE: {}" , documentId);
        return documentRepo.getDocument(documentId);
    }
}
