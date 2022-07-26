package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.Role;
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
}
