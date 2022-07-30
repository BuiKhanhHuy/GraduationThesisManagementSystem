package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Role;
import com.buikhanhhuy.repository.RoleRepository;
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
public class RoleRepositoryImplement implements RoleRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Role> getRoles(String kw) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);

        query.select(root);

        if (kw != null && !kw.isEmpty()) {
            query.where(builder.like(root.<String>get("roleName"), String.format("%%%s%%", kw)));
        }

        return session.createQuery(query).getResultList();
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Role> query = builder.createQuery(Role.class);
            Root<Role> root = query.from(Role.class);
            query.select(root);
            query.where(builder.equal(root.get("roleName").as(String.class), roleName));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
