package com.amalitech.caf.configs.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permissions {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_CREATE("admin:create"),
    ASSOCIATION_READ("association:read"),
    ASSOCIATION_UPDATE("association:update"),
    ASSOCIATION_DELETE("association:delete"),
    ASSOCIATION_CREATE("association:create");

    @Getter
    private final String permission;
}
