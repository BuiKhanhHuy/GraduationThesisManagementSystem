package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.repository.CouncilDetailRepository;
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
public class CouncilDetailRepositoryImplement implements CouncilDetailRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<CouncilDetail> getCouncilsDetail(Map<String, String> params) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<CouncilDetail> query = builder.createQuery(CouncilDetail.class);
        Root<CouncilDetail> root = query.from(CouncilDetail.class);
        query.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (params.containsKey("councilId") && !params.get("councilId").isEmpty()) {
            int councilId = Integer.parseInt(params.get("councilId"));

            predicates.add(builder.equal(root.get("council"), councilId));
        }

        if (params.containsKey("lecturerId") && !params.get("lecturerId").isEmpty()) {
            int lecturerId = Integer.parseInt(params.get("lecturerId"));

            predicates.add(builder.equal(root.get("lecturer"), lecturerId));
        }

        query.where(predicates.toArray(new Predicate[]{}));

        return session.createQuery(query).getResultList();
    }

    @Override
    public List<Object[]> getCouncilsDetail(int lecturerId, Map<String, String> params) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<CouncilDetail> councilDetailRoot = query.from(CouncilDetail.class);
        Root<Council> councilRoot = query.from(Council.class);
        Root<SchoolYear> schoolYearRoot = query.from(SchoolYear.class);

        query.multiselect(councilDetailRoot.get("id"), councilDetailRoot.get("position"),
                councilRoot.get("name"), councilRoot.get("block") ,schoolYearRoot.get("name"));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(councilDetailRoot.get("lecturer"), lecturerId));
        predicates.add(builder.equal(councilDetailRoot.get("council"), councilRoot.get("id").as(Integer.class)));
        predicates.add(builder.equal(councilRoot.get("schoolYear"),
                schoolYearRoot.get("id").as(Integer.class)));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            predicates.add(builder.like(councilRoot.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        if (params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
            int schoolYearId = Integer.parseInt(params.get("schoolYearId"));

            predicates.add(builder.equal(councilRoot.get("schoolYear"), schoolYearId));
        }

        if (params.containsKey("block") && !params.get("block").isEmpty()) {
            boolean block = Boolean.parseBoolean(params.get("block"));

            predicates.add(builder.equal(councilRoot.get("block").as(Boolean.class), block));
        }

        query.where(predicates.toArray(new Predicate[]{}));
        query.orderBy(builder.desc(councilDetailRoot.get("id")));

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
    public long countCouncilDetail(int lecturerId, Map<String, String> params) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<CouncilDetail> councilDetailRoot = query.from(CouncilDetail.class);
        Root<Council> councilRoot = query.from(Council.class);
        Root<SchoolYear> schoolYearRoot = query.from(SchoolYear.class);

        query.multiselect(builder.count(councilDetailRoot.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(councilDetailRoot.get("lecturer"), lecturerId));
        predicates.add(builder.equal(councilDetailRoot.get("council"), councilRoot.get("id").as(Integer.class)));
        predicates.add(builder.equal(councilRoot.get("schoolYear"),
                schoolYearRoot.get("id").as(Integer.class)));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            predicates.add(builder.like(councilRoot.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        if (params.containsKey("schoolYearId") && !params.get("schoolYearId").isEmpty()) {
            int schoolYearId = Integer.parseInt(params.get("schoolYearId"));

            predicates.add(builder.equal(councilRoot.get("schoolYear"), schoolYearId));
        }

        if (params.containsKey("block") && !params.get("block").isEmpty()) {
            boolean block = Boolean.parseBoolean(params.get("block"));

            predicates.add(builder.equal(councilRoot.get("block").as(Boolean.class), block));
        }

        query.where(predicates.toArray(new Predicate[]{}));

        Query q = session.createQuery(query);
        Object result = q.getSingleResult();

        return (long) result;
    }

    @Override
    public CouncilDetail getCouncilDetailById(int councilDetailId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CouncilDetail> query = builder.createQuery(CouncilDetail.class);
            Root<CouncilDetail> root = query.from(CouncilDetail.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), councilDetailId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean checkAllowAddAndEdit(int councilDetailId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<CouncilDetail> root = query.from(CouncilDetail.class);
            Root<Council> councilRoot = query.from(Council.class);
            query.multiselect(councilRoot.get("block"));
            query.where(builder.equal(root.get("id").as(Integer.class), councilDetailId),
                    builder.equal(root.get("council"), councilRoot.get("id")));

            Query q = session.createQuery(query);
            Object result =  q.getSingleResult();

            return (boolean) result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }


}
