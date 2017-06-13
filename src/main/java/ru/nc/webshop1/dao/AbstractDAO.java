package ru.nc.webshop1.dao;

import ru.nc.webshop1.util.HibernateUtil;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.nc.webshop1.entity.Entity;

public abstract class AbstractDAO<T extends Entity> {
    
    public abstract String getSelectQuery();

    public T create(T entity) throws SQLException {
        T newEntity;
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Ошибка запроса на создание записи!\n" + e.getMessage());
        } finally {
            session.close();
        }
        
        try {
            newEntity = getByID((int) entity.getId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Ошибка поиска добавленной записи!\n" + e.getMessage());
        }
        
        return newEntity;
    }

    public T getByID(int id) throws SQLException {
        T newEntity = null;
        String queryString = getSelectQuery() + " where id = :id";
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            transaction = session.beginTransaction();
            Query query = session.createQuery(queryString);
            query.setParameter("id", id);
            newEntity = (T) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Ошибка поиска записи!\n" + e.getMessage());
        } finally {
            session.close();
        }
        
        if (newEntity == null) {
            throw new SQLException("Сущность не найдена!");
        }
        
        return newEntity;
    }

    public void update(T entity) throws SQLException {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Ошибка в обработке запроса на обновление данных!\n" + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void delete(T entity) throws SQLException {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Ошибка в обработке запроса на удаление данных!\n" + e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<T> getAll() throws SQLException {
        List<T> list;
        String query = getSelectQuery();
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            transaction = session.beginTransaction();
            list = session.createQuery(query).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new SQLException("Ошибка в обработке запроса на выборку!\n" + e.getMessage());
        } finally {
            session.close();
        }
        return list;
    }
}
