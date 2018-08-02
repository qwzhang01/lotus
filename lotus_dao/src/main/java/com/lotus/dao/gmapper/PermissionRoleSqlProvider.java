package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.PermissionRole;
import org.apache.ibatis.jdbc.SQL;

public class PermissionRoleSqlProvider {

    public String insertSelective(PermissionRole record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("auth_permission_role");
        
        if (record.getRoleid() != null) {
            sql.VALUES("RoleId", "#{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionid() != null) {
            sql.VALUES("PermissionId", "#{permissionid,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedon() != null) {
            sql.VALUES("CreatedOn", "#{createdon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("CreatedBy", "#{createdby,jdbcType=VARCHAR}");
        }
        
        if (record.getModifiedon() != null) {
            sql.VALUES("ModifiedOn", "#{modifiedon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedby() != null) {
            sql.VALUES("ModifiedBy", "#{modifiedby,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PermissionRole record) {
        SQL sql = new SQL();
        sql.UPDATE("auth_permission_role");
        
        if (record.getRoleid() != null) {
            sql.SET("RoleId = #{roleid,jdbcType=INTEGER}");
        }
        
        if (record.getPermissionid() != null) {
            sql.SET("PermissionId = #{permissionid,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedon() != null) {
            sql.SET("CreatedOn = #{createdon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedby() != null) {
            sql.SET("CreatedBy = #{createdby,jdbcType=VARCHAR}");
        }
        
        if (record.getModifiedon() != null) {
            sql.SET("ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedby() != null) {
            sql.SET("ModifiedBy = #{modifiedby,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("RpId = #{rpid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}