package com.wms.admin.util;

import java.util.function.Function;

/**
 * @author: xwh90
 * @date: 2022/8/1 17:53
 * @description:
 */
public class VOUtil {

   public static <V,E> E convertTo(V vo, Function<V,E> function){
       return  function.apply(vo);
   }
}
