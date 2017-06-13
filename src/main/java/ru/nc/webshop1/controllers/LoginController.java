package ru.nc.webshop1.controllers;

import ru.nc.webshop1.dao.CustomerDao;
import ru.nc.webshop1.dao.ManagerDao;
import ru.nc.webshop1.entity.Customer;
import ru.nc.webshop1.entity.Entity;
import ru.nc.webshop1.entity.Manager;

import java.rmi.ServerException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    
    @Autowired
    private HttpServletRequest request;
    
    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    protected String cabinet() {
        return "cabinet";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    protected String doGet() {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    protected String doPost(Model model) 
            throws ServerException {

        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        try {
            Entity<Integer> userEntity = isExist(user, pwd);

            if (userEntity != null) {
                Manager manager;
                Customer customer;

                HttpSession session = request.getSession();
                Cookie userName = null;
                Cookie userRole = null;

                if (userEntity.getClass().equals(Manager.class)) {
                    manager = (Manager) userEntity;
                    session.setAttribute("userid", manager.getId());
                    session.setAttribute("user", manager.getName());
                    session.setAttribute("role", manager.getRole());
                    userName = new Cookie("user", manager.getName());
                    userRole = new Cookie("role", "0");
                }

                if (userEntity.getClass().equals(Customer.class)) {
                    customer = (Customer) userEntity;
                    session.setAttribute("userid", customer.getId());
                    session.setAttribute("user", customer.getName());
                    session.setAttribute("role", customer.getRole());
                    userName = new Cookie("user", customer.getName());
                    userRole = new Cookie("role", "1");
                }

                session.setMaxInactiveInterval(1800);
                userName.setMaxAge(1800);

                return cabinet();

            } else {
                
                model.addAttribute("message", "<font color=red>Логин или пароль введены неверно.</font>");                
                return "login";
            }
        } catch (SQLException e) {
            throw new ServerException(e.getMessage());
        }
    }
    
    private Entity<Integer> isExist(String user, String pass) throws SQLException {
        user = user.toLowerCase();
        List<Manager> managers = new ManagerDao().getAll();
        for (Manager m : managers) {
            if (m.getName().toLowerCase().equals(user)
                    && m.getPass().equals(pass)) {
                return m;
            }
        }

        List<Customer> customers = new CustomerDao().getAll();
        for (Customer c : customers) {
            if (c.getName().toLowerCase().equals(user) && c.getPass().equals(pass)) {
                return c;
            }
        }

        return null;
    }
}
