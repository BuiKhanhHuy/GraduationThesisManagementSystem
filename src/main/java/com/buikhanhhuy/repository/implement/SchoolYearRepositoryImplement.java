package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.repository.SchoolYearRepository;
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
public class SchoolYearRepositoryImplement implements SchoolYearRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public boolean checkUniqueSchoolYearName(String schoolYearName) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM SchoolYear WHERE name=:name";
        Query query = session.createQuery(sql);
        query.setParameter("name", schoolYearName.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public List<SchoolYear> getSchoolYears(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SchoolYear> query = builder.createQuery(SchoolYear.class);
        Root<SchoolYear> root = query.from(SchoolYear.class);
        query.select(root);

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            query.where(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
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
    public List<Object[]> getSchoolYearOptions() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<SchoolYear> root = query.from(SchoolYear.class);
        query.multiselect(root.get("id"), root.get("name"));

        return session.createQuery(query).getResultList();
    }

    @Override
    public long countSchoolYear(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<SchoolYear> root = query.from(SchoolYear.class);
        query.multiselect(builder.count(root.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            query.where(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        Query q = session.createQuery(query);
        Object result = q.getSingleResult();

        return (long) result;
    }

    @Override
    public boolean addSchoolYear(SchoolYear schoolYear) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            session.save(schoolYear);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public SchoolYear getSchoolYearById(int schoolYearId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<SchoolYear> query = builder.createQuery(SchoolYear.class);
            Root<SchoolYear> root = query.from(SchoolYear.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), schoolYearId));

            return session.createQuery(query).getSingleResult();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateSchoolYear(int schoolYearId, SchoolYear schoolYear) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            SchoolYear objSchoolYear = session.get(SchoolYear.class, schoolYearId);
            objSchoolYear.setName(schoolYear.getName());
            objSchoolYear.setStartDate(schoolYear.getStartDate());
            objSchoolYear.setEndDate(schoolYear.getEndDate());

            session.update(objSchoolYear);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSchoolYear(int schoolYearId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            SchoolYear schoolYear = session.get(SchoolYear.class, schoolYearId);
            session.delete(schoolYear);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
