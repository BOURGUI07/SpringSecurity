package com.example.MethodLevelSecurity.postAuthorization;

import java.util.List;

public record Employee(
         String name,
         List<String> books,
         List<String> roles
) {
}
