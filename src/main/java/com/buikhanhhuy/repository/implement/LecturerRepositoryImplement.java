package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Lecturer;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.repository.LecturerRepository;
import com.buikhanhhuy.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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

@Repository
@Transactional
public class LecturerRepositoryImplement implements LecturerRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean checkUniqueLecturerCode(String lecturerCode) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Lecturer WHERE code=:code";
        Query query = session.createQuery(sql);
        query.setParameter("code", lecturerCode.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public boolean checkUniqueLecturerEmail(String lecturerEmail) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Lecturer WHERE email=:email";
        Query query = session.createQuery(sql);
        query.setParameter("email", lecturerEmail.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public boolean checkUniqueLecturerPhone(String lecturerPhone) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Lecturer WHERE phone=:phone";
        Query query = session.createQuery(sql);
        query.setParameter("phone", lecturerPhone.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public List<Object[]> getLecturerOptions() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Lecturer> root = query.from(Lecturer.class);
        query.multiselect(root.get("id"), root.get("fullName"));

        return session.createQuery(query).getResultList();
    }

    @Override
    public List<Lecturer> getLecturers(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Lecturer> query = builder.createQuery(Lecturer.class);
        Root<Lecturer> lecturerRoot = query.from(Lecturer.class);
        Root<User> userRoot = query.from(User.class);

        query.select(lecturerRoot);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(lecturerRoot.get("user"), userRoot.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            Predicate predicate1 = builder.like(lecturerRoot.get("code").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate2 = builder.like(lecturerRoot.get("fullName").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate3 = builder.like(lecturerRoot.get("email").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate4 = builder.like(lecturerRoot.get("phone").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate5 = builder.like(userRoot.get("username").as(String.class), String.format("%%%s%%", kw));

            predicates.add(builder.or(predicate1, predicate2, predicate3, predicate4));
        }

        if (params.containsKey("positionId") && !params.get("positionId").isEmpty()) {
            predicates.add(builder.equal(lecturerRoot.get("position"), Integer.parseInt(params.get("positionId"))));
        }

        if (params.containsKey("departmentId") && !params.get("departmentId").isEmpty()) {
            predicates.add(builder.equal(lecturerRoot.get("department"), Integer.parseInt(params.get("departmentId"))));
        }

        if (params.containsKey("roleId") && !params.get("roleId").isEmpty()) {
            predicates.add(builder.equal(userRoot.get("role"), Integer.parseInt(params.get("roleId"))));
        }

        if (params.containsKey("active") && !params.get("active").isEmpty()) {
            boolean active = Boolean.parseBoolean(params.get("active"));

            predicates.add(builder.equal(userRoot.get("active").as(Boolean.class), active));
        }

        query.where(predicates.toArray(new Predicate[]{}));
        query.orderBy(builder.desc(lecturerRoot.get("id")));

        Query q = session.createQuery(query);

        int page = 1;
        int pageSize = SystemConstant.PAGE_SIZE;
        if (params.containsKey("page") && !params.get("page").isEmpty()) page = Integer.parseInt(params.get("page"));

        int startPage = (page - 1) * pageSize;
        q.setMaxResults(pageSize);
        q.setFirstResult(startPage);

        return q.getResultList();
    }

    @Override
    public long countLecturer(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Lecturer> lecturerRoot = query.from(Lecturer.class);
        Root<User> userRoot = query.from(User.class);

        query.multiselect(builder.count(lecturerRoot.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(lecturerRoot.get("user"), userRoot.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            Predicate predicate1 = builder.like(lecturerRoot.get("code").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate2 = builder.like(lecturerRoot.get("fullName").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate3 = builder.like(lecturerRoot.get("email").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate4 = builder.like(lecturerRoot.get("phone").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate5 = builder.like(userRoot.get("username").as(String.class), String.format("%%%s%%", kw));

            predicates.add(builder.or(predicate1, predicate2, predicate3, predicate4));
        }

        if (params.containsKey("positionId") && !params.get("positionId").isEmpty()) {
            predicates.add(builder.equal(lecturerRoot.get("position"), Integer.parseInt(params.get("positionId"))));
        }

        if (params.containsKey("departmentId") && !params.get("departmentId").isEmpty()) {
            predicates.add(builder.equal(lecturerRoot.get("department"), Integer.parseInt(params.get("departmentId"))));
        }

        if (params.containsKey("roleId") && !params.get("roleId").isEmpty()) {
            predicates.add(builder.equal(userRoot.get("role"), Integer.parseInt(params.get("roleId"))));
        }

        if (params.containsKey("active") && !params.get("active").isEmpty()) {
            boolean active = Boolean.parseBoolean(params.get("active"));

            predicates.add(builder.equal(userRoot.get("active").as(Boolean.class), active));
        }

        query.where(predicates.toArray(new Predicate[]{}));

        Query q = session.createQuery(query);
        Object result = q.getSingleResult();

        return (long) result;
    }

    @Override
    public long countAllLecturer() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            String sql = "SELECT COUNT(id) FROM Lecturer";

            return (long) session.createQuery(sql).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return 0;
    }

    @Override
    public Lecturer getLecturerById(int lecturerId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Lecturer> query = builder.createQuery(Lecturer.class);
            Root<Lecturer> root = query.from(Lecturer.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), lecturerId));
            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addLecturer(Lecturer lecturer) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            if (this.userRepository.addUser(lecturer.getUser())) session.save(lecturer);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateLecturer(int lecturerId, Lecturer lecturer) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Lecturer objLecturer = session.get(Lecturer.class, lecturerId);
            objLecturer.setCode(lecturer.getCode());
            objLecturer.setFullName(lecturer.getFullName());
            objLecturer.setEmail(lecturer.getEmail());
            objLecturer.setPhone(lecturer.getPhone());
            objLecturer.setBirthday(lecturer.getBirthday());
            objLecturer.setGender(lecturer.getGender());
            objLecturer.setAddress(lecturer.getAddress());
            objLecturer.setPosition(lecturer.getPosition());
            objLecturer.setDepartment(lecturer.getDepartment());

            User objUser = session.get(User.class, objLecturer.getUser().getId());
            objUser.setUsername(lecturer.getUser().getUsername());
            if (lecturer.getUser().getAvatar() != null)
                objUser.setAvatar(lecturer.getUser().getAvatar());
            objUser.setActive(lecturer.getUser().getActive());
            objUser.setRole(lecturer.getUser().getRole());

            session.update(objUser);
            session.update(objLecturer);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteLecturer(int lecturerId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Lecturer objLecturer = session.get(Lecturer.class, lecturerId);
            User objUser = session.get(User.class, objLecturer.getUser().getId());

            session.delete(objLecturer);
            session.delete(objUser);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
