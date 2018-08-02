package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Permission;
import org.apache.ibatis.jdbc.SQL;

public class PermissionSqlProvider {

    public String insertSelective(Permission record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("auth_permission");
        
        if (record.getMenu() != null) {
            sql.VALUES("Menu", "#{menu,jdbcType=VARCHAR}");
        }
        
        if (record.getSubmenu() != null) {
            sql.VALUES("SubMenu", "#{submenu,jdbcType=VARCHAR}");
        }
        
        if (record.getOperate() != null) {
            sql.VALUES("Operate", "#{operate,jdbcType=VARCHAR}");
        }
        
        if (record.getSortno() != null) {
            sql.VALUES("SortNo", "#{sortno,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(Permission record) {
        SQL sql = new SQL();
        sql.UPDATE("auth_permission");
        
        if (record.getMenu() != null) {
            sql.SET("Menu = #{menu,jdbcType=VARCHAR}");
        }
        
        if (record.getSubmenu() != null) {
            sql.SET("SubMenu = #{submenu,jdbcType=VARCHAR}");
        }
        
        if (record.getOperate() != null) {
            sql.SET("Operate = #{operate,jdbcType=VARCHAR}");
        }
        
        if (record.getSortno() != null) {
            sql.SET("SortNo = #{sortno,jdbcType=VARCHAR}");
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
        
        sql.WHERE("PermissionId = #{permissionid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}