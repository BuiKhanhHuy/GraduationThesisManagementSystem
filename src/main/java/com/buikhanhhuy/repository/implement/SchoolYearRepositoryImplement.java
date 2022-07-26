package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.repository.SchoolYearRepository;
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
public class SchoolYearRepositoryImplement implements SchoolYearRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<SchoolYear> getSchoolYears() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<SchoolYear> query = builder.createQuery(SchoolYear.class);
        Root<SchoolYear> root = query.from(SchoolYear.class);
        query.select(root);

        return session.createQuery(query).getResultList();
    }
}
