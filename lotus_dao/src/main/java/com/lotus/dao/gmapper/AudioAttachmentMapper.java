package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.AudioAttachment;
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

public interface AudioAttachmentMapper {
    @Delete({
        "delete from co_audio_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into co_audio_attachment (content_id, attachment_id)",
        "values (#{contentId,jdbcType=INTEGER}, #{attachmentId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(AudioAttachment record);

    @InsertProvider(type=AudioAttachmentSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(AudioAttachment record);

    @Select({
        "select",
        "id, content_id, attachment_id",
        "from co_audio_attachment",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="content_id", property="contentId", jdbcType=JdbcType.INTEGER),
        @Result(column="attachment_id", property="attachmentId", jdbcType=JdbcType.INTEGER)
    })
    AudioAttachment selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AudioAttachmentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AudioAttachment record);

    @Update({
        "update co_audio_attachment",
        "set content_id = #{contentId,jdbcType=INTEGER},",
          "attachment_id = #{attachmentId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AudioAttachment record);
}