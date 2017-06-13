package ru.nc.webshop1.dao;

import java.sql.SQLException;
import java.util.List;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.nc.webshop1.entity.Manager;
import ru.nc.webshop1.util.HibernateUtil;

public class ManagerDao extends AbstractDAO<Manager>{

    @Override
    public String getSelectQuery() {
        return "from Manager";
    }
    
    @Override
    public Manager getByID(int id) throws SQLException {
        Manager newEntity = null;
        String queryString = getSelectQuery() + " where role = 0 AND id = :id";
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.setHibernateFlushMode(FlushMode.MANUAL);
            Query query = session.createQuery(queryString);
            query.setParameter("id", id);
            newEntity = (Manager) query.uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Ошибка поиска записи!\n" + e.getMessage());
        }
        
        if (newEntity == null) {
            throw new SQLException("Сущность не найдена!");
        }
        
        return newEntity;
    }

    @Override
    public List<Manager> getAll() throws SQLException {
        List<Manager> list;
        String query = getSelectQuery() + " where role = 0";
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.setHibernateFlushMode(FlushMode.MANUAL);
            list = session.createQuery(query).list();
        } catch (Exception e) {
            throw new SQLException("Ошибка в обработке запроса на выборку!\n" + e.getMessage());
        }
        return list;
    }
}
