package com.wms.admin.util;

import java.util.function.Function;

/**
 * @author: xwh90
 * @date: 2022/8/1 17:53
 * @description:
 */
public class VOUtil {

    public static <V, E> E toEntity(V vo, Function<V, E> function) {
        return function.apply(vo);
    }

    public static <E, V> V toVO(E entity, Function<E, V> function) {
        return function.apply(entity);
    }
}
