package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Attachment;
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

public interface AttachmentMapper {
    @Delete({
        "delete from at_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into at_attachment (title, author, ",
        "thumbnail, path, ",
        "folder_id, summary)",
        "values (#{title,jdbcType=VARCHAR}, #{author,jdbcType=VARCHAR}, ",
        "#{thumbnail,jdbcType=VARCHAR}, #{path,jdbcType=VARCHAR}, ",
        "#{folderId,jdbcType=INTEGER}, #{summary,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Attachment record);

    @InsertProvider(type=AttachmentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Attachment record);

    @Select({
        "select",
        "id, title, author, thumbnail, path, folder_id, summary",
        "from at_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="author", property="author", jdbcType=JdbcType.VARCHAR),
        @Result(column="thumbnail", property="thumbnail", jdbcType=JdbcType.VARCHAR),
        @Result(column="path", property="path", jdbcType=JdbcType.VARCHAR),
        @Result(column="folder_id", property="folderId", jdbcType=JdbcType.INTEGER),
        @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR)
    })
    Attachment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AttachmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Attachment record);

    @Update({
        "update at_attachment",
        "set title = #{title,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "thumbnail = #{thumbnail,jdbcType=VARCHAR},",
          "path = #{path,jdbcType=VARCHAR},",
          "folder_id = #{folderId,jdbcType=INTEGER},",
          "summary = #{summary,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Attachment record);
}