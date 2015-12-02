package com.dianping.paas.core.dao.support;

import com.dianping.paas.core.entity.Entity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * base crud for dao
 * <p/>
 * chao.yu@dianping.com
 * Created by yuchao on 11/3/15.
 */
@Component
public abstract class DefaultCrudDao<T extends Entity> implements CrudDao<T> {

    @Resource
    private GenericDaoSupport genericDaoSupport;

    private String insertStatement;
    private String deleteStatement;
    private String updateStatement;
    private String selectOneStatement;
    private String selectAllStatement;

    public DefaultCrudDao() {
        String namespace = getNamespace();
        insertStatement = String.format("%s.insert", namespace);
        deleteStatement = String.format("%s.delete", namespace);
        updateStatement = String.format("%s.update", namespace);
        selectOneStatement = String.format("%s.selectOne", namespace);
        selectAllStatement = String.format("%s.selectAll", namespace);
    }

    public int delete(Long id) {
        return genericDaoSupport.delete(deleteStatement, id);
    }


    public int update(T entity) {
        return genericDaoSupport.update(updateStatement, entity);
    }


    public List<T> findAll() {
        return genericDaoSupport.getList(selectAllStatement, null);
    }

    public T findOne(Long id) {
        return (T) genericDaoSupport.get(selectOneStatement, id);
    }

    public Long save(Entity entity) {
        return (Long) genericDaoSupport.insert(insertStatement, entity);
    }

    public GenericDaoSupport getGenericDaoSupport() {
        return genericDaoSupport;
    }
}
