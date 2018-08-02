package com.lotus.service.system;

import com.lotus.common.entity.Page;

import java.util.Map;

public interface ParamService {
    Page<Map<String, Object>> findPageList(String searchKey, Integer pageNumber, Integer pageSize);

    String getCodeName(String type, String code);
}
