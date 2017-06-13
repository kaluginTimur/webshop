package ru.nc.webshop1.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.nc.webshop1.entity.Customer;
import ru.nc.webshop1.util.HibernateUtil;

public class CustomerDao extends AbstractDAO<Customer>{

    @Override
    public String getSelectQuery() {
        return "from Customer";
    }
    
    @Override
    public Customer getByID(int id) throws SQLException {
        Customer newEntity = null;
        String queryString = getSelectQuery() + " where role = 1 AND id = :id";
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.setHibernateFlushMode(FlushMode.MANUAL);
            Query query = session.createQuery(queryString);
            query.setParameter("id", id);
            newEntity = (Customer) query.uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Ошибка поиска записи!\n" + e.getMessage());
        }
        
        if (newEntity == null) {
            throw new SQLException("Сущность не найдена!");
        }
        
        return newEntity;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        List<Customer> list;
        String query = getSelectQuery() + " where role = 1";
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.setHibernateFlushMode(FlushMode.MANUAL);
            list = session.createQuery(query).list();
        } catch (Exception e) {
            throw new SQLException("Ошибка в обработке запроса на выборку!\n" + e.getMessage());
        }
        return list;
    }
    
}
