package ru.nc.webshop1.dao;

import ru.nc.webshop1.entity.Bicycle;

public class BicycleDao extends AbstractDAO<Bicycle>{

    @Override
    public String getSelectQuery() {
        return "from Bicycle";
    }    
    
}
