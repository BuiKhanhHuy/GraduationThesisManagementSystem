package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Manage;
import com.buikhanhhuy.pojo.Role;
import com.buikhanhhuy.repository.ManageRepository;
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

@Repository
@Transactional
public class ManageRepositoryImplement implements ManageRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Manage> getManages() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Manage> query = builder.createQuery(Manage.class);
        Root<Manage> root = query.from(Manage.class);
        query.select(root);

        return session.createQuery(query).getResultList();
    }
}
