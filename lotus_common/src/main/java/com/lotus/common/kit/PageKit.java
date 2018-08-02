package com.lotus.common.kit;

import com.github.pagehelper.PageInfo;
import com.lotus.common.entity.Page;

public class PageKit {

    public static <T> Page<T> pageComplete(PageInfo<T> pageInfo) {
        Page<T> page = new Page();
        page.setPageNumber(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setTotalRow(pageInfo.getTotal());
        page.setList(pageInfo.getList());
        page.setTotalPage(calTotalPage(page));
        return page;
    }

    private static <T> int calTotalPage(Page<T> page) {
        if (page.getPageSize() > 0 && page.getTotalRow() > -1) {
            return (int) (page.getTotalRow() % page.getPageSize() == 0 ? page.getTotalRow() / page.getPageSize() : page.getTotalRow() / page.getPageSize() + 1);
        }
        return -1;
    }
}
