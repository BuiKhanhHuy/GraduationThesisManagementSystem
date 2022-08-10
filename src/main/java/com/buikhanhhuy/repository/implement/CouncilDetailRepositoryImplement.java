package com.buikhanhhuy.repository.implement;

import com.buikhanhhuy.pojo.CouncilDetail;
import com.buikhanhhuy.repository.CouncilDetailRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

@Repository
public class CouncilDetailRepositoryImplement implements CouncilDetailRepository {
    @Autowired
    private LocalSessionFactoryBean sessionFactoryBean;

}
