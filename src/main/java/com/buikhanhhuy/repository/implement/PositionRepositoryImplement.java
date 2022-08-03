package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Position;
import com.buikhanhhuy.repository.PositionRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
public class PositionRepositoryImplement implements PositionRepository {
    @Autowired
    private Environment environment;
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<Position> getPositions(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Position> query = builder.createQuery(Position.class);
        Root<Position> root = query.from(Position.class);
        query.select(root);

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            query.where(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        int page = 1;
        int pageSize = Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("pageSize")));

        if (params.containsKey("page") && !params.get("page").isEmpty()) page = Integer.parseInt(params.get("page"));

        Query q = session.createQuery(query);

        int startPage = (page - 1) * pageSize;
        q.setFirstResult(startPage);
        q.setMaxResults(pageSize);

        return q.getResultList();
    }

    @Override
    public List<Object[]> getPositionOptions() {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Position> root = query.from(Position.class);
        query.multiselect(root.get("id"), root.get("name"));

        return session.createQuery(query).getResultList();
    }

    @Override
    public long countPosition(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<Position> root = query.from(Position.class);
        query.multiselect(builder.count(root.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");

            query.where(builder.like(root.get("name").as(String.class), String.format("%%%s%%", kw)));
        }

        Query q = session.createQuery(query);
        Object result =  q.getSingleResult();

        return (long) result;
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
