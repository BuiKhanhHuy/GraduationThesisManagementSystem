package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.repository.DepartmentRepository;
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
import java.util.Objects;

@Repository
@Transactional
public class DepartmentRepositoryImplement implements DepartmentRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public boolean checkUniqueDepartmentCode(String departmentCode) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Department WHERE code=:code";
        Query query = session.createQuery(sql);
        query.setParameter("code", departmentCode.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public boolean checkUniqueDepartmentName(String departmentName) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Department WHERE name=:name";
        Query query = session.createQuery(sql);
        query.setParameter("name", departmentName.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public List<Department> getDepartments(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
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
    public long countDepartment(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Department> root = query.from(Department.class);
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
    public List<Object[]> getDepartmentOptions() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Department> root = query.from(Department.class);
        query.multiselect(root.get("id"), root.get("name"));

        return session.createQuery(query).getResultList();
    }

    @Override
    public boolean addDepartment(Department department) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        try {
            session.save(department);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public Department getDepartmentById(int departmentId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> root = query.from(Department.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), departmentId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateDepartment(int departmentId, Department department) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Department objDepartment = session.get(Department.class, departmentId);
            objDepartment.setCode(department.getCode());
            objDepartment.setName(department.getName());
            objDepartment.setFounding(department.getFounding());
            objDepartment.setDescription(department.getDescription());

            session.update(objDepartment);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDepartment(int departmentId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Department objDepartment = session.get(Department.class, departmentId);
            session.delete(objDepartment);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean checkCodeExists(String code) {
        return false;
    }
}
