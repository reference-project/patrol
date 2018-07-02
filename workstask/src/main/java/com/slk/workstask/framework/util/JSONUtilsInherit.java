package com.slk.workstask.framework.util;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class JSONUtilsInherit extends JSONUtils {
    public static ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
    }
    public static <T> T objectFromJsonStr(String content,Class<T> valueType) throws Exception {
        T obj = objectMapper.readValue(content, valueType);
        return obj;
    };


}
