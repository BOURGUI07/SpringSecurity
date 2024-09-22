package com.example.MethodLevelSecurity.preAuthorization;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NameService {
    private Map<String, List<String>> map =
            Map.of("john", List.of("John1","John2","John3"),
                    "jane",List.of("Jane1","Jane2","Jane3"));




    @PreAuthorize("hasAuthority('write')")
    public String getName(){
        return "Youness!";
    }

    @PreAuthorize("#name == authentication.principal.username")
    public List<String> getSecretNames(String name){
        return map.get(name);
    }
}
