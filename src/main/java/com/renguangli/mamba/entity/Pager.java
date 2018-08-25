package com.renguangli.mamba.entity;

public class Pager {

    private int offset;

    private int limit;

    private String sort;
    
    private String order;

    private int totalRecords;

    private int totalPages;

    public Pager() {}

    public Pager(int offset, int limit, String order, String sort, int totalRecords, int totalPages) {
        this.offset = offset;
        this.limit = limit;
        this.order = order;
        this.sort = sort;
        this.totalRecords = totalRecords;
        this.totalPages = totalPages;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        if (totalRecords % limit == 0) {
            totalPages = totalRecords / this.limit;
        } else {
            this.totalPages = (totalRecords / limit) + 1;
        }
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "Pager{" +
                "offset=" + offset +
                ", limit=" + limit +
                ", order='" + order + '\'' +
                ", sort='" + sort + '\'' +
                ", totalRecords=" + totalRecords +
                ", totalPages=" + totalPages +
                '}';
    }
}
