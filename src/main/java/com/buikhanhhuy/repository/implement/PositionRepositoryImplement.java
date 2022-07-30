package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.repository.PositionRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
public class PositionRepositoryImplement implements PositionRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Position> getPositions() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Position> query = builder.createQuery(Position.class);
        Root<Position> root = query.from(Position.class);
        query.select(root);

        return session.createQuery(query).getResultList();
    }

    @Override
    public boolean addPosition(Position position) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            session.save(position);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public Position getPositionById(int positionId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Position> query = builder.createQuery(Position.class);
            Root<Position> root = query.from(Position.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), positionId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updatePosition(int positionId, Position position) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Position objPosition = session.get(Position.class, positionId);
            objPosition.setName(position.getName());
            objPosition.setDescription(position.getDescription());

            session.update(objPosition);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deletePosition(int positionId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            Position position = session.get(Position.class, positionId);
            session.delete(position);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
