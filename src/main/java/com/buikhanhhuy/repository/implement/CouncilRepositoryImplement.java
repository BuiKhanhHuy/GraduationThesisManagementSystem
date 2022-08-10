package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Council;
import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.pojo.Thesis;
import com.buikhanhhuy.repository.CouncilDetailRepository;
import com.buikhanhhuy.repository.CouncilRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@Transactional
public class CouncilRepositoryImplement implements CouncilRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;
    @Autowired
    private CouncilDetailRepository councilDetailRepository;

    @Override
    public List<Council> getCouncils(Map<String, String> params) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Council> query = builder.createQuery(Council.class);
        Root<Council> root = query.from(Council.class);
        query.select(root);

        Query q = session.createQuery(query);

        return q.getResultList();
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
            for(Thesis thesis: council.getTheses()){
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
}
