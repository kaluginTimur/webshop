package ru.nc.webshop1.controllers;

import ru.nc.webshop1.dao.BicycleDao;
import ru.nc.webshop1.dao.ShopCartDao;
import ru.nc.webshop1.dao.ShopOrderDao;
import ru.nc.webshop1.entity.Bicycle;
import ru.nc.webshop1.entity.ShopCart;
import ru.nc.webshop1.entity.ShopOrder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/order")
public class OrderController {
    
    @Autowired
    private HttpServletRequest request;
    
    ShopOrderDao orderDAO;

    public OrderController() {
        orderDAO = new ShopOrderDao();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    private String orderList(Model model)
            throws SQLException {
        Integer currentUserID;
        if (request.getParameter("managmentUserIdOrder") == null) {
            currentUserID = getUserId();
        } else {
            currentUserID = Integer.parseInt(request.getParameter("managmentUserIdOrder"));
        }

        List<ShopOrder> orderListAll = orderDAO.getAll();
        List<ShopOrder> orderList = new ArrayList<>();
        if (!orderListAll.isEmpty()) {
            for (ShopOrder sc : orderListAll) {
                Integer userID = sc.getUserid();
                if (userID.equals(currentUserID)) {
                    orderList.add(orderDAO.getByID(sc.getId()));
                }
            }
        }

        if (!orderList.isEmpty()) {
            model.addAttribute("orders", orderList);
        }
        return "order";
    }
    
    @RequestMapping(value = "ordersondelete", method = RequestMethod.GET)
    private String deletedOrdersList(Model model)
            throws SQLException {
        List<ShopOrder> orderListAll = orderDAO.getAll();
        List<ShopOrder> orderList = new ArrayList<>();
        if (!orderListAll.isEmpty()) {
            for (ShopOrder sc : orderListAll) {
                if (sc.getStatus() == -1) {
                    orderList.add(orderDAO.getByID(sc.getId()));
                }
            }
        }

        if (!orderList.isEmpty()) {
            model.addAttribute("orders", orderList);
        }
        return "order";
    }
    
    @RequestMapping(value = "commit", method = RequestMethod.GET)
    private String commitOrder()
            throws SQLException {
        Integer currentUserID = getUserId();

        ShopCartDao shopCartDAO = new ShopCartDao();
        BicycleDao bicycleDAO = new BicycleDao();
        List<Bicycle> itemList = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        double price = 0;
        List<ShopCart> shopCartListAll = shopCartDAO.getAll();
        if (!shopCartListAll.isEmpty()) {
            for (ShopCart sc : shopCartListAll) {
                Integer userID = sc.getUserid();
                if (userID.equals(currentUserID)) {
                    Bicycle bicycle = bicycleDAO.getByID(sc.getItemid());
                    amounts.add(sc.getAmount());
                    price += bicycle.getPrice() * sc.getAmount();
                    itemList.add(bicycle);
                    shopCartDAO.delete(sc);
                }
            }
        }
        String items = "";
        if (!itemList.isEmpty()) {
            int index = 0;
            for (Bicycle bicycle : itemList) {
                items += StringEscapeUtils.unescapeJava(bicycle.getModel() + ", " + amounts.get(index)
                        + " шт., Цена: " + bicycle.getPrice() + ",<br/>" + "<p align=\"right\">"
                        + "Сумма: " + bicycle.getPrice() * amounts.get(index) + ";</p>");
                ++index;
            }
        }
        orderDAO.create(new ShopOrder(currentUserID, items, price, 0));

        return "redirect:/order";
    }
    
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    private String deleteOrder()
            throws SQLException {
        Integer orderId = Integer.parseInt(request.getParameter("orderId"));

        ShopOrder order = orderDAO.getByID(orderId);
        orderDAO.delete(order);

        return "redirect:/order/ordersondelete";
    }
    
    @RequestMapping(value = "changestatus", method = { RequestMethod.GET, RequestMethod.POST })
    private String changeStatus()
            throws SQLException {
        Integer currentUserID;
        if (request.getParameter("managmentUserIdOrder") == null) {
            currentUserID = getUserId();
        } else {
            currentUserID = Integer.parseInt(request.getParameter("managmentUserIdOrder"));
        }
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));

        ShopOrder order = orderDAO.getByID(orderId);
        order.setStatus(orderStatus);
        orderDAO.update(order);

        HttpSession session = request.getSession();
        if (request.getParameter("managmentUserIdOrder") != null) {
            Integer userIdOrder = Integer.parseInt(request.getParameter("managmentUserIdOrder"));
            Integer sessionId = (Integer) session.getAttribute("userid");
            if (userIdOrder.equals(sessionId)) {
                return "redirect:/order";
            } else {
                return "redirect:/order?managmentUserIdOrder=" + userIdOrder;
            }
        } else {
            return "redirect:/order";
        }
    }
    
    private Integer getUserId() {
        HttpSession session = request.getSession();
        Integer currentUserID;
        if (session.getAttribute("userid") != null) {
            currentUserID = (Integer) request.getSession().getAttribute("userid");
        } else {
            currentUserID = (Integer) session.getAttribute("hash");
        }

        return currentUserID;
    }
}
