package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.AudioAttachment;
import org.apache.ibatis.jdbc.SQL;

public class AudioAttachmentSqlProvider {

    public String insertSelective(AudioAttachment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("co_audio_attachment");
        
        if (record.getContentId() != null) {
            sql.VALUES("content_id", "#{contentId,jdbcType=INTEGER}");
        }
        
        if (record.getAttachmentId() != null) {
            sql.VALUES("attachment_id", "#{attachmentId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(AudioAttachment record) {
        SQL sql = new SQL();
        sql.UPDATE("co_audio_attachment");
        
        if (record.getContentId() != null) {
            sql.SET("content_id = #{contentId,jdbcType=INTEGER}");
        }
        
        if (record.getAttachmentId() != null) {
            sql.SET("attachment_id = #{attachmentId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}