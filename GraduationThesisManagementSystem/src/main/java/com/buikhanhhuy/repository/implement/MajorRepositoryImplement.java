package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.repository.MajorRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class MajorRepositoryImplement implements MajorRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public boolean checkUniqueMajorCode(String majorCode) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Major WHERE code=:code";
        Query query = session.createQuery(sql);
        query.setParameter("code", majorCode.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public boolean checkUniqueMajorName(String majorName) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Major WHERE name=:name";
        Query query = session.createQuery(sql);
        query.setParameter("name", majorName.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public List<Major> getMajors(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Major> query = builder.createQuery(Major.class);
        Root<Major> root = query.from(Major.class);

        query.select(root);

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            query.where(builder.like(root.get("code").as(String.class), String.format("%%%s%%", kw)));
        }
        query.orderBy(builder.desc(root.get("id")));

        int page = 1;
        int pageSize = SystemConstant.PAGE_SIZE;

        if (params.containsKey("page") && !params.get("page").isEmpty()) page = Integer.parseInt(params.get("page"));

        Query q = session.createQuery(query);

        int startPage = (page - 1) * pageSize;
        q.setFirstResult(startPage);
        q.setMaxResults(pageSize);

        return q.getResultList();
    }

    @Override
    public long countMajor(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Major> root = query.from(Major.class);
        query.multiselect(builder.count(root.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            query.where(builder.like(root.get("code").as(String.class), String.format("%%%s%%", kw)));
        }

        Query q = session.createQuery(query);
        Object result =  q.getSingleResult();

        return (long) result;
    }

    @Override
    public List<Object[]> getMajorOptions() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Major> root = query.from(Major.class);
        query.multiselect(root.get("id"), root.get("name"));

        return session.createQuery(query).getResultList();
    }

    @Override
    public boolean addMajor(Major major) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            session.save(major);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public Major getMajorById(int majorId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Major> query = builder.createQuery(Major.class);
            Root<Major> root = query.from(Major.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), majorId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateMajor(int majorId, Major major) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Major objMajor = session.get(Major.class, majorId);
            objMajor.setCode(major.getCode());
            objMajor.setName(major.getName());
            objMajor.setDescription(major.getDescription());
            objMajor.setDepartment(major.getDepartment());

            session.update(objMajor);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteMajor(int majorId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Major objMajor = session.get(Major.class, majorId);
            session.delete(objMajor);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
