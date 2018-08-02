package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Permission;
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

public interface PermissionMapper {
    @Delete({
        "delete from auth_permission",
        "where PermissionId = #{permissionid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer permissionid);

    @Insert({
        "insert into auth_permission (Menu, SubMenu, ",
        "Operate, SortNo, ",
        "CreatedOn, CreatedBy, ",
        "ModifiedOn, ModifiedBy)",
        "values (#{menu,jdbcType=VARCHAR}, #{submenu,jdbcType=VARCHAR}, ",
        "#{operate,jdbcType=VARCHAR}, #{sortno,jdbcType=VARCHAR}, ",
        "#{createdon,jdbcType=TIMESTAMP}, #{createdby,jdbcType=VARCHAR}, ",
        "#{modifiedon,jdbcType=TIMESTAMP}, #{modifiedby,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="permissionid", before=false, resultType=Integer.class)
    int insert(Permission record);

    @InsertProvider(type=PermissionSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="permissionid", before=false, resultType=Integer.class)
    int insertSelective(Permission record);

    @Select({
        "select",
        "PermissionId, Menu, SubMenu, Operate, SortNo, CreatedOn, CreatedBy, ModifiedOn, ",
        "ModifiedBy",
        "from auth_permission",
        "where PermissionId = #{permissionid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="PermissionId", property="permissionid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="Menu", property="menu", jdbcType=JdbcType.VARCHAR),
        @Result(column="SubMenu", property="submenu", jdbcType=JdbcType.VARCHAR),
        @Result(column="Operate", property="operate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SortNo", property="sortno", jdbcType=JdbcType.VARCHAR),
        @Result(column="CreatedOn", property="createdon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="CreatedBy", property="createdby", jdbcType=JdbcType.VARCHAR),
        @Result(column="ModifiedOn", property="modifiedon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ModifiedBy", property="modifiedby", jdbcType=JdbcType.VARCHAR)
    })
    Permission selectByPrimaryKey(Integer permissionid);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Permission record);

    @Update({
        "update auth_permission",
        "set Menu = #{menu,jdbcType=VARCHAR},",
          "SubMenu = #{submenu,jdbcType=VARCHAR},",
          "Operate = #{operate,jdbcType=VARCHAR},",
          "SortNo = #{sortno,jdbcType=VARCHAR},",
          "CreatedOn = #{createdon,jdbcType=TIMESTAMP},",
          "CreatedBy = #{createdby,jdbcType=VARCHAR},",
          "ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP},",
          "ModifiedBy = #{modifiedby,jdbcType=VARCHAR}",
        "where PermissionId = #{permissionid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Permission record);
}