package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.repository.LecturerRepository;
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
public class LecturerRepositoryImplement implements LecturerRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Lecturer> getLecturers() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lecturer> query = builder.createQuery(Lecturer.class);
        Root<Lecturer> root = query.from(Lecturer.class);

        query.select(root);

        return session.createQuery(query).getResultList();
    }
}
