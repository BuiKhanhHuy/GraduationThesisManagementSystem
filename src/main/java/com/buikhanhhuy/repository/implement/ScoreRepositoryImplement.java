package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Score;
import com.buikhanhhuy.pojo.ScoreDetail;
import com.buikhanhhuy.repository.ScoreRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Objects;


@Repository
@Transactional
public class ScoreRepositoryImplement implements ScoreRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public Score getScoreWithThesisIdAndCouncilDetailId(int thesisId, int councilDetailId) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Score> query = builder.createQuery(Score.class);
            Root<Score> root = query.from(Score.class);
            query.select(root);
            query.where(builder.equal(root.get("thesis"), thesisId),
                    builder.equal(root.get("councilDetail"), councilDetailId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public long checkTheGradedThesisByCouncilDetailId(int thesisId, int councilDetailId) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<Score> root = query.from(Score.class);
            query.multiselect(builder.count(root.get("id")));
            query.where(builder.equal(root.get("thesis"), thesisId),
                    builder.equal(root.get("councilDetail"), councilDetailId));

            Query q = session.createQuery(query);
            Object result = q.getSingleResult();

            return (long) result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean addScore(Score score) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        try {
            session.save(score);

            for (ScoreDetail scoreDetail : score.getScoreDetails()) {
                scoreDetail.setScore(score);

                session.save(scoreDetail);
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateScore(int scoreId, Score score) {
        Session session = Objects.requireNonNull(this.sessionFactoryBean.getObject()).getCurrentSession();
        try {
            for (ScoreDetail scoreDetail : score.getScoreDetails()) {
                ScoreDetail objScoreDetail = session.get(ScoreDetail.class, scoreDetail.getId());
                objScoreDetail.setScoreNum(scoreDetail.getScoreNum());

                session.update(objScoreDetail);
            }

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }
}
