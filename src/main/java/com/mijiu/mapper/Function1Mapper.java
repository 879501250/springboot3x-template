package com.mijiu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mijiu.entity.Function1;
import com.mijiu.entity.FunctionSystem;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 系统一级功能表 Mapper 接口
 * </p>
 *
 * @author 蒾酒
 * @since 2024-02-21
 */
public interface Function1Mapper extends BaseMapper<Function1> {
    //查询系统功能树
    @Select("select * from function_system where merchant_id=#{merchantId};")
    FunctionSystem getSystemFunctionTree(Integer merchantId);

    //查询一级功能列表
    @Select("<script>" +
            "select * from function1 where fid in" +
            "<foreach item='item' collection='f1idList' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "order by sort"+
            "</script>")
    List<Function1> getFunction1(List<Integer> f1idList);

    //查询二级功能列表
    @Select("<script>" +
            "select * from function2 where fid in" +
            "<foreach item='item' collection='f2idList' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "order by sort"+
            "</script>")
    List<Function1> getFunction2(List<Integer> f2idList);
    //查询三级功能列表
    @Select("<script>" +
            "select * from function3 where fid in" +
            "<foreach item='item' collection='f3idList' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "order by sort"+
            "</script>")
    List<Function1> getFunction3(List<Integer> f3idList);
}
