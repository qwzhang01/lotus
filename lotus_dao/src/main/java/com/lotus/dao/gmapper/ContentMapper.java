package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Content;
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

public interface ContentMapper {
    @Delete({
        "delete from co_content",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into co_content (taxonomy_id, title, ",
        "text, summary, thumbnail, ",
        "is_stick, is_published, ",
        "sort_num, source, ",
        "source_href, created, ",
        "modified, icon)",
        "values (#{taxonomyId,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{text,jdbcType=VARCHAR}, #{summary,jdbcType=VARCHAR}, #{thumbnail,jdbcType=VARCHAR}, ",
        "#{isStick,jdbcType=INTEGER}, #{isPublished,jdbcType=INTEGER}, ",
        "#{sortNum,jdbcType=INTEGER}, #{source,jdbcType=VARCHAR}, ",
        "#{sourceHref,jdbcType=VARCHAR}, #{created,jdbcType=TIMESTAMP}, ",
        "#{modified,jdbcType=TIMESTAMP}, #{icon,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Content record);

    @InsertProvider(type=ContentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Content record);

    @Select({
        "select",
        "id, taxonomy_id, title, text, summary, thumbnail, is_stick, is_published, sort_num, ",
        "source, source_href, created, modified, icon",
        "from co_content",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="taxonomy_id", property="taxonomyId", jdbcType=JdbcType.INTEGER),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="text", property="text", jdbcType=JdbcType.VARCHAR),
        @Result(column="summary", property="summary", jdbcType=JdbcType.VARCHAR),
        @Result(column="thumbnail", property="thumbnail", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_stick", property="isStick", jdbcType=JdbcType.INTEGER),
        @Result(column="is_published", property="isPublished", jdbcType=JdbcType.INTEGER),
        @Result(column="sort_num", property="sortNum", jdbcType=JdbcType.INTEGER),
        @Result(column="source", property="source", jdbcType=JdbcType.VARCHAR),
        @Result(column="source_href", property="sourceHref", jdbcType=JdbcType.VARCHAR),
        @Result(column="created", property="created", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="modified", property="modified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR)
    })
    Content selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ContentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Content record);

    @Update({
        "update co_content",
        "set taxonomy_id = #{taxonomyId,jdbcType=INTEGER},",
          "title = #{title,jdbcType=VARCHAR},",
          "text = #{text,jdbcType=VARCHAR},",
          "summary = #{summary,jdbcType=VARCHAR},",
          "thumbnail = #{thumbnail,jdbcType=VARCHAR},",
          "is_stick = #{isStick,jdbcType=INTEGER},",
          "is_published = #{isPublished,jdbcType=INTEGER},",
          "sort_num = #{sortNum,jdbcType=INTEGER},",
          "source = #{source,jdbcType=VARCHAR},",
          "source_href = #{sourceHref,jdbcType=VARCHAR},",
          "created = #{created,jdbcType=TIMESTAMP},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "icon = #{icon,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Content record);
}