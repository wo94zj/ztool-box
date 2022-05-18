package com.ztool.box.page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author zhaoj
 */
public class UnifyPageHelper {

    public static UnifyPage emtryPage() {
        return new UnifyPage();
    }

    public static <T, E> UnifyPage<T> fromList(List<E> list, Function<E, T> func, Pageable pageable, int total) {
        UnifyPage<T> unifyPage = new UnifyPage<>(pageable.getPageNo(), pageable.getPageSize(), total);
        unifyPage.setData(list.stream().map(func).collect(Collectors.toList()));
        return unifyPage;
    }

    public static <T> UnifyPage<T> fromList(List<T> list, Pageable pageable, int total) {
        UnifyPage<T> unifyPage = new UnifyPage<>(pageable.getPageNo(), pageable.getPageSize(), total);
        unifyPage.setData(list);
        return unifyPage;
    }
}
