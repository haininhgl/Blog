package com.example.blogger.dto.filter;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.example.blogger.constants.Constants.STRING_MAX_LENGTH;


public class PostFilter {
    @Size(max = STRING_MAX_LENGTH)
    private String text = "";

    private Set<Long> userIds = new HashSet<>();

    public PostFilter() {}

    public PostFilter(String text) {
        this.text = text;
    }

    public String getText() {
        return StringUtils.trim(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(Set<Long> userIds) {
        this.userIds = userIds;
    }
}