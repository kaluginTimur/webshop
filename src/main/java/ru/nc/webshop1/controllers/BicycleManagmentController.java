package ru.nc.webshop1.controllers;

import ru.nc.webshop1.dao.BicycleDao;
import ru.nc.webshop1.dao.ImageDao;
import ru.nc.webshop1.dao.StockDao;
import ru.nc.webshop1.entity.Bicycle;
import ru.nc.webshop1.entity.Image;
import ru.nc.webshop1.entity.Stock;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "managment/BicycleManagment")
public class BicycleManagmentController {

    @Autowired
    private HttpServletRequest request;

    BicycleDao bicycleDAO;
    StockDao stockDAO;
    ImageDao imageDAO;

    public BicycleManagmentController() {
        bicycleDAO = new BicycleDao();
        stockDAO = new StockDao();
        imageDAO = new ImageDao();
    }

    @RequestMapping(method = RequestMethod.GET)
    private ModelAndView bicycleList()
            throws SQLException {
        List<Bicycle> bicycleList = bicycleDAO.getAll();
        return new ModelAndView("managment/BicycleManagment", "bicycleList", bicycleList);
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    private String showNewForm() {
        return "managment/BicycleForm";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    private ModelAndView showEditForm(@RequestParam("id") int id)
            throws SQLException {
        Bicycle bicycle = bicycleDAO.getByID(id);
        return new ModelAndView("managment/BicycleForm", "bicycle", bicycle);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    private String createBicycle()
            throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        double weight = Double.parseDouble(request.getParameter("weight"));
        double wheeldiam = Double.parseDouble(request.getParameter("wheeldiam"));
        double price = Double.parseDouble(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String path = request.getParameter("path");
        Bicycle bicycle = new Bicycle(model, type, weight, wheeldiam, price);
        Stock stock = new Stock(amount);
        Image image = new Image(path);
        bicycle.setStock(stock);
        bicycle.setImage(image);
        stock.setBicycle(bicycle);
        image.setBicycle(bicycle);
        bicycleDAO.create(bicycle);
        return "redirect:/managment/BicycleManagment";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private String updateBicycle()
            throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String model = request.getParameter("model");
        String type = request.getParameter("type");
        double weight = Double.parseDouble(request.getParameter("weight"));
        double wheeldiam = Double.parseDouble(request.getParameter("wheeldiam"));
        double price = Double.parseDouble(request.getParameter("price"));
        int amount = Integer.parseInt(request.getParameter("amount"));
        String path = request.getParameter("path");
        Bicycle bicycle = bicycleDAO.getByID(id);
        Stock stock = bicycle.getStock();
        Image image = bicycle.getImage();

        bicycle.setModel(model);
        bicycle.setType(type);
        bicycle.setWeight(weight);
        bicycle.setWheeldiam(wheeldiam);
        bicycle.setPrice(price);
        stock.setAmount(amount);
        bicycle.setStock(stock);
        image.setPath(path);
        bicycle.setImage(image);
        stock.setBicycle(bicycle);
        image.setBicycle(bicycle);

        bicycleDAO.update(bicycle);
        return "redirect:/managment/BicycleManagment";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    private String deleteBicycle()
            throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));

        Bicycle bicycle = bicycleDAO.getByID(id);
        Stock stock = bicycle.getStock();
        Image image = bicycle.getImage();
        bicycleDAO.delete(bicycle);
        stockDAO.delete(stock);
        imageDAO.delete(image);
        return "redirect:/managment/BicycleManagment";
    }
}
