package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.repository.DepartmentRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class DepartmentRepositoryImplement implements DepartmentRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Department> getDepartments() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Department> query = builder.createQuery(Department.class);
        Root<Department> root = query.from(Department.class);
        query.select(root);

        return session.createQuery(query).getResultList();
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
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
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
