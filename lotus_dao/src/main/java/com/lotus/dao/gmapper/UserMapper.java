package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.User;
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

public interface UserMapper {
    @Delete({
        "delete from auth_user",
        "where UserId = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userid);

    @Insert({
        "insert into auth_user (UserName, Password, ",
        "RealName, Department, ",
        "PasswordType, ContactWay, ",
        "IsOnline, State, ",
        "DeleteMark, CreatedBy, ",
        "CreatedOn, ModifiedBy, ",
        "ModifiedOn, GlobalId, ",
        "LastIpAddress, LastLoginDate, ",
        "OrgId, ExpirationDate)",
        "values (#{username,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{realname,jdbcType=VARCHAR}, #{department,jdbcType=VARCHAR}, ",
        "#{passwordtype,jdbcType=INTEGER}, #{contactway,jdbcType=VARCHAR}, ",
        "#{isonline,jdbcType=INTEGER}, #{state,jdbcType=INTEGER}, ",
        "#{deletemark,jdbcType=INTEGER}, #{createdby,jdbcType=VARCHAR}, ",
        "#{createdon,jdbcType=TIMESTAMP}, #{modifiedby,jdbcType=VARCHAR}, ",
        "#{modifiedon,jdbcType=TIMESTAMP}, #{globalid,jdbcType=INTEGER}, ",
        "#{lastipaddress,jdbcType=VARCHAR}, #{lastlogindate,jdbcType=TIMESTAMP}, ",
        "#{orgid,jdbcType=INTEGER}, #{expirationdate,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userid", before=false, resultType=Integer.class)
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userid", before=false, resultType=Integer.class)
    int insertSelective(User record);

    @Select({
        "select",
        "UserId, UserName, Password, RealName, Department, PasswordType, ContactWay, ",
        "IsOnline, State, DeleteMark, CreatedBy, CreatedOn, ModifiedBy, ModifiedOn, GlobalId, ",
        "LastIpAddress, LastLoginDate, OrgId, ExpirationDate",
        "from auth_user",
        "where UserId = #{userid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="UserId", property="userid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="UserName", property="username", jdbcType=JdbcType.VARCHAR),
        @Result(column="Password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="RealName", property="realname", jdbcType=JdbcType.VARCHAR),
        @Result(column="Department", property="department", jdbcType=JdbcType.VARCHAR),
        @Result(column="PasswordType", property="passwordtype", jdbcType=JdbcType.INTEGER),
        @Result(column="ContactWay", property="contactway", jdbcType=JdbcType.VARCHAR),
        @Result(column="IsOnline", property="isonline", jdbcType=JdbcType.INTEGER),
        @Result(column="State", property="state", jdbcType=JdbcType.INTEGER),
        @Result(column="DeleteMark", property="deletemark", jdbcType=JdbcType.INTEGER),
        @Result(column="CreatedBy", property="createdby", jdbcType=JdbcType.VARCHAR),
        @Result(column="CreatedOn", property="createdon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ModifiedBy", property="modifiedby", jdbcType=JdbcType.VARCHAR),
        @Result(column="ModifiedOn", property="modifiedon", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="GlobalId", property="globalid", jdbcType=JdbcType.INTEGER),
        @Result(column="LastIpAddress", property="lastipaddress", jdbcType=JdbcType.VARCHAR),
        @Result(column="LastLoginDate", property="lastlogindate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="OrgId", property="orgid", jdbcType=JdbcType.INTEGER),
        @Result(column="ExpirationDate", property="expirationdate", jdbcType=JdbcType.VARCHAR)
    })
    User selectByPrimaryKey(Integer userid);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update auth_user",
        "set UserName = #{username,jdbcType=VARCHAR},",
          "Password = #{password,jdbcType=VARCHAR},",
          "RealName = #{realname,jdbcType=VARCHAR},",
          "Department = #{department,jdbcType=VARCHAR},",
          "PasswordType = #{passwordtype,jdbcType=INTEGER},",
          "ContactWay = #{contactway,jdbcType=VARCHAR},",
          "IsOnline = #{isonline,jdbcType=INTEGER},",
          "State = #{state,jdbcType=INTEGER},",
          "DeleteMark = #{deletemark,jdbcType=INTEGER},",
          "CreatedBy = #{createdby,jdbcType=VARCHAR},",
          "CreatedOn = #{createdon,jdbcType=TIMESTAMP},",
          "ModifiedBy = #{modifiedby,jdbcType=VARCHAR},",
          "ModifiedOn = #{modifiedon,jdbcType=TIMESTAMP},",
          "GlobalId = #{globalid,jdbcType=INTEGER},",
          "LastIpAddress = #{lastipaddress,jdbcType=VARCHAR},",
          "LastLoginDate = #{lastlogindate,jdbcType=TIMESTAMP},",
          "OrgId = #{orgid,jdbcType=INTEGER},",
          "ExpirationDate = #{expirationdate,jdbcType=VARCHAR}",
        "where UserId = #{userid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}