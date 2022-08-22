package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Student;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.repository.RoleRepository;
import com.buikhanhhuy.repository.StudentRepository;
import com.buikhanhhuy.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class StudentRepositoryImplement implements StudentRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean checkUniqueStudentCode(String studentCode) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Student WHERE code=:code";
        Query query = session.createQuery(sql);
        query.setParameter("code", studentCode.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public boolean checkUniqueStudentEmail(String studentEmail) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Student WHERE email=:email";
        Query query = session.createQuery(sql);
        query.setParameter("email", studentEmail.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public boolean checkUniqueStudentPhone(String studentPhone) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(id) FROM Student WHERE phone=:phone";
        Query query = session.createQuery(sql);
        query.setParameter("phone", studentPhone.trim());

        return (long)query.getSingleResult() > 0;
    }

    @Override
    public List<Object[]> getStudentOptions() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Student> root = query.from(Student.class);
        query.multiselect(root.get("id"), root.get("fullName"));

        return session.createQuery(query).getResultList();
    }

    @Override
    public List<Student> getStudents(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);
        Root<User> userRoot = query.from(User.class);

        query.select(studentRoot);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(studentRoot.get("user"), userRoot.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            Predicate predicate1 = builder.like(studentRoot.get("code").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate2 = builder.like(studentRoot.get("fullName").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate3 = builder.like(studentRoot.get("email").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate4 = builder.like(studentRoot.get("phone").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate5 = builder.like(userRoot.get("username").as(String.class), String.format("%%%s%%", kw));

            predicates.add(builder.or(predicate1, predicate2, predicate3, predicate4));
        }

        if (params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
            predicates.add(builder.equal(studentRoot.get("schoolYear"), Integer.parseInt(params.get("schoolYearId"))));
        }

        if (params.containsKey("majorId") && !params.get("majorId").isEmpty()) {
            predicates.add(builder.equal(studentRoot.get("major"), Integer.parseInt(params.get("majorId"))));
        }

        if (params.containsKey("active") && !params.get("active").isEmpty()) {
            boolean active = Boolean.parseBoolean(params.get("active"));

            predicates.add(builder.equal(userRoot.get("active").as(Boolean.class), active));
        }

        query.where(predicates.toArray(new Predicate[]{}));
        query.orderBy(builder.desc(studentRoot.get("id")));

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
    public long countStudent(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Student> studentRoot = query.from(Student.class);
        Root<User> userRoot = query.from(User.class);

        query.multiselect(builder.count(studentRoot.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(studentRoot.get("user"), userRoot.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            Predicate predicate1 = builder.like(studentRoot.get("code").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate2 = builder.like(studentRoot.get("fullName").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate3 = builder.like(studentRoot.get("email").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate4 = builder.like(studentRoot.get("phone").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate5 = builder.like(userRoot.get("username").as(String.class), String.format("%%%s%%", kw));

            predicates.add(builder.or(predicate1, predicate2, predicate3, predicate4));
        }

        if (params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
            predicates.add(builder.equal(studentRoot.get("schoolYear"), Integer.parseInt(params.get("schoolYearId"))));
        }

        if (params.containsKey("majorId") && !params.get("majorId").isEmpty()) {
            predicates.add(builder.equal(studentRoot.get("major"), Integer.parseInt(params.get("majorId"))));
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
    public long countAllStudent() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
           String sql = "SELECT COUNT(id) FROM Student";

            return (long) session.createQuery(sql).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    @Override
    public Student getStudentById(int studentId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> query = builder.createQuery(Student.class);
            Root<Student> root = query.from(Student.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), studentId));
            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addStudent(Student student) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            User user = student.getUser();
            if (this.userRepository.addUser(user)) {
                user.setRole(this.roleRepository.getRoleByRoleName("STUDENT"));
                session.update(user);
                session.save(student);
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateStudent(int studentId, Student student) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Student objStudent = session.get(Student.class, studentId);
            objStudent.setCode(student.getCode());
            objStudent.setFullName(student.getFullName());
            objStudent.setEmail(student.getEmail());
            objStudent.setPhone(student.getPhone());
            objStudent.setBirthday(student.getBirthday());
            objStudent.setGender(student.getGender());
            objStudent.setAddress(student.getAddress());
            objStudent.setGpa(student.getGpa());
            objStudent.setMajor(student.getMajor());
            objStudent.setSchoolYear(student.getSchoolYear());

            User objUser = session.get(User.class, objStudent.getUser().getId());
            objUser.setUsername(student.getUser().getUsername());
            objUser.setPassword(student.getUser().getPassword());
            if (student.getUser().getAvatar() != null)
                objUser.setAvatar(student.getUser().getAvatar());
            objUser.setActive(student.getUser().getActive());

            session.update(objUser);
            session.update(objStudent);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int studentId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Student objStudent = session.get(Student.class, studentId);
            User objUser = session.get(User.class, objStudent.getUser().getId());

            session.delete(objStudent);
            session.delete(objUser);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
