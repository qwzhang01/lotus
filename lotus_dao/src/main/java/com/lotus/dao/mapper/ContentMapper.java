package com.lotus.dao.mapper;

import com.lotus.dao.pojo.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface ContentMapper extends com.lotus.dao.gmapper.ContentMapper {

    /**
     * 查询列表
     *
     * @param appColumn
     * @param sticky
     * @return
     */
    List<Map<String, Object>> selectList(@Param("appColumn") String appColumn, @Param("sticky") Integer sticky);

    /**
     * 查询列表
     *
     * @param contentType 内容类型 article audio
     * @param title
     * @param begin
     * @param end
     * @return
     */
    List<Map<String, Object>> selectListAdmin(@Param("contentType") String contentType, @Param("taxonomyId") Integer taxonomyId, @Param("title") String title,
                                              @Param("begin") Date begin, @Param("end") Date end);

    /**
     * 获取邻居
     *
     * @param upOrDown   up<p>
     *                   "SELECT * FROM co_content WHERE sort_num > ? AND taxonomy_id = ? ORDER BY sort_num ASC LIMIT 1",
     *                   down<p>
     *                   "SELECT * FROM co_content WHERE sort_num < ? AND taxonomy_id = ? ORDER BY sort_num DESC LIMIT 1",
     * @param sortNum
     * @param taxonomyId
     * @return
     */
    Content selectNeighbor(@Param("upOrDown") String upOrDown, @Param("sortNum") Integer sortNum, @Param("taxonomyId") Integer taxonomyId);

    /**
     * 查询最大的排序数据
     *
     * @param taxonomyId
     * @return
     */
    Integer selectMaxSortNum(@Param("taxonomyId") Integer taxonomyId);
}