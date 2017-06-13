package ru.nc.webshop1.dao;

import ru.nc.webshop1.entity.Stock;

public class StockDao extends AbstractDAO<Stock>{
    
    @Override
    public String getSelectQuery() {
        return "from Stock";
    }
       
}
