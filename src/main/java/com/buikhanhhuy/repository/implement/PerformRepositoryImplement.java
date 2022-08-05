package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.Perform;
import com.buikhanhhuy.repository.PerformRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class PerformRepositoryImplement implements PerformRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

    @Override
    public boolean addPerform(int studentId, int thesisId) {
        Session session = sessionFactoryBean.getObject().getCurrentSession();
        try {
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
