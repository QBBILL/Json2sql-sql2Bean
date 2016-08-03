package cn.qbbill.dao;

import cn.qbbill.bean.SqlData;
import cn.qbbill.util.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 * Created by 钱斌 on 2016/8/3.
 */
@Repository
public class CommonDao {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private SqlUtil sqlUtils;

    public Long saveTableMap(String tableName, Map<String, Object> result) {
        final SqlData sqlData = sqlUtils.converMapToSql(tableName, result);

        Long id = null;
        if (sqlData != null) {
            KeyHolder keyHolder = new GeneratedKeyHolder();

            /**
             * 在采用---数据库自增主键---的方案里，如果JDBC驱动不能绑定新增记录对应的主键，
             * 就需要手工执行查询语句以获取对应的主键值，
             * 对于高并发的系统，这很容易返回错误的主键。
             * Spring利用这一技术，提供了一个可以返回新增记录对应主键值的方法：
             * int update(PreparedStatementCreator psc, KeyHolder generatedKeyHolder)
             */
            jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement preparedStatement =
                            connection.prepareStatement(sqlData.getSql(), Statement.RETURN_GENERATED_KEYS);
                    for (int i = 1; i <= sqlData.getParams().size(); i++) {
                        preparedStatement.setObject(i, sqlData.getParams().get(i - 1));
                    }
                    return preparedStatement;
                }
            }, keyHolder);
            if (keyHolder.getKey() != null) {
                id = keyHolder.getKey().longValue();
                return id;
            }
        }
        return null;
    }
}
