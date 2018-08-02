package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Option;
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

public interface OptionMapper {
    @Delete({
        "delete from sys_option",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into sys_option (option_key, option_value, ",
        "option_remark)",
        "values (#{optionKey,jdbcType=VARCHAR}, #{optionValue,jdbcType=VARCHAR}, ",
        "#{optionRemark,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Option record);

    @InsertProvider(type=OptionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Option record);

    @Select({
        "select",
        "id, option_key, option_value, option_remark",
        "from sys_option",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="option_key", property="optionKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="option_value", property="optionValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="option_remark", property="optionRemark", jdbcType=JdbcType.VARCHAR)
    })
    Option selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OptionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Option record);

    @Update({
        "update sys_option",
        "set option_key = #{optionKey,jdbcType=VARCHAR},",
          "option_value = #{optionValue,jdbcType=VARCHAR},",
          "option_remark = #{optionRemark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Option record);
}