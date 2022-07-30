package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Manage;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.repository.ManageRepository;
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
public class ManageRepositoryImplement implements ManageRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Manage> getManages() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Manage> query = builder.createQuery(Manage.class);
        Root<Manage> root = query.from(Manage.class);
        query.select(root);

        return session.createQuery(query).getResultList();
    }

    @Override
    public boolean addManage(Manage manage) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            User user = manage.getUser();
            user.setRole(this.roleRepository.getRoleByRoleName("ADMIN"));

            session.save(user);
            session.save(manage);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public Manage getManageById(int manageId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Manage> query = builder.createQuery(Manage.class);
            Root<Manage> root = query.from(Manage.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), manageId));
            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateManage(int manageId, Manage manage) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Manage objManage = session.get(Manage.class, manageId);
            objManage.setFullName(manage.getFullName());
            objManage.setEmail(manage.getEmail());
            objManage.setPhone(manage.getPhone());

            User objUser = session.get(User.class, objManage.getUser().getId());
            objUser.setUsername(manage.getUser().getUsername());
            objUser.setPassword(manage.getUser().getPassword());
//          objUser.setAvatar(manage.getUser().getAvatar());
            objUser.setActive(manage.getUser().getActive());

            session.update(objUser);
            session.update(objManage);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteManage(int manageId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Manage objManage = session.get(Manage.class, manageId);
            User objUser = session.get(User.class, objManage.getUser().getId());

            session.delete(objManage);
            session.delete(objUser);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
