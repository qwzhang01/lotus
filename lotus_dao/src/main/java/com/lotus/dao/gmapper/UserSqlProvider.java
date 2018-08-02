package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("auth_user");
        
        if (record.getUsername() != null) {
            sql.VALUES("UserName", "#{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("Password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getRealname() != null) {
            sql.VALUES("RealName", "#{realname,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.VALUES("Department", "#{department,jdbcType=VARCHAR}");
        }
        
        if (record.getPasswordtype() != null) {
            sql.VALUES("PasswordType", "#{passwordtype,jdbcType=INTEGER}");
        }
        
        if (record.getContactway() != null) {
            sql.VALUES("ContactWay", "#{contactway,jdbcType=VARCHAR}");
        }
        
        if (record.getIsonline() != null) {
            sql.VALUES("IsOnline", "#{isonline,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("State", "#{state,jdbcType=INTEGER}");
        }
        
        if (record.getDeletemark() != null) {
            sql.VALUES("DeleteMark", "#{deletemark,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedby() != null) {
            sql.VALUES("CreatedBy", "#{createdby,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedon() != null) {
            sql.VALUES("CreatedOn", "#{createdon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedby() != null) {
            sql.VALUES("ModifiedBy", "#{modifiedby,jdbcType=VARCHAR}");
        }
        
        if (record.getModifiedon() != null) {
            sql.VALUES("ModifiedOn", "#{modifiedon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getGlobalid() != null) {
            sql.VALUES("GlobalId", "#{globalid,jdbcType=INTEGER}");
        }
        
        if (record.getLastipaddress() != null) {
            sql.VALUES("LastIpAddress", "#{lastipaddress,jdbcType=VARCHAR}");
        }
        
        if (record.getLastlogindate() != null) {
            sql.VALUES("LastLoginDate", "#{lastlogindate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrgid() != null) {
            sql.VALUES("OrgId", "#{orgid,jdbcType=INTEGER}");
        }
        
        if (record.getExpirationdate() != null) {
            sql.VALUES("ExpirationDate", "#{expirationdate,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("auth_user");
        
        if (record.getUsername() != null) {
            sql.SET("UserName = #{username,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("Password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getRealname() != null) {
            sql.SET("RealName = #{realname,jdbcType=VARCHAR}");
        }
        
        if (record.getDepartment() != null) {
            sql.SET("Department = #{department,jdbcType=VARCHAR}");
        }
        
        if (record.getPasswordtype() != null) {
            sql.SET("PasswordType = #{passwordtype,jdbcType=INTEGER}");
        }
        
        if (record.getContactway() != null) {
            sql.SET("ContactWay = #{contactway,jdbcType=VARCHAR}");
        }
        
        if (record.getIsonline() != null) {
            sql.SET("IsOnline = #{isonline,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.SET("State = #{state,jdbcType=INTEGER}");
        }
        
        if (record.getDeletemark() != null) {
            sql.SET("DeleteMark = #{deletemark,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedby() != null) {
            sql.SET("CreatedBy = #{createdby,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedon() != null) {
            sql.SET("CreatedOn = #{createdon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifiedby() != null) {
            sql.SET("ModifiedBy = #{modifiedby,jdbcType=VARCHAR}");
        }
        
        if (record.getModifiedon() != null) {
            sql.SET("ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP}");
        }
        
        if (record.getGlobalid() != null) {
            sql.SET("GlobalId = #{globalid,jdbcType=INTEGER}");
        }
        
        if (record.getLastipaddress() != null) {
            sql.SET("LastIpAddress = #{lastipaddress,jdbcType=VARCHAR}");
        }
        
        if (record.getLastlogindate() != null) {
            sql.SET("LastLoginDate = #{lastlogindate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getOrgid() != null) {
            sql.SET("OrgId = #{orgid,jdbcType=INTEGER}");
        }
        
        if (record.getExpirationdate() != null) {
            sql.SET("ExpirationDate = #{expirationdate,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("UserId = #{userid,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}