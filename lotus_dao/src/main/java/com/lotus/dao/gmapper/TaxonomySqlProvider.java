package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Taxonomy;
import org.apache.ibatis.jdbc.SQL;

public class TaxonomySqlProvider {

    public String insertSelective(Taxonomy record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("co_taxonomy");
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=VARCHAR}");
        }
        
        if (record.getAppColumn() != null) {
            sql.VALUES("app_column", "#{appColumn,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderNumber() != null) {
            sql.VALUES("order_number", "#{orderNumber,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Taxonomy record) {
        SQL sql = new SQL();
        sql.UPDATE("co_taxonomy");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=VARCHAR}");
        }
        
        if (record.getAppColumn() != null) {
            sql.SET("app_column = #{appColumn,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderNumber() != null) {
            sql.SET("order_number = #{orderNumber,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}