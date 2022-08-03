package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Topic;
import com.buikhanhhuy.repository.TopicRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
public class TopicRepositoryImplement implements TopicRepository {
    @Autowired
    private Environment environment;
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Topic> getTopics(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Topic> query = builder.createQuery(Topic.class);
        Root<Topic> root = query.from(Topic.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if(params.containsKey("departmentId") && !params.get("departmentId").isEmpty()){
            int departmentId = Integer.parseInt(params.get("departmentId"));
            predicates.add(builder.equal(root.get("department"), departmentId));
        }

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            predicates.add(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        query.where(predicates.toArray(new Predicate[]{}));

        int page = 1;
        int pageSize = Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("pageSize")));

        if (params.containsKey("page") && !params.get("page").isEmpty()) page = Integer.parseInt(params.get("page"));

        Query q = session.createQuery(query);

        int startPage = (page - 1) * pageSize;
        q.setFirstResult(startPage);
        q.setMaxResults(pageSize);

        return q.getResultList();
    }

    @Override
    public long countTopic(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Topic> root = query.from(Topic.class);
        query.multiselect(builder.count(root.get("id")));

        List<Predicate> predicates = new ArrayList<>();

        if(params.containsKey("departmentId") && !params.get("departmentId").isEmpty()){
            int departmentId = Integer.parseInt(params.get("departmentId"));
            predicates.add(builder.equal(root.get("department"), departmentId));
        }

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            predicates.add(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        query.where(predicates.toArray(new Predicate[]{}));

        Query q = session.createQuery(query);
        Object result =  q.getSingleResult();

        return (long) result;
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
