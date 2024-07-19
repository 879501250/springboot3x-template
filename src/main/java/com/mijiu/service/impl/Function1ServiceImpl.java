package com.mijiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mijiu.entity.Function1;
import com.mijiu.entity.FunctionSystem;
import com.mijiu.mapper.Function1Mapper;
import com.mijiu.service.Function1Service;
import com.mijiu.commom.util.StringSplitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统一级功能表 服务实现类
 * </p>
 *
 * @author 蒾酒
 * @since 2024-02-21
 */
@Service
@Slf4j
public class Function1ServiceImpl extends ServiceImpl<Function1Mapper, Function1> implements Function1Service {
    private final Function1Mapper function1Mapper;

    public Function1ServiceImpl(Function1Mapper function1Mapper) {
        this.function1Mapper = function1Mapper;
    }


    @Override
    public List<Function1> getSystemFunctionTree() {
        FunctionSystem systemFunctionTree = function1Mapper.getSystemFunctionTree(1);
        List<Function1> function1 = null;
        List<Function1> function2 = null;
        List<Function1> function3 = null;
        // 字符串解析为查询条件
        List<Integer> f1idList = StringSplitUtil.functionIdSplit(systemFunctionTree.getF1id());
        if (f1idList != null) {
            function1 = function1Mapper.getFunction1(f1idList);
            log.info(function1.toString());
            List<Integer> f2idList = StringSplitUtil.functionIdSplit(systemFunctionTree.getF2id());
            if (f2idList != null) {
                function2 = function1Mapper.getFunction2(f2idList);
                log.info(function2.toString());
                if(systemFunctionTree.getF3id()!=null){
                    List<Integer> f3idList = StringSplitUtil.functionIdSplit(systemFunctionTree.getF3id());
                    if (f3idList != null) {
                        function3 = function1Mapper.getFunction3(f3idList);
                        log.info(function3.toString());
                    }
                }

            }
        }
        if (function1 != null) {
            for (Function1 value : function1) {
                List<Function1> f1Children = new ArrayList<>();
                if (function2 != null) {
                    for (int j = 0; j < function2.size(); j++) {
                        List<Function1> f2Children = new ArrayList<>();
                        if (value.getFid().equals(function2.get(j).getFatherId())) {
                            if (function3 != null) {
                                for (int k = 0; k < function3.size(); k++) {
                                    if (function2.get(j).getFid().equals(function3.get(k).getFatherId())) {
                                        f2Children.add(function3.get(k));
                                    }
                                    if (k == function3.size() - 1) {
                                        function2.get(j).setChildren(f2Children);
                                    }
                                }
                            }
                            f1Children.add(function2.get(j));
                        }
                        if (j == function2.size() - 1) {
                            value.setChildren(f1Children);
                        }
                    }
                }
            }
        }
        if (function1 != null) {
            log.info(function1.toString());
        }
        return function1;
    }
}
