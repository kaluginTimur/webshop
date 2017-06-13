package ru.nc.webshop1.dao;

import java.sql.SQLException;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import ru.nc.webshop1.entity.ShopCart;
import ru.nc.webshop1.util.HibernateUtil;

public class ShopCartDao extends AbstractDAO<ShopCart>{

    @Override
    public String getSelectQuery() {
        return "from ShopCart";
    }

    public ShopCart getByItem(int userid, int itemid) throws SQLException {
        ShopCart newEntity = null;
        String queryString = getSelectQuery() + " where USER_ID = :userid AND ITEM_ID = :itemid";
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.setHibernateFlushMode(FlushMode.MANUAL);
            Query query = session.createQuery(queryString);
            query.setParameter("userid", userid);
            query.setParameter("itemid", itemid);
            newEntity = (ShopCart) query.uniqueResult();
        } catch (Exception e) {
            throw new SQLException("Ошибка поиска записи!\n" + e.getMessage());
        }
        
        if (newEntity == null) {
            throw new SQLException("Сущность не найдена!");
        }
        
        return newEntity;
    }
    
}
