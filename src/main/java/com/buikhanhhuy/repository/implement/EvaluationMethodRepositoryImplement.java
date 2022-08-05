package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.pojo.ScoreColumn;
import com.buikhanhhuy.pojo.ScoreComponent;
import com.buikhanhhuy.repository.EvaluationMethodRepository;
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

        return session.createQuery(query).getResultList();
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
}
