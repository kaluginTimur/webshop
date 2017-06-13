package ru.nc.webshop1.dao;

import ru.nc.webshop1.entity.ShopOrder;

public class ShopOrderDao extends AbstractDAO<ShopOrder>{

    @Override
    public String getSelectQuery() {
        return "from ShopOrder";
    }
    
}
