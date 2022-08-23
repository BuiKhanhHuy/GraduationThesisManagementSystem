package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Major;
import com.buikhanhhuy.pojo.Major_;
import com.buikhanhhuy.pojo.SchoolYear;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.StatsRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class StatsRepositoryImplement implements StatsRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Object[]> thesisStatisticsByMajor(Integer schoolYearId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<Major> majorRoot = query.from(Major.class);
            Join<Major, Thesis> thesisRoot = majorRoot.join(Major_.theses, JoinType.LEFT);

            query.multiselect(majorRoot.get("id"), majorRoot.get("name"), builder.count(thesisRoot.get("id")));
            query.groupBy(majorRoot.get("id"));

            if (schoolYearId != null) {
                query.where(builder.equal(thesisRoot.get("schoolYear"), schoolYearId));
            }

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public List<Object[]> thesisScoreStatistics(Integer schoolYearId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();

        try {
            String sql1 = "SELECT sy.name, m.name, AVG(t.totalScore) FROM SchoolYear sy LEFT JOIN sy.theses t LEFT JOIN t.major m GROUP BY sy.id, m.id";
            String sql2 = "SELECT sy.name, m.name, AVG(t.totalScore) FROM SchoolYear sy LEFT JOIN sy.theses t LEFT JOIN t.major m WHERE t.schoolYear.id=:schoolYearId GROUP BY sy.id, m.id";
            Query query;

            if (schoolYearId == null) {
                query = session.createQuery(sql1);
            } else {
                query = session.createQuery(sql2);
                query.setParameter("schoolYearId", schoolYearId);
            }

            return query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }
}
