package ru.nc.webshop1.controllers;

import ru.nc.webshop1.dao.CustomerDao;
import ru.nc.webshop1.entity.Customer;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/registration")
public class RegisrationController {
    
    @Autowired
    private HttpServletRequest request;
    
    CustomerDao customerDAO;

    public RegisrationController() {
        customerDAO = new CustomerDao();
    }
    
    @RequestMapping(method = RequestMethod.GET)
    protected String doGet() {
        return "registration";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String doPost(Model model) 
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("user");
        String pass = request.getParameter("pwd");
        String pass1 = request.getParameter("pwd1");

        if (pass.isEmpty() || pass1.isEmpty() || !pass.equals(pass1)) {
            model.addAttribute("message", "<font color=red>Пароли пустые или не совпадают!</font>");
            return "registration";
        } else {
            try {
                customerDAO.create(new Customer(name, pass));
                model.addAttribute("message", "<font color=purple>Регистрация успешна! Вы можете войти.</font>");
                return "login";
            } catch (SQLException e) {
                model.addAttribute("message", "<font color=red>Ошибка ввода данных! Возможно такой пользователь уже существует.</font>");
                return "registration";
            }
        }
    }
}
