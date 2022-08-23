package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.*;
import com.buikhanhhuy.repository.ThesisRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import utils.Utils;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.*;

@Repository
@Transactional
public class ThesisRepositoryImplement implements ThesisRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public boolean checkStudentDoManyThesisInASchoolYear(int studentId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(t.id) FROM Student s INNER JOIN s.theses t WHERE s.id=:studentId AND t.result = 1";
        Query query = session.createQuery(sql);
        query.setParameter("studentId", studentId);

        return (long) query.getSingleResult() > 0;
    }

    @Override
    public boolean checkStudentCompletedThesis(int studentId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();

        String sql = "SELECT COUNT(t.id) FROM Student s INNER JOIN s.theses t WHERE s.id=:studentId AND t.result = 3";
        Query query = session.createQuery(sql);
        query.setParameter("studentId", studentId);

        return (long) query.getSingleResult() > 0;
    }

    @Override
    public List<Object[]> getThesisOptions(String isCouncil) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Thesis> root = query.from(Thesis.class);
        Root<Topic> topicRoot = query.from(Topic.class);
        query.multiselect(root.get("id"), root.get("code"), topicRoot.get("name"));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(root.get("topic"), topicRoot.get("id")));

        if (isCouncil != null && !isCouncil.isEmpty() && !Boolean.parseBoolean(isCouncil)) {
            predicates.add(builder.isNull(root.get("council")));
        }

        query.where(predicates.toArray(new Predicate[]{}));

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

        if (params.containsKey("majorId") && !params.get("majorId").isEmpty()) {
            predicates.add(builder.equal(root.get("major"), Integer.parseInt(params.get("majorId"))));
        }

        if (params.containsKey("result") && !params.get("result").isEmpty()) {
            int result = Integer.parseInt(params.get("result"));

            predicates.add(builder.equal(root.get("result").as(Integer.class), result));
        }

        query.where(predicates.toArray(new Predicate[]{}));
        query.orderBy(builder.desc(root.get("id")));

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

        if (params.containsKey("majorId") && !params.get("majorId").isEmpty()) {
            predicates.add(builder.equal(root.get("major"), Integer.parseInt(params.get("majorId"))));
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
    public long countAllThesis() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            String sql = "SELECT COUNT(id) FROM Thesis";

            return (long) session.createQuery(sql).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    @Override
    public Boolean addThesis(Thesis thesis) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
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

    @Override
    public Double scoreOfAThesisInCouncil(int councilId, int thesisId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();

        try {
            String stringQuery = "SELECT SUM(scoD.scoreNum * scoC.weight) " +
                    "FROM CouncilDetail cd LEFT JOIN cd.scores sco " +
                    "LEFT JOIN sco.scoreDetails scoD " +
                    "LEFT JOIN scoD.scoreColumn scoC " +
                    "WHERE sco.thesis.id =: thesisID AND cd.council.id =: councilId " +
                    "GROUP BY sco.councilDetail.id";

            Query query = session.createQuery(stringQuery);
            query.setParameter("thesisID", thesisId);
            query.setParameter("councilId", councilId);

            List resultList = query.getResultList();

            double totalScore = 0.0;
            for (Object score : query.getResultList()) {
                if (score != null) {
                    totalScore += (Double) score;
                }
            }

            if (resultList.size() > 0) return totalScore / resultList.size();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0.0;
    }

    @Override
    public Council thesisResult(int councilId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Council council = session.get(Council.class, councilId);
            for (Thesis thesis : council.getTheses()) {
                double scoreResult = this.scoreOfAThesisInCouncil(councilId, thesis.getId());

                thesis.setTotalScore(scoreResult);
                thesis.setResult(Utils.checkThesisResult(scoreResult));

                session.update(thesis);
            }

            return council;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean uploadThesisReportFile(int thesisId, String urlReportFile) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Thesis objThesis = session.get(Thesis.class, thesisId);
            if (urlReportFile != null && !urlReportFile.isEmpty()) {
                objThesis.setReportFile(urlReportFile);
                session.update(objThesis);

                return true;
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
