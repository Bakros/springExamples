package com.example.demo.Chapter7_Hibernate.DAO;

import com.example.demo.Chapter7_Hibernate.Entities.Singer;

import org.hibernate.SessionFactory;
import org.hibernate.boot.query.NamedQueryDefinition;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("singerDao")
public class SingerDaoImpl implements SingerDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Singer s").list();
    }

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findAllWithAlbum() {
        /*
        The following method - getNamedQuery is deprecated.
         */
       //return sessionFactory.getCurrentSession().getNamedQuery("Singer.findAllWithAlbum").list();

       // The following method to use NamedQuery is the correct way on Hibernate 6.4.4. - This answer was given by chatGPT.
        return sessionFactory.getCurrentSession().createNamedQuery("Singer.findAllWithAlbum", Singer.class).getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public Singer findById(Long id) {
        return (Singer) sessionFactory.getCurrentSession()
                .getNamedQuery("Singer.findById")
                .setParameter("id", id)
                .uniqueResult();
    }

    @Override
    public Singer save(Singer singer) {
        return null;
    }

    @Override
    public void delete(Singer singer) {

    }
// other methods omitted
}