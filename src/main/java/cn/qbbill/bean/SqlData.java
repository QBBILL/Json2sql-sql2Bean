package cn.qbbill.bean;

import java.util.List;

/**
 * Created by 钱斌 on 2016/8/3.
 */
public class SqlData {
    private String sql;

    private List<Object> params;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> param) {
        this.params = param;
    }
}
