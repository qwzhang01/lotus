package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Option;
import org.apache.ibatis.jdbc.SQL;

public class OptionSqlProvider {

    public String insertSelective(Option record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("sys_option");
        
        if (record.getOptionKey() != null) {
            sql.VALUES("option_key", "#{optionKey,jdbcType=VARCHAR}");
        }
        
        if (record.getOptionValue() != null) {
            sql.VALUES("option_value", "#{optionValue,jdbcType=VARCHAR}");
        }
        
        if (record.getOptionRemark() != null) {
            sql.VALUES("option_remark", "#{optionRemark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Option record) {
        SQL sql = new SQL();
        sql.UPDATE("sys_option");
        
        if (record.getOptionKey() != null) {
            sql.SET("option_key = #{optionKey,jdbcType=VARCHAR}");
        }
        
        if (record.getOptionValue() != null) {
            sql.SET("option_value = #{optionValue,jdbcType=VARCHAR}");
        }
        
        if (record.getOptionRemark() != null) {
            sql.SET("option_remark = #{optionRemark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}