package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.CouncilRepository;
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
import java.util.Objects;

@Repository
@Transactional
public class CouncilRepositoryImplement implements CouncilRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Council> getCouncils(Map<String, String> params) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Council> query = builder.createQuery(Council.class);
        Root<Council> root = query.from(Council.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            predicates.add(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        if (params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
            int schoolYearId = Integer.parseInt(params.get("schoolYearId"));

            predicates.add(builder.equal(root.get("schoolYear"), schoolYearId));
        }

        if (params.containsKey("block") && !params.get("block").isEmpty()) {
            boolean block = Boolean.parseBoolean(params.get("block"));

            predicates.add(builder.equal(root.get("block").as(Boolean.class), block));
        }

        query.where(predicates.toArray(new Predicate[]{}));
        query.orderBy(builder.desc(root.get("id")));

        int page = 1;
        int pageSize = SystemConstant.PAGE_SIZE;

        if (params.containsKey("page") && !params.get("page").isEmpty()) page = Integer.parseInt(params.get("page"));

        Query q = session.createQuery(query);

        int startPage = (page - 1) * pageSize;
        q.setFirstResult(startPage);
        q.setMaxResults(pageSize);

        return q.getResultList();
    }

    @Override
    public Council getCouncilById(int councilId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Council> query = builder.createQuery(Council.class);
            Root<Council> root = query.from(Council.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), councilId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public long countCouncil(Map<String, String> params) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Council> root = query.from(Council.class);
        query.multiselect(builder.count(root.get("id")));

        List<Predicate> predicates = new ArrayList<>();

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            predicates.add(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        if (params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
            int schoolYearId = Integer.parseInt(params.get("schoolYearId"));

            predicates.add(builder.equal(root.get("schoolYear"), schoolYearId));
        }

        if (params.containsKey("block") && !params.get("block").isEmpty()) {
            boolean block = Boolean.parseBoolean(params.get("block"));

            predicates.add(builder.equal(root.get("block").as(Boolean.class), block));
        }

        query.where(predicates.toArray(new Predicate[]{}));

        Query q = session.createQuery(query);
        Object result = q.getSingleResult();

        return (long) result;
    }

    @Override
    public long countAllCouncil() {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
           String sql = "SELECT COUNT(id) FROM Council";

           return (long) session.createQuery(sql).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean addCouncil(Council council) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        try {
            session.save(council);

            for (CouncilDetail councilDetail : council.getCouncilDetails()) {
                councilDetail.setCouncil(council);

                session.save(councilDetail);
            }
            for (Thesis thesis : council.getTheses()) {
                Thesis th = session.get(Thesis.class, thesis.getId());
                th.setCouncil(council);

                session.update(th);
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteCouncil(int councilId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Council council = session.get(Council.class, councilId);
            session.delete(council);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Object[]> scoreDetailOfCouncilForThesis(int councilId, int thesisId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();

        try {
            String stringQuery = "SELECT l.user.id, l.id, l.fullName, SUM(scoD.scoreNum * scoC.weight) " + "FROM CouncilDetail cd LEFT JOIN cd.scores sco " + "LEFT JOIN sco.scoreDetails scoD " + "LEFT JOIN scoD.scoreColumn scoC, Lecturer l " + "WHERE sco.thesis.id =: thesisID AND cd.council.id =: councilId AND cd.lecturer = l " + "GROUP BY sco.councilDetail.id";

            Query query = session.createQuery(stringQuery);
            query.setParameter("thesisID", thesisId);
            query.setParameter("councilId", councilId);

            List<Object[]> resultList = query.getResultList();

            return resultList;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public Council lockOrUnlockCouncil(int councilId, boolean block) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Council council = session.get(Council.class, councilId);
            council.setBlock(block);

            session.update(council);
            return council;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
