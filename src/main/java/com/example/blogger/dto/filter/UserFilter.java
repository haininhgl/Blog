package com.example.blogger.dto.filter;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static com.example.blogger.constants.Constants.STRING_MAX_LENGTH;

public class UserFilter {

    @Size(max = STRING_MAX_LENGTH)
    private String text = "";

    private Set<Long> roleIds = new HashSet<>();


    public UserFilter() {}

    public UserFilter(Set<Long> roleIds, String text) {
        this.roleIds = roleIds;
        this.text = text;
    }

    public String getText() {
        return StringUtils.trim(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
}