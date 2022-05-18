package com.ztool.box.page;

import com.ztool.box.constant.Constants;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @Author zhaoj
 */
@Data
public class UnifyPage<T> {

    private int pageNo = Constants.DEFAULT_FIRST_PAGE;

    private int pageSize = Constants.DEFAULT_PAGE_SIZE;

    private int total = 0;

    private List<T> data = Collections.emptyList();

    public UnifyPage() {
    }

    public UnifyPage(Pageable pageable) {
        this.pageNo = pageable.getPageNo();
        this.pageSize = pageable.getPageSize();
    }

    public UnifyPage(int pageNo, int pageSize, int total) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
    }

    public UnifyPage(int pageNo, int pageSize, int total, List<T> data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.data = data;
    }
}
