package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Taxonomy;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TaxonomyMapper {
    @Delete({
        "delete from co_taxonomy",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into co_taxonomy (title, type, ",
        "app_column, remark, ",
        "order_number)",
        "values (#{title,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, ",
        "#{appColumn,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{orderNumber,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Taxonomy record);

    @InsertProvider(type=TaxonomySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Taxonomy record);

    @Select({
        "select",
        "id, title, type, app_column, remark, order_number",
        "from co_taxonomy",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="app_column", property="appColumn", jdbcType=JdbcType.VARCHAR),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_number", property="orderNumber", jdbcType=JdbcType.INTEGER)
    })
    Taxonomy selectByPrimaryKey(Integer id);

    @UpdateProvider(type=TaxonomySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Taxonomy record);

    @Update({
        "update co_taxonomy",
        "set title = #{title,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "app_column = #{appColumn,jdbcType=VARCHAR},",
          "remark = #{remark,jdbcType=VARCHAR},",
          "order_number = #{orderNumber,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Taxonomy record);
}