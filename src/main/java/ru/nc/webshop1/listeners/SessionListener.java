package ru.nc.webshop1.listeners;

import ru.nc.webshop1.entity.ShopCart;
import ru.nc.webshop1.dao.ShopCartDao;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Integer hash = session.hashCode() / ThreadLocalRandom.current().nextInt(100, 1000);
        session.setAttribute("hash", hash);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        Integer hash = (Integer) session.getAttribute("hash");
        if (session.getAttribute("userid") == null) {
            ShopCartDao shopCartDAO = new ShopCartDao();
            List<ShopCart> shopCartListAll = null;
            try {
                shopCartListAll = shopCartDAO.getAll();
            } catch (SQLException e) {
                System.err.println("ERROR in SessionListener." + e);
            }
            if (shopCartListAll != null && !shopCartListAll.isEmpty()) {
                for (ShopCart sc : shopCartListAll) {
                    Integer userID = sc.getUserid();
                    if (userID.equals(hash)) {
                        try {
                            shopCartDAO.delete(sc);
                        } catch (SQLException e) {
                            System.err.println("ERROR in SessionListener." + e);
                        }
                    }
                }
            }
        }
    }
}
