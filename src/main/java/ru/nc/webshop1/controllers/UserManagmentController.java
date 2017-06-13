package ru.nc.webshop1.controllers;

import ru.nc.webshop1.dao.CustomerDao;
import ru.nc.webshop1.dao.ManagerDao;
import ru.nc.webshop1.entity.Customer;
import ru.nc.webshop1.entity.Manager;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping(value = "managment/UserManagment")
public class UserManagmentController {

    @Autowired
    private HttpServletRequest request;
    
    CustomerDao customerDAO;
    ManagerDao managerDAO;

    public UserManagmentController() {
        customerDAO = new CustomerDao();
        managerDAO = new ManagerDao();
    }
    
    @RequestMapping(value = "/managers", method = RequestMethod.GET)
    private ModelAndView managerList()
            throws SQLException {
        List<Manager> managerList = managerDAO.getAll();
        return new ModelAndView("managment/UserManagment", "userList", managerList);
    }
    
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    private ModelAndView userList()
            throws SQLException {
        List<Customer> customerList = customerDAO.getAll();
        return new ModelAndView("managment/UserManagment", "userList", customerList);
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    private ModelAndView showEditForm()
            throws SQLException, UnsupportedEncodingException{
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        Manager manager = null;
        Customer customer = null;
        try {
            manager = managerDAO.getByID(id);
        } catch (SQLException e) {
            try {
                customer = customerDAO.getByID(id);
            } catch (SQLException ex) {
                throw new SQLException(ex);
            }
        }
        if (manager == null) {
            return new ModelAndView("managment/UserForm", "user", customer);
        } else {
            return new ModelAndView("managment/UserForm", "user", manager);
        }
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    private String updateUser()
            throws SQLException, UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        int role = Integer.parseInt(request.getParameter("role"));
        Manager manager = null;
        Customer customer = null;
        try {
            manager = managerDAO.getByID(id);
        } catch (SQLException e) {
            try {
                customer = customerDAO.getByID(id);
            } catch (SQLException ex) {
                throw new SQLException(ex);
            }
        }
        if (manager == null) {
            customer.setName(name);
            customer.setPass(pass);
            customer.setRole(role);
            customerDAO.update(customer);
            return "redirect:/managment/UserManagment/customers";
        } else {
            manager.setName(name);
            manager.setPass(pass);
            manager.setRole(role);
            managerDAO.update(manager);
            return "redirect:/managment/UserManagment/managers";
        }
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    private String deleteUser()
            throws SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        Manager manager = null;
        Customer customer = null;
        try {
            manager = managerDAO.getByID(id);
        } catch (SQLException e) {
            try {
                customer = customerDAO.getByID(id);
            } catch (SQLException ex) {
                throw new SQLException(ex);
            }
        }
        if (manager == null) {
            customerDAO.delete(customer);
            return "redirect:/managment/UserManagment/customers";
        } else {
            managerDAO.delete(manager);
            return "redirect:/managment/UserManagment/managers";
        }
    }
}
