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

            if(schoolYearId != null){
                query.where(builder.equal(thesisRoot.get("schoolYear"), schoolYearId));
            }

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return new ArrayList<>();
    }
}
