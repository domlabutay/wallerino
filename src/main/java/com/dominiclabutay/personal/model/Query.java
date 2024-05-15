package com.dominiclabutay.personal.model;

public class Query {
    private String searchTerm;

    public String getSearchTerm() {
        return searchTerm;
    }
    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    @Override
    public String toString() {
        return "Query [searchTerm=" + searchTerm + "]";
    }
}

