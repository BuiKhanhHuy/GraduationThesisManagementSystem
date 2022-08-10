package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.Department;
import com.buikhanhhuy.repository.CouncilRepository;
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
public class CouncilRepositoryImplement implements CouncilRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Override
    public List<Council> getCouncils(Map<String, String> params) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Council> query = builder.createQuery(Council.class);
        Root<Council> root = query.from(Council.class);
        query.select(root);

        Query q = session.createQuery(query);

        return q.getResultList();
    }

    @Override
    public boolean addCouncil(Council council) {
        return false;
    }
}
