package com.mijiu.commom.util;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 拆解一级功能id列表字符串转list集合
 * </p>
 *
 * @author mijiupro
 */
@Slf4j
public class StringSplitUtil {


    public static List<Integer> functionIdSplit(String functionId) {
        log.info("接收到一级功能id列表：{}", functionId);
        if (functionId.isEmpty()) {
            return null;
        }

        List<Integer> f1idList = new ArrayList<>();
        // TODO 字符串分割异常捕获
        try {
            String[] split = functionId.split(",");
            for (String s : split) {
                f1idList.add(Integer.valueOf(s));
            }
            log.info("f1idList:{}", f1idList);
        } catch (Exception e) {
            log.info("功能列表字符串分割异常");

        }

        return f1idList;
    }
}
