/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import com.entity.Ticket;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author meme
 */
public class TicketDAO {

    private Session sesion;
    private Transaction tx;

    //  Iniciar una sesión y una transacción en la base de datos
    private void iniciaOperacion() throws HibernateException {
        sesion = HibernateUtil.getSessionFactory().openSession();
        tx = sesion.beginTransaction();
    }

    private void manejaExcepcion(HibernateException he) throws HibernateException {
        tx.rollback();
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he);
    }

    public long saveTicket(Ticket ticket) {
        long id = 0;

        try {
            iniciaOperacion();
            id = (Long) sesion.save(ticket);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
        return id;
    }

    public void updateTicket(Ticket ticket) throws HibernateException {
        try {
            iniciaOperacion();
            sesion.update(ticket);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
            throw he;
        } finally {
            sesion.close();
        }
    }

    public void deleteTicket(Ticket ticket) {

        try {
            iniciaOperacion();
            sesion.delete(ticket);
            tx.commit();
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
    }

    public Ticket searchTicketId(int idTicket) {
        Ticket ticket = null;

        try {
            iniciaOperacion();
            ticket = (Ticket) sesion.get(Ticket.class, idTicket);
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
        return ticket;
    }

        public Ticket searchTicketUserName(String userName) {
        Ticket ticket = null;

        try {
            iniciaOperacion();
            ticket = (Ticket) sesion.get(Ticket.class, userName);
        } catch (HibernateException he) {
            manejaExcepcion(he);
        } finally {
            sesion.close();
        }
        return ticket;
    }
    
    public List<Ticket> getTicketList() throws HibernateException {
        List<Ticket> ticketList = null;

        try {
            iniciaOperacion();
            ticketList = sesion.createQuery("from Ticket").list();
        } finally {
            sesion.close();
        }
        return ticketList;
    }
}
