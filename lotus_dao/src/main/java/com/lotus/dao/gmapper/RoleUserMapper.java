package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.RoleUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

public interface RoleUserMapper {
    @Delete({
        "delete from auth_role_user",
        "where RoleId = #{roleid,jdbcType=INTEGER}",
          "and UserId = #{userid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(@Param("roleid") Integer roleid, @Param("userid") Integer userid);

    @Insert({
        "insert into auth_role_user (RoleId, UserId)",
        "values (#{roleid,jdbcType=INTEGER}, #{userid,jdbcType=INTEGER})"
    })
    int insert(RoleUser record);

    @InsertProvider(type=RoleUserSqlProvider.class, method="insertSelective")
    int insertSelective(RoleUser record);
}