package com.ztool.box.page;

import com.ztool.box.constant.Constants;
import com.ztool.box.util.ListUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhaoj
 */
@ApiModel("分页参数")
@Data
public class Pageable {

    @ApiModelProperty("当前页")
    private Integer pageNo = Constants.DEFAULT_FIRST_PAGE;

    @ApiModelProperty("页大小")
    private Integer pageSize = Constants.DEFAULT_PAGE_SIZE;

    @ApiModelProperty("升序排序")
    private List<String> ordersAsc;

    @ApiModelProperty("降序排序")
    private List<String> ordersDesc;


    public void init() {
        if (pageNo == null || pageNo < Constants.DEFAULT_FIRST_PAGE) {
            pageNo = Constants.DEFAULT_FIRST_PAGE;
        }
        if (pageSize == null || pageSize <= Constants.DEFAULT_FIRST_PAGE) {
            pageSize = Constants.DEFAULT_PAGE_SIZE;
        } else if (pageSize > Constants.MAX_PAGE_SIZE) {
            pageSize = Constants.MAX_PAGE_SIZE;
        }
    }



    public PageRequest toPage() {
        this.init();

        if (this.isSort()) {
            List<Sort.Order> orders = new ArrayList<>();
            if (!ListUtil.isEmtry(this.getOrdersAsc())) {
                Sort sort = Sort.by(Sort.Direction.ASC, this.getOrdersAsc().toArray(new String[this.getOrdersAsc().size()]));
                orders.addAll(sort.toList());
            }

            if (!ListUtil.isEmtry(this.getOrdersDesc())) {
                Sort sort = Sort.by(Sort.Direction.DESC, this.getOrdersDesc().toArray(new String[this.getOrdersDesc().size()]));
                orders.addAll(sort.toList());
            }

            return PageRequest.of(this.getPageNo(), this.getPageSize(), Sort.by(orders));
        }

        return PageRequest.of(this.getPageNo(), this.getPageSize());
    }

    @ApiModelProperty(hidden = true)
    public boolean isSort() {
        if (ListUtil.isEmtry(ordersAsc) && ListUtil.isEmtry(ordersDesc)) {
            return false;
        }

        return true;
    }
}
