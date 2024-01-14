package com.example.be.dto.request.agent;

public class AgentsEmployeeDto {
    private Integer id;
    private String name;
    private String sortType;

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

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
