package com.example.be.dto.request.employee;

public class SearchEmployee {
    private String name;
    private String sortType;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(final String sortType) {
        this.sortType = sortType;
    }
}
