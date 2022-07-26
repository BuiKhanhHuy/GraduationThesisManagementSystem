package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.pojo.Role;
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
}
