package com.example.MethodLevelSecurity.permissions;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Permissions;

@Component
public class DocumentsPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(
            Authentication authentication,
            Object targetDomainObject,
            Object permission) {
        var document = (Document) targetDomainObject;
        var permissions = (String) permission;
        boolean admin = authentication.getAuthorities()
                .stream()
                .anyMatch(g -> g.getAuthority().equals(permissions));
        return admin || document.owner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
