package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.ScoreComponent;
import com.buikhanhhuy.repository.ScoreComponentRepository;
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
public class ScoreComponentRepositoryImplement implements ScoreComponentRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<ScoreComponent> getScoreComponents() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ScoreComponent> query = builder.createQuery(ScoreComponent.class);
        Root<ScoreComponent> root = query.from(ScoreComponent.class);
        query.select(root);

        return session.createQuery(query).getResultList();
    }
}
