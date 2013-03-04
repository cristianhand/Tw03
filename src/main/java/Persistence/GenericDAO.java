/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author meme
 */
public class GenericDAO {

    private Session session;
    private Transaction tx;

    //  Iniciar una sesión y una transacción en la base de datos
    private void beginOperation() throws HibernateException {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void manageExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public Object getObjectById(int idObject) {
        Object object = null;

        try {
            beginOperation();
            object = session.get(Object.class, idObject);
        } catch (HibernateException he) {
            manageExcepcion(he);
        } finally {
            session.close();
        }
        return object;
    }

    public Object getObjectByString(String stringObject) {
        Object object = null;

        try {
            beginOperation();
            object = session.get(Object.class, stringObject);
        } catch (HibernateException he) {
            manageExcepcion(he);
        } finally {
            session.close();
        }

        return object;
    }

    public List<Object> getObjectList(String table) {
        List<Object> objectList = null;

        try {
            beginOperation();
            objectList = (List<Object>)session.createQuery("from " + table).list();
        } catch (HibernateException he) {
            manageExcepcion(he);
        } finally {
            session.close();
        }
        return objectList;
    }
}
