package com.buikhanhhuy.repository.implement;

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

    @Override
    public boolean addTopic(Topic topic) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            session.save(topic);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public Topic getTopicById(int topicId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Topic> query = builder.createQuery(Topic.class);
        Root<Topic> root = query.from(Topic.class);
        query.select(root);
        query.where(builder.equal(root.get("id").as(Integer.class), topicId));

        return session.createQuery(query).getSingleResult();
    }

    @Override
    public boolean updateTopic(int topicId, Topic topic) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Topic objTopic = session.get(Topic.class, topicId);
            objTopic.setName(topic.getName());
            objTopic.setDescription(topic.getDescription());
            objTopic.setDepartment(topic.getDepartment());

            session.update(objTopic);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTopic(int topicId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Topic objTopic = session.get(Topic.class, topicId);
            session.delete(objTopic);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
