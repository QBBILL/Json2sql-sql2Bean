package cn.qbbill.util;

import cn.qbbill.bean.SqlData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 钱斌 on 2016/8/3.
 */
@Component
public class SqlUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将hashmap拼接为sql语句
     * @param tableName
     * @param result
     * @return
     */
    public SqlData converMapToSql(String tableName, Map<String, Object> result){
        if (StringUtils.isBlank(tableName)){
            logger.info("将Map转换为SQL发生错误,表名不能为空!");
            return null;
        }

        if (result == null || result.size() == 0){
            logger.info("将{}表关联的Map转换为SQL发生错误,Map不能为空!", tableName);
            return null;
        }

        StringBuilder sql = new StringBuilder();
        StringBuilder sqlValues = new StringBuilder();
        List<Object> params = new ArrayList<Object>();

        sql.append("INSERT INTO ");
        sql.append("`").append(tableName).append("` ");
        sql.append("(");

        sqlValues.append("VALUES (");

        for(String key : result.keySet()){
            sql.append("`").append(key).append("`,");
            sqlValues.append("?,");
            params.add(result.get(key));
        }

        sql = sql.deleteCharAt(sql.length() - 1);
        sqlValues = sqlValues.deleteCharAt(sqlValues.length() - 1);

        sql.append(") ");
        sqlValues.append(")");

        SqlData sqlData = new SqlData();
        sqlData.setSql(sql.append(sqlValues).toString());
        sqlData.setParams(params);

        return sqlData;
    }
}
