package com.lotus.dao.gmapper;

import com.lotus.dao.pojo.Content;
import org.apache.ibatis.jdbc.SQL;

public class ContentSqlProvider {

    public String insertSelective(Content record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("co_content");
        
        if (record.getTaxonomyId() != null) {
            sql.VALUES("taxonomy_id", "#{taxonomyId,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.VALUES("title", "#{title,jdbcType=VARCHAR}");
        }
        
        if (record.getText() != null) {
            sql.VALUES("text", "#{text,jdbcType=VARCHAR}");
        }
        
        if (record.getSummary() != null) {
            sql.VALUES("summary", "#{summary,jdbcType=VARCHAR}");
        }
        
        if (record.getThumbnail() != null) {
            sql.VALUES("thumbnail", "#{thumbnail,jdbcType=VARCHAR}");
        }
        
        if (record.getIsStick() != null) {
            sql.VALUES("is_stick", "#{isStick,jdbcType=INTEGER}");
        }
        
        if (record.getIsPublished() != null) {
            sql.VALUES("is_published", "#{isPublished,jdbcType=INTEGER}");
        }
        
        if (record.getSortNum() != null) {
            sql.VALUES("sort_num", "#{sortNum,jdbcType=INTEGER}");
        }
        
        if (record.getSource() != null) {
            sql.VALUES("source", "#{source,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceHref() != null) {
            sql.VALUES("source_href", "#{sourceHref,jdbcType=VARCHAR}");
        }
        
        if (record.getCreated() != null) {
            sql.VALUES("created", "#{created,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModified() != null) {
            sql.VALUES("modified", "#{modified,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIcon() != null) {
            sql.VALUES("icon", "#{icon,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Content record) {
        SQL sql = new SQL();
        sql.UPDATE("co_content");
        
        if (record.getTaxonomyId() != null) {
            sql.SET("taxonomy_id = #{taxonomyId,jdbcType=INTEGER}");
        }
        
        if (record.getTitle() != null) {
            sql.SET("title = #{title,jdbcType=VARCHAR}");
        }
        
        if (record.getText() != null) {
            sql.SET("text = #{text,jdbcType=VARCHAR}");
        }
        
        if (record.getSummary() != null) {
            sql.SET("summary = #{summary,jdbcType=VARCHAR}");
        }
        
        if (record.getThumbnail() != null) {
            sql.SET("thumbnail = #{thumbnail,jdbcType=VARCHAR}");
        }
        
        if (record.getIsStick() != null) {
            sql.SET("is_stick = #{isStick,jdbcType=INTEGER}");
        }
        
        if (record.getIsPublished() != null) {
            sql.SET("is_published = #{isPublished,jdbcType=INTEGER}");
        }
        
        if (record.getSortNum() != null) {
            sql.SET("sort_num = #{sortNum,jdbcType=INTEGER}");
        }
        
        if (record.getSource() != null) {
            sql.SET("source = #{source,jdbcType=VARCHAR}");
        }
        
        if (record.getSourceHref() != null) {
            sql.SET("source_href = #{sourceHref,jdbcType=VARCHAR}");
        }
        
        if (record.getCreated() != null) {
            sql.SET("created = #{created,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModified() != null) {
            sql.SET("modified = #{modified,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIcon() != null) {
            sql.SET("icon = #{icon,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}