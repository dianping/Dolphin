package com.dianping.paas.core.dao.support;

import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 一站式dao服务
 * <p/>
 * Created by yuchao on 15/8/13.
 */
@Component
public class GenericDaoSupport {

    @Resource
    private SqlMapClientTemplate sqlMapClientTemplate;

    public <T> T insert(String statementName, Object parameterObject) {
        return (T) sqlMapClientTemplate.insert(statementName, parameterObject);
    }

    public Integer delete(String statementName, Object parameterObject) {
        return sqlMapClientTemplate.delete(statementName, parameterObject);
    }

    public <E> E get(String statementName, Object parameterObject) {
        return (E) sqlMapClientTemplate.queryForObject(statementName, parameterObject);
    }

    public <E> List<E> getList(String statementName, Object parameterObject) {
        return sqlMapClientTemplate.queryForList(statementName, parameterObject);
    }

    public Integer update(String statementName, Object parameterObject) {
        return sqlMapClientTemplate.update(statementName, parameterObject);
    }
}
