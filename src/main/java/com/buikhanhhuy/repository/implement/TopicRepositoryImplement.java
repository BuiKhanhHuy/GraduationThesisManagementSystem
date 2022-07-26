package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.pojo.Topic;
import com.buikhanhhuy.repository.TopicRepository;
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
public class TopicRepositoryImplement implements TopicRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Topic> getTopics() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Topic> query = builder.createQuery(Topic.class);
        Root<Topic> root = query.from(Topic.class);
        query.select(root);

        return session.createQuery(query).getResultList();
    }
}
