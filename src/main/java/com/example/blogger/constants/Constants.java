package com.example.blogger.constants;


import org.springframework.data.domain.Sort;

public final class Constants {
    public static final int MIN_USERNAME_LENGTH = 3;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int STRING_MAX_LENGTH = 50;
    public static final int MAX_TITLE_LENGTH = 100;
    public static final String ID = "id";
    public static final String FIELD_PREFIX = "$";
    public static final String REF_ID = "." + FIELD_PREFIX + ID;
    public static final String ROLE = "roles";
    public static final String LOGIN = "login";
    public static final String USER_NAME = "username";
    public static final String EMAIL = "email";
    public static final String ADMIN = "admin";

    // pagination
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGEABLE_INDEX = 0;
    public static final int DEFAULT_PAGINATION_REQUEST_INDEX = DEFAULT_PAGEABLE_INDEX + 1;
    public static final String DESCENDING_SORT_DIRECTION = Sort.Direction.DESC.name();

    public static final String DATABASE_ID_REGEX = "^[a-fA-F0-9]{24}$";

    private Constants() {
        throw new IllegalStateException("Constant class");
    }
}

