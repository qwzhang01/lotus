package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.RoleUser;
import org.apache.ibatis.jdbc.SQL;

public class RoleUserSqlProvider {

    public String insertSelective(RoleUser record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("auth_role_user");
        
        if (record.getRoleid() != null) {
            sql.VALUES("RoleId", "#{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getUserid() != null) {
            sql.VALUES("UserId", "#{userid,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }
}