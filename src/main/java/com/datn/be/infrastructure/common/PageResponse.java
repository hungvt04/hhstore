package com.datn.be.infrastructure.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {

    private List<T> data;

    private int totalPage;

    private int currentPage;

    public PageResponse(Page<T> page) {
        this.data = page.getContent();
        this.totalPage = page.getTotalPages();
        this.currentPage = page.getNumber();
    }

}
