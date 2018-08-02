package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Folder;
import org.apache.ibatis.jdbc.SQL;

public class FolderSqlProvider {

    public String insertSelective(Folder record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("at_folder");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getFullPath() != null) {
            sql.VALUES("full_path", "#{fullPath,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Folder record) {
        SQL sql = new SQL();
        sql.UPDATE("at_folder");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getFullPath() != null) {
            sql.SET("full_path = #{fullPath,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}