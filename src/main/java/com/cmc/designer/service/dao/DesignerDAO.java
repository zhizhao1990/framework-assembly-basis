package com.cmc.designer.service.dao;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cmc.designer.facade.entity.Designer;

/**
 * Design DAO
 * 
 * <p>
 * <ul>Hibernate语句
 * <li>NativeSQL，本地查询语句，不便于跨平台服务，不常用但是比较基础</li>
 * <li>HQL，Hibernate查询语句，设置方言之后可以使用不同的数据库，是一种面向对象的数据库DML</li>
 * <li>EJBQL（JPQL 1.0）</li>
 * <li>QBC，Query By Criterion</li>
 * <li>QBE，Query By Example，QBC的一部分</li>
 * </ul>
 * </p>
 * 
 * @author Thomas Lee
 * @version 2017年3月21日 上午10:24:00
 */
public class DesignerDAO implements IDesignerDAO {

    private static final Logger LOG = LoggerFactory.getLogger(DesignerDAO.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Designer> selectDesigners() {
        // 实体名称
        String hql = "from Designer";
        Session session = sessionFactory.getCurrentSession();
        // 通过setCacheable(Boolean.TRUE)方法启动查询缓存
        Query query = session.createQuery(hql).setCacheable(Boolean.TRUE);
        // 查询缓存（依赖二级缓存）：在第一次执行list时,会把查询对象的id缓存到查询缓存里，第二次执行list时, 会遍历查询缓存里的id到缓存里去找实体对象,由于这里开启了二级缓存,可以找到目标实体对象。
        return query.list();
    }

    @Override
    public boolean insert(Designer designer) {
        // Session session = sessionFactory.getCurrentSession();
        // return session.save(designer).equals(new Integer(0));

        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(designer);
        designer.setNiceName("NICE NAME");
        session.getTransaction().commit();
        return true;
    }

    @Override
    public void delete(Designer designer) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(designer);
    }

    @Override
    public void update(Designer designer) {
        Session session = sessionFactory.getCurrentSession();
        session.update(designer);
    }

    @Override
    public Designer select(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Designer) session.get(Designer.class, id);
    }

    /*************************************************Part：Hibernate语句练习Begin**************************************************/

    /**
     * NaviteSQL
     * 
     * <h4>注意：</h4>
     * <ul>
     * <li>创建的是SQLQuery，而不是Query；</li>
     * <li>查询对象不再是Bean Entity，而是对应的表明；</li>
     * <li>addEntity(Entity.class)指定返回的具体Bean Entity.</li>
     * </ul>
     * @author Thomas Lee
     * @version 2017年3月22日 上午10:33:20
     */
    public void testNativeSQL() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "select * from designer";
        SQLQuery query = session.createSQLQuery(sql);
        query.addEntity(Designer.class);
        @SuppressWarnings("unchecked")
        Iterator<Designer> iDesigner = query.iterate();
        while (iDesigner.hasNext()) {
            Designer designer = iDesigner.next();
            LOG.info(designer.toString());
        }
    }

    /**
     * HQL
     * @author Thomas Lee
     * @version 2017年3月22日 上午11:35:02
     */
    public void testHQL() {
        String hql = "from Designer";
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hql);
        @SuppressWarnings("unchecked")
        Iterator<Designer> iDesigner = query.iterate();
        while (iDesigner.hasNext()) {
            Designer designer = iDesigner.next();
            LOG.info(designer.toString());
        }
    }

    public void testEJBQL() {

    }

    /**
     * QBC，Query By Criterion
     * @author Thomas Lee
     * @version 2017年3月22日 上午11:35:40
     */
    public void testQBC() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Designer.class).add(Restrictions.gt("id", 2));
        @SuppressWarnings("unchecked")
        List<Designer> designers = criteria.list();
        Iterator<Designer> iDesigner = designers.iterator();
        while (iDesigner.hasNext()) {
            Designer designer = iDesigner.next();
            LOG.info(designer.toString());
        }
    }

    /**
     * QBE，Query By Example
     * @author Thomas Lee
     * @version 2017年3月22日 上午11:36:18
     */
    public void testQBE() {
        Session session = sessionFactory.getCurrentSession();

        Designer designer = new Designer();
        designer.setAge(10);
        Example example = Example.create(designer);

        Criteria criteria = session.createCriteria(Designer.class).add(Restrictions.gt("id", 2)).add(Restrictions.lt("id", 5)).add(example);
        @SuppressWarnings("unchecked")
        List<Designer> designers = criteria.list();
        Iterator<Designer> iDesigner = designers.iterator();
        while (iDesigner.hasNext()) {
            LOG.info(iDesigner.next().toString());
        }

    }
    /*************************************************Part：Hibernate语句练习Finish**************************************************/

}