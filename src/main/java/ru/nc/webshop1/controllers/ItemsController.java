package ru.nc.webshop1.controllers;

import ru.nc.webshop1.dao.BicycleDao;
import ru.nc.webshop1.entity.Bicycle;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class ItemsController {

    @Autowired
    private HttpServletRequest request;

    BicycleDao bicycleDAO;

    public ItemsController() {
        bicycleDAO = new BicycleDao();
    }

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView getItems()
            throws ServletException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String category = "";
        HttpSession session = request.getSession();
        session.setAttribute("category", "");
        if (request.getParameter("action") != null) {
            String action = request.getParameter("action");
            switch (action) {
                case "1":
                    category = "Спортивный";
                    session.setAttribute("category", "1");
                    break;
                case "2":
                    category = "Горный";
                    session.setAttribute("category", "2");
                    break;
                case "3":
                    category = "Женский";
                    session.setAttribute("category", "3");
                    break;
                case "4":
                    category = "Экстремальный";
                    session.setAttribute("category", "4");
                    break;
                case "5":
                    category = "Городской";
                    session.setAttribute("category", "5");
                    break;
                default:
                    session.setAttribute("category", "");
            }
        }
        List<Bicycle> bicycleList = new ArrayList<>();
        try {
            itemList(bicycleList, category);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        return new ModelAndView("index", "bicycleList", bicycleList);
    }

    private void itemList(List bicycleList, String category)
            throws SQLException {
        List<Bicycle> bicycleListAll = bicycleDAO.getAll();
        if (!category.isEmpty()) {
            for (Bicycle bicycle : bicycleListAll) {
                if (bicycle.getType().equals(category)) {
                    bicycleList.add(bicycle);
                }
            }
        } else {
            bicycleList.addAll(bicycleListAll);
        }
    }

}
