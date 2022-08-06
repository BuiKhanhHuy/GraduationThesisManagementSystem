package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
@Transactional
public class UserRepositoryImplement implements UserRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Object[]> getUsers(Map<String, String> params) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<User> root = query.from(User.class);
        query.multiselect(root.get("id"), root.get("username"));
        List<Predicate> predicates = new ArrayList<>();

        if (params.containsKey("roleId") && !params.get("roleId").isEmpty()) {
            Predicate predicate1 = builder.notEqual(root.get("role"), Integer.parseInt(params.get("roleId")));

            if (Integer.parseInt(params.get("roleId")) == 4 && params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
                // LEFT JOIN
                Join<User, Student> studentRoot = root.join("student", JoinType.LEFT);

                Predicate predicate2 = builder.notEqual(studentRoot.get("schoolYear"), Integer.parseInt(params.get("schoolYearId")));

                predicates.add(builder.or(predicate1, predicate2));

            } else {
                predicates.add(predicate1);
            }
        }

        query.where(predicates.toArray(new Predicate[]{}));

        return session.createQuery(query).getResultList();
    }

    @Override
    public Set<Integer> getUsers(Map<String, String> params, List<Integer> usersId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<User> root = query.from(User.class);
            query.multiselect(root.get("id"));
            List<Predicate> predicates = new ArrayList<>();
            Set<Integer> usersIdResult = new HashSet<>();

            if (params.containsKey("roleId") && !params.get("roleId").isEmpty()) {
                Predicate predicate1 = builder.equal(root.get("role"), Integer.parseInt(params.get("roleId")));

                if (Integer.parseInt(params.get("roleId")) == 4 && params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
                    // LEFT JOIN
                    Join<User, Student> studentRoot = root.join("student", JoinType.LEFT);

                    Predicate predicate2 = builder.equal(studentRoot.get("schoolYear"), Integer.parseInt(params.get("schoolYearId")));

                    predicates.add(predicate2);
                } else {
                    predicates.add(predicate1);
                }
            }

            query.where(predicates.toArray(new Predicate[]{}));
            Query q = session.createQuery(query);
            List<Object> rs = q.getResultList();

            for (Object o : rs) {
                usersIdResult.add(Integer.parseInt(o.toString()));
            }

            return usersIdResult;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        try {
            user.setPassword(user.getPassword());
            session.save(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}