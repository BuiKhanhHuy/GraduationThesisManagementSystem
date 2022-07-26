package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.EvaluationMethod;
import com.buikhanhhuy.pojo.Manage;
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
}
