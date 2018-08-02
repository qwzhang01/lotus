package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.PermissionRole;
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

public interface PermissionRoleMapper {
    @Delete({
        "delete from auth_permission_role",
        "where RpId = #{rpid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer rpid);

    @Insert({
        "insert into auth_permission_role (RoleId, PermissionId, ",
        "CreatedOn, CreatedBy, ",
        "ModifiedOn, ModifiedBy)",
        "values (#{roleid,jdbcType=INTEGER}, #{permissionid,jdbcType=INTEGER}, ",
        "#{createdon,jdbcType=TIMESTAMP}, #{createdby,jdbcType=VARCHAR}, ",
        "#{modifiedon,jdbcType=TIMESTAMP}, #{modifiedby,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="rpid", before=false, resultType=Integer.class)
    int insert(PermissionRole record);

    @InsertProvider(type=PermissionRoleSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="rpid", before=false, resultType=Integer.class)
    int insertSelective(PermissionRole record);

    @Select({
        "select",
        "RpId, RoleId, PermissionId, CreatedOn, CreatedBy, ModifiedOn, ModifiedBy",
        "from auth_permission_role",
        "where RpId = #{rpid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="RpId", property="rpid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="RoleId", property="roleid", jdbcType=JdbcType.INTEGER),
        @Result(column="PermissionId", property="permissionid", jdbcType=JdbcType.INTEGER),
        @Result(column="CreatedOn", property="createdon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CreatedBy", property="createdby", jdbcType=JdbcType.VARCHAR),
        @Result(column="ModifiedOn", property="modifiedon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ModifiedBy", property="modifiedby", jdbcType=JdbcType.VARCHAR)
    })
    PermissionRole selectByPrimaryKey(Integer rpid);

    @UpdateProvider(type=PermissionRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PermissionRole record);

    @Update({
        "update auth_permission_role",
        "set RoleId = #{roleid,jdbcType=INTEGER},",
          "PermissionId = #{permissionid,jdbcType=INTEGER},",
          "CreatedOn = #{createdon,jdbcType=TIMESTAMP},",
          "CreatedBy = #{createdby,jdbcType=VARCHAR},",
          "ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP},",
          "ModifiedBy = #{modifiedby,jdbcType=VARCHAR}",
        "where RpId = #{rpid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PermissionRole record);
}