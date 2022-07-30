package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            if (Integer.parseInt(params.get("roleId")) == 4
                    && params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
                // LEFT JOIN
                Root<Student> studentRoot = query.from(Student.class);
                predicates.add(builder.equal(root.get("id").as(Integer.class),
                        studentRoot.get("user")));
                predicates.add(builder.notEqual(studentRoot.get("schoolYear"),
                        Integer.parseInt(params.get("schoolYearId"))));
            }else {
                predicates.add(builder.notEqual(root.get("role"), Integer.parseInt(params.get("roleId"))));
            }
        }

        query.where(predicates.toArray(new Predicate[]{}));

        return session.createQuery(query).getResultList();
    }
}
