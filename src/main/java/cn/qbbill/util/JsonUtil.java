package cn.qbbill.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by 钱斌 on 2016/8/3.
 */
@Component
public class JsonUtil {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * convert string to jsonArray
     *
     * @param jsonString
     * @return
     * @throws IOException
     */
    public List<Map> convertJsonList(String jsonString) throws IOException {
        List list = objectMapper.readValue(jsonString, new TypeReference<List>() {
        });
        return list;
    }

    /**
     * convert string to jsonObject
     *
     * @param jsonString
     * @return
     * @throws IOException
     */
    public Map convertJsonMap(String jsonString) throws IOException {
        Map map = objectMapper.readValue(jsonString, new TypeReference<Map>() {
        });
        return map;
    }

    /**
     * 根据json字符串和节点路径获取json对象/数组
     * jsonUtil.getSpecifiedJson(string, "");根路径节点
     * jsonUtil.getSpecifiedJson(testMapString, "/contacts");获取contacts节点数据
     *
     * @param jsonString
     * @param path
     * @return
     * @throws IOException
     */
    public Object getSpecifiedJson(String jsonString, String path) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        if (jsonNode != null) {
            JsonNode pathJsonNode = jsonNode.at(path);
            return getJsonResult(pathJsonNode);
        }
        return null;
    }

    public Object getSpecifiedJson(String jsonString) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        return getJsonResult(jsonNode);
    }

    private Object getJsonResult(JsonNode jsonNode) {
        if (jsonNode != null) {
            Class type = jsonNode.isArray()? List.class :(jsonNode.isObject()? Map.class: null);
            if(type != null)
            return objectMapper.convertValue(jsonNode, type);
        }
        return null;
    }
}
