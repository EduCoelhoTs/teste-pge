package br.com.testpge.order.shared.data.dtos;

import java.util.List;

public class SearchResult<E, F> {

    final private List<E> items;
    final private int total;
    final private int currentPage;
    final private int perPage;
    final private String sort;
    final private String sortDir;
    final private F filter;
    private int lastPage = 0;

    public SearchResult(
            List<E> items,
            int total,
            int currentPage,
            int perPage,
            String sort,
            String sortDir,
            F filter
    ) {
        this.items = items;
        this.total = total;
        this.currentPage = currentPage;
        this.perPage = perPage;
        this.sort = sort;
        this.sortDir = sortDir;
        this.filter = filter;
        if (this.total > 0 && this.perPage > 0) {
            this.lastPage = Math.ceilDiv(this.total, this.perPage);
        }
    }

    public List<E> getItems() {
        return items;
    }

    public int getTotal() {
        return total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPerPage() {
        return perPage;
    }

    public String getSort() {
        return sort;
    }

    public String getSortDir() {
        return sortDir;
    }

    public F getFilter() {
        return filter;
    }

    public int getLastPage() {
        return lastPage;
    }
}
