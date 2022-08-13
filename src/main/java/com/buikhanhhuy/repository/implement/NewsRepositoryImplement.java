package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.constants.SystemConstant;
import com.buikhanhhuy.pojo.News;
import com.buikhanhhuy.pojo.User;
import com.buikhanhhuy.repository.NewsRepository;
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

@Repository
@Transactional
public class NewsRepositoryImplement implements NewsRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public List<News> getNews(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<News> query = builder.createQuery(News.class);
        Root<News> root = query.from(News.class);
        query.select(root);

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            query.where(builder.like(root.get("title").as(String.class), String.format("%%%s%%", kw)));
        }
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
    public long countNews(Map<String, String> params) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root<News> root = query.from(News.class);
        query.multiselect(builder.count(root.get("id")));

        if (params.containsKey("kw") && !params.get("kw").isEmpty()) {
            String kw = params.get("kw");
            query.where(builder.like(root.get("title").as(String.class), String.format("%%%s%%", kw)));
        }

        Query q = session.createQuery(query);
        Object result = q.getSingleResult();

        return (long) result;
    }

    @Override
    public News getNewsWithAuthorById(int newsId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<News> query = builder.createQuery(News.class);
            Root<News> root = query.from(News.class);
            query.select(root);
            query.where(builder.equal(root.get("id").as(Integer.class), newsId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public Object[] getNewsById(int newsId) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<News> root = query.from(News.class);

            query.multiselect(root.get("id"), root.get("title"), root.get("content"));
            query.where(builder.equal(root.get("id").as(String.class), newsId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean addNews(News news) {
        Session session = this.sessionFactoryBean.getObject().getCurrentSession();
        try {
            news.setUser(session.get(User.class, 1));
            session.save(news);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateNews(int newsId, News news) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            News objNews = session.get(News.class, newsId);
            objNews.setTitle(news.getTitle());
            objNews.setContent(news.getContent());

            session.update(objNews);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteNews(int newsId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            News objNews = session.get(News.class, newsId);
            session.delete(objNews);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
