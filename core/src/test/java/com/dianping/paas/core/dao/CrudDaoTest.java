package com.dianping.paas.core.dao;

import com.dianping.paas.core.entity.Entity;
import com.dianping.paas.test.SpringTest;
import com.dianping.paas.core.dao.support.CrudDao;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 该类在每一个方法测试前会将{@link #entity}插入数据库, 并且将数据库主键保存在 {@link #id}字段
 * 在每一个方法测试后将{@link #id}对应的数据库记录删除, 如果不想被删除,那么在方法结束前调用{@link #doNotAutoDelete()}
 * <p/>
 * chao.yu@dianping.com
 * Created by yuchao on 11/3/15.
 */
public abstract class CrudDaoTest extends SpringTest {

    protected Long id;
    protected Entity entity;
    protected CrudDao crudDao;

    @Before
    public void setUp() throws Exception {
        prepareData();
        id = crudDao.save(this.entity);
    }

    @After
    public void tearDown() throws Exception {
        if (id != null) {
            crudDao.delete(id);
        }
    }

    /**
     * 注入dao和entity
     */
    protected abstract void prepareData();

    protected void setMetadata(CrudDao crudDao, Entity entity) {
        this.crudDao = crudDao;
        this.entity = entity;
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertNotNull(id);
    }

    @Test
    public void testDelete() throws Exception {
        Assert.assertTrue(crudDao.delete(id) > 0);
        doNotAutoDelete();
    }

    protected void doNotAutoDelete() {
        id = null;
    }

    @Test
    public void testFindAll() throws Exception {
        Assert.assertTrue(crudDao.findAll().size() > 0);
    }

    @Test
    public void testFindOne() throws Exception {
        Entity entityInDB = crudDao.findOne(id);
        Assert.assertEquals(id, entityInDB.getId());
    }


    @Test
    public void testUpdate() throws Exception {
        entity.setId(id);
        doUpdate();
        Assert.assertTrue(crudDao.update(entity) > 0);
    }

    /**
     * 具体update的事情
     */
    protected abstract void doUpdate();
}