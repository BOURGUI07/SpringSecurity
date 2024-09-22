package com.example.MethodLevelSecurity.postAuthorization;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {
    private Map<String,Employee> map =
            Map.of("youness",
                    new Employee(
                    "Youness Bourgui", List.of("Java TextBook"),List.of("programmer","tester")),


                    "yassine", new Employee("Yassine Bourgui",List.of("Brothers"),List.of("reader","researcher"))
            );

    @PostAuthorize("returnObject.roles.contains('tester')")
    public Employee getEmployee(String name) {
        return map.get(name);
    }
}
