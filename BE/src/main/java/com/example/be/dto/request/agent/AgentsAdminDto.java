package com.example.be.dto.request.agent;

public class AgentsAdminDto {
    private String name;
    private String sortType;


    public AgentsAdminDto() {
    }

    public AgentsAdminDto(final String name, final String sortType) {
        this.name = name;
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(final String sortType) {
        this.sortType = sortType;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
