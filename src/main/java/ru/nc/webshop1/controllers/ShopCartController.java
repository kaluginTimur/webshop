package ru.nc.webshop1.controllers;

import ru.nc.webshop1.dao.BicycleDao;
import ru.nc.webshop1.dao.ImageDao;
import ru.nc.webshop1.dao.ShopCartDao;
import ru.nc.webshop1.dao.StockDao;
import ru.nc.webshop1.entity.Bicycle;
import ru.nc.webshop1.entity.ShopCart;
import ru.nc.webshop1.entity.Stock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/shopcart")
public class ShopCartController {
    
    @Autowired
    private HttpServletRequest request;
    
    ShopCartDao shopCartDAO;
    StockDao stockDAO;
    ImageDao imageDAO;

    public ShopCartController() {
        shopCartDAO = new ShopCartDao();
        stockDAO = new StockDao();
        imageDAO = new ImageDao();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    private String shopCartList(Model model)
            throws SQLException {
        List<Bicycle> itemList = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        BicycleDao bicycleDAO = new BicycleDao();
        Integer currentUserID = getUserId();

        List<ShopCart> shopCartListAll = shopCartDAO.getAll();
        if (!shopCartListAll.isEmpty()) {
            for (ShopCart sc : shopCartListAll) {
                Integer userID = sc.getUserid();
                if (userID.equals(currentUserID)) {
                    itemList.add(bicycleDAO.getByID(sc.getItemid()));
                    amounts.add(sc.getAmount());
                }
            }
        }
        
        if (!itemList.isEmpty()) {
            model.addAttribute("itemList", itemList);
            model.addAttribute("amounts", amounts);
        }
        return "shopcart";
    }
    
    @RequestMapping(value = "/erase", method = RequestMethod.GET)
    private String deleteShopCart()
            throws SQLException {
        Integer currentUserID = getUserId();

        List<ShopCart> shopCartListAll = shopCartDAO.getAll();
        if (!shopCartListAll.isEmpty()) {
            for (ShopCart sc : shopCartListAll) {
                Integer userID = sc.getUserid();
                if (userID.equals(currentUserID)) {
                    Stock stock = stockDAO.getByID(sc.getItemid());
                    stock.setAmount(stock.getAmount() + sc.getAmount());
                    stockDAO.update(stock);
                    shopCartDAO.delete(sc);
                }
            }
        }
        return "redirect:/shopcart";
    }
    
    @RequestMapping(value = "/add")
    private String addItem(RedirectAttributes redir)
            throws SQLException {
        int itemid = Integer.parseInt(request.getParameter("id"));
        Integer currentUserID = getUserId();

        Stock stock = stockDAO.getByID(itemid);
        if (stock.getAmount() > 0) {
            ShopCart shopCart = null;
            try {
                shopCart = shopCartDAO.getByItem(currentUserID, itemid);
            } catch (SQLException e) {
            }

            if (shopCart == null) {
                shopCart = shopCartDAO.create(new ShopCart(currentUserID, itemid, 1));
            } else {
                shopCart.setAmount(shopCart.getAmount() + 1);
                shopCartDAO.update(shopCart);
            }

            stock.setAmount(stock.getAmount() - 1);
            stockDAO.update(stock);
            redir.addFlashAttribute("message", "Товар добавлен! В корзине: " + shopCart.getAmount());
            redir.addFlashAttribute("messageid", itemid);
        }        
        HttpSession session = request.getSession();
        return "redirect:/?action=" + session.getAttribute("category");
    }
    
    @RequestMapping(value = "/increase", method = RequestMethod.GET)
    private String increaseItem()
            throws SQLException {
        int itemid = Integer.parseInt(request.getParameter("id"));
        Integer currentUserID = getUserId();

        Stock stock = stockDAO.getByID(itemid);
        if (stock.getAmount() > 0) {
            ShopCart shopCart = shopCartDAO.getByItem(currentUserID, itemid);
            shopCart.setAmount(shopCart.getAmount() + 1);
            stock.setAmount(stock.getAmount() - 1);
            shopCartDAO.update(shopCart);
            stockDAO.update(stock);
        }
        return "redirect:/shopcart";
    }
    
    @RequestMapping(value = "/decrease", method = RequestMethod.GET)
    private String decreaseItem()
            throws SQLException {
        int itemid = Integer.parseInt(request.getParameter("id"));
        Integer currentUserID = getUserId();
        
        Stock stock = stockDAO.getByID(itemid);
        ShopCart shopCart = shopCartDAO.getByItem(currentUserID, itemid);
        int amount = shopCart.getAmount();
        if (amount > 0) {
            amount--;
            if (amount != 0) {
                shopCart.setAmount(amount);
                stock.setAmount(stock.getAmount() + 1);
                shopCartDAO.update(shopCart);
                stockDAO.update(stock);
                return "redirect:/shopcart";
            } else {
                return deleteItem();
            }
        } else {
            return "redirect:/shopcart";
        }
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    private String deleteItem()
            throws SQLException {
        int itemid = Integer.parseInt(request.getParameter("id"));
        Integer currentUserID = getUserId();
        
        Stock stock = stockDAO.getByID(itemid);
        ShopCart shopCart = shopCartDAO.getByItem(currentUserID, itemid);
        shopCartDAO.delete(shopCart);
        stock.setAmount(stock.getAmount() + shopCart.getAmount());
        stockDAO.update(stock);
        
        return "redirect:/shopcart";
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
