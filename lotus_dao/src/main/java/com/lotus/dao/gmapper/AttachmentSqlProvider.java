package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Attachment;
import org.apache.ibatis.jdbc.SQL;

public class AttachmentSqlProvider {

    public String insertSelective(Attachment record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("at_attachment");
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            sql.VALUES("author", "#{author,jdbcType=VARCHAR}");
        }
        
        if (record.getThumbnail() != null) {
            sql.VALUES("thumbnail", "#{thumbnail,jdbcType=VARCHAR}");
        }
        
        if (record.getPath() != null) {
            sql.VALUES("path", "#{path,jdbcType=VARCHAR}");
        }
        
        if (record.getFolderId() != null) {
            sql.VALUES("folder_id", "#{folderId,jdbcType=INTEGER}");
        }
        
        if (record.getSummary() != null) {
            sql.VALUES("summary", "#{summary,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Attachment record) {
        SQL sql = new SQL();
        sql.UPDATE("at_attachment");
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getAuthor() != null) {
            sql.SET("author = #{author,jdbcType=VARCHAR}");
        }
        
        if (record.getThumbnail() != null) {
            sql.SET("thumbnail = #{thumbnail,jdbcType=VARCHAR}");
        }
        
        if (record.getPath() != null) {
            sql.SET("path = #{path,jdbcType=VARCHAR}");
        }
        
        if (record.getFolderId() != null) {
            sql.SET("folder_id = #{folderId,jdbcType=INTEGER}");
        }
        
        if (record.getSummary() != null) {
            sql.SET("summary = #{summary,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}