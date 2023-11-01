package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.*;
import com.buikhanhhuy.repository.EvaluationMethodRepository;
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

@Repository
@Transactional
public class EvaluationMethodRepositoryImplement implements EvaluationMethodRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<EvaluationMethod> getEvaluationMethods() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<EvaluationMethod> query = builder.createQuery(EvaluationMethod.class);
        Root<EvaluationMethod> root = query.from(EvaluationMethod.class);
        query.select(root);
        query.orderBy(builder.desc(root.get("id")));

        return session.createQuery(query).getResultList();
    }

    @Override
    public long countEvaluationMethod(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<EvaluationMethod> root = query.from(EvaluationMethod.class);
        query.multiselect(builder.count(root.get("id")));

        Query q = session.createQuery(query);
        Object result = q.getSingleResult();

        return (long) result;
    }

    @Override
    public EvaluationMethod getEvaluationMethodById(int evaluationMethodId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<EvaluationMethod> query = builder.createQuery(EvaluationMethod.class);
            Root<EvaluationMethod> root = query.from(EvaluationMethod.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), evaluationMethodId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public EvaluationMethod getEvaluationMethodActive() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<EvaluationMethod> query = builder.createQuery(EvaluationMethod.class);
            Root<EvaluationMethod> root = query.from(EvaluationMethod.class);
            query.select(root);
            query.where(builder.equal(root.get("active"), true));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean addEvaluationMethod(EvaluationMethod evaluationMethod) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            session.save(evaluationMethod);

            for (ScoreComponent scoreComponent : evaluationMethod.getScoreComponents()) {
                scoreComponent.setEvaluationMethod(evaluationMethod);
                session.save(scoreComponent);

                for (ScoreColumn scoreColumn : scoreComponent.getScoreColumns()) {
                    scoreColumn.setScoreComponent(scoreComponent);
                    session.save(scoreColumn);
                }
            }

            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateEvaluationMethod(int evaluationMethodId, EvaluationMethod evaluationMethod) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            EvaluationMethod objEvaluationMethod = session.get(EvaluationMethod.class, evaluationMethodId);

            for (ScoreComponent scoreComponent : objEvaluationMethod.getScoreComponents()) {
                ScoreComponent objScoreComponent = session.get(ScoreComponent.class, scoreComponent.getId());


                for (ScoreColumn scoreColumn : scoreComponent.getScoreColumns()) {
                    ScoreColumn objScoreColumn = session.get(ScoreColumn.class, scoreColumn.getId());
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteEvaluationMethod(int evaluationMethodId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            EvaluationMethod objEvaluationMethod = session.get(EvaluationMethod.class, evaluationMethodId);
            session.delete(objEvaluationMethod);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean activeAEvaluationMethod(int evaluationMethodId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            String sql = "UPDATE EvaluationMethod e SET e.active=false";
            session.createQuery(sql).executeUpdate();

            EvaluationMethod evaluationMethod = session.get(EvaluationMethod.class, evaluationMethodId);
            evaluationMethod.setActive(true);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
