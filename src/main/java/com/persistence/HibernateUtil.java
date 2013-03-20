/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author meme
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ExceptionInInitializerError(e);
        }

        /* catch (HibernateException he) {
         System.err.println("Ocurrió un error en la inicialización de la SessionFactory: " + he);
         //he.getMessage();
         throw new ExceptionInInitializerError(he);
         */
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
