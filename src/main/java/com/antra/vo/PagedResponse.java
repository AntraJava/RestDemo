package com.antra.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement
public class PagedResponse<T> {
    private int page;
    private int rows;
    private int totalPage;
    private long totalElement;
    private String order;
    private List<T> body;

    public PagedResponse() {
    }

    public boolean isEmpty(){
        return body == null || body.isEmpty();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }
}
