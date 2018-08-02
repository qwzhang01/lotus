package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Folder;
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

public interface FolderMapper {
    @Delete({
        "delete from at_folder",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into at_folder (name, full_path)",
        "values (#{name,jdbcType=VARCHAR}, #{fullPath,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Folder record);

    @InsertProvider(type=FolderSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Folder record);

    @Select({
        "select",
        "id, name, full_path",
        "from at_folder",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="full_path", property="fullPath", jdbcType=JdbcType.VARCHAR)
    })
    Folder selectByPrimaryKey(Integer id);

    @UpdateProvider(type=FolderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Folder record);

    @Update({
        "update at_folder",
        "set name = #{name,jdbcType=VARCHAR},",
          "full_path = #{fullPath,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Folder record);
}