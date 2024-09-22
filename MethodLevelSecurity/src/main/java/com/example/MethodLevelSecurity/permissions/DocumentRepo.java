package com.example.MethodLevelSecurity.permissions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class DocumentRepo {
    private Map<String, Document> map=
            Map.of("abc111",new Document("john"),
                    "abc222",new Document("john"),
                    "abc333",new Document("jane"));

    public Document getDocument(String code) {

        return map.get(code);
    }
}
