package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.repository.MajorRepository;
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
public class MajorRepositoryImplement implements MajorRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Major> getMajors() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Major> query = builder.createQuery(Major.class);
        Root<Major> root = query.from(Major.class);

        query.select(root);

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
