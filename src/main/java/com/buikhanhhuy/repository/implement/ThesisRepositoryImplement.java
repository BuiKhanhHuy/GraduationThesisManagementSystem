package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.*;
import com.buikhanhhuy.repository.ThesisRepository;
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
import java.util.*;

@Repository
@Transactional
public class ThesisRepositoryImplement implements ThesisRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Object[]> getThesisOptions() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Thesis> root = query.from(Thesis.class);
        Root<Topic> topicRoot = query.from(Topic.class);
        query.multiselect(root.get("id"), root.get("code"), topicRoot.get("name"));

        query.where(builder.equal(root.get("topic"), topicRoot.get("id")));

        return session.createQuery(query).getResultList();
    }

    @Override
    public List<Thesis> getTheses(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Thesis> query = builder.createQuery(Thesis.class);
        Root<Thesis> root = query.from(Thesis.class);
        Root<Topic> topicRoot = query.from(Topic.class);

        query.select(root);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("topic"), topicRoot.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            Predicate predicate1 = builder.like(root.get("code").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate2 = builder.like(topicRoot.get("name").as(String.class), String.format("%%%s%%", kw));

            predicates.add(builder.or(predicate1, predicate2));
        }

        if (params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
            predicates.add(builder.equal(root.get("schoolYear"), Integer.parseInt(params.get("schoolYearId"))));
        }

        if (params.containsKey("departmentId") && !params.get("departmentId").isEmpty()) {
            predicates.add(builder.equal(root.get("department"), Integer.parseInt(params.get("departmentId"))));
        }

        if (params.containsKey("result") && !params.get("result").isEmpty()) {
            int result = Integer.parseInt(params.get("result"));

            predicates.add(builder.equal(root.get("result").as(Integer.class), result));
        }

        query.where(predicates.toArray(new Predicate[]{}));

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
    public long countThesis(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Thesis> root = query.from(Thesis.class);
        Root<Topic> topicRoot = query.from(Topic.class);

        query.multiselect(builder.count(root.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("topic"), topicRoot.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            Predicate predicate1 = builder.like(root.get("code").as(String.class), String.format("%%%s%%", kw));
            Predicate predicate2 = builder.like(topicRoot.get("name").as(String.class), String.format("%%%s%%", kw));

            predicates.add(builder.or(predicate1, predicate2));
        }

        if (params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
            predicates.add(builder.equal(root.get("schoolYear"), Integer.parseInt(params.get("schoolYearId"))));
        }

        if (params.containsKey("departmentId") && !params.get("departmentId").isEmpty()) {
            predicates.add(builder.equal(root.get("department"), Integer.parseInt(params.get("departmentId"))));
        }

        if (params.containsKey("result") && !params.get("result").isEmpty()) {
            int result = Integer.parseInt(params.get("result"));

            predicates.add(builder.equal(root.get("result").as(Integer.class), result));
        }

        query.where(predicates.toArray(new Predicate[]{}));

        Query q = session.createQuery(query);
        Object result = q.getSingleResult();

        return (long) result;
    }

    @Override
    public boolean addThesis(Thesis thesis) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            Set<Student> students = new HashSet<>();
            Set<Lecturer> lecturers = new HashSet<>();
            for (int performStudentId : thesis.getPerformStudentsId()) {
                students.add(session.get(Student.class, performStudentId));
            }
            for (int instructorId : thesis.getInstructorsId()) {
                lecturers.add(session.get(Lecturer.class, instructorId));
            }

            thesis.setStudents(students);
            thesis.setLecturers(lecturers);
            session.save(thesis);

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public Thesis getThesisById(int thesisId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Thesis> query = builder.createQuery(Thesis.class);
            Root<Thesis> root = query.from(Thesis.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), thesisId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteThesis(int thesisId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Thesis objThesis = session.get(Thesis.class, thesisId);
            session.delete(objThesis);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
