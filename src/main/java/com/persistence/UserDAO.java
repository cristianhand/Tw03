/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import com.entity.User;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author meme
 */
public class UserDAO {

    private Session session;
    private Transaction tx;

    //  Iniciar una sesión y una transacción en la base de datos
    private void iniciaOperacion() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public long saveUser(User user) {
        long id = 0;

        try {
            iniciaOperacion();
            id = (Long) session.save(user);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return id;
    }

    public void updateUser(User user) throws HibernateException {
        try {
            iniciaOperacion();
            session.update(user);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteUser(User user) {

        try {
            iniciaOperacion();
            session.delete(user);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public User searchUserId(int idUser) {
        User user = null;

        try {
            iniciaOperacion();
            user = (User) session.get(User.class, idUser);
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    public User searchUserbyUserName(String userName, String columName) {
        User user = null;

        try {
            iniciaOperacion();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq(columName, userName));
            List results = criteria.list();
            user = (User) results.get(0);
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    public List<User> getUserList() throws HibernateException {
        List<User> userList = null;

        try {
            iniciaOperacion();
            userList = session.createQuery("from User").list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userList;
    }
}
