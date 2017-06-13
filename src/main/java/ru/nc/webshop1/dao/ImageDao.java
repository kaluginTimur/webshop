package ru.nc.webshop1.dao;

import ru.nc.webshop1.entity.Image;

public class ImageDao extends AbstractDAO<Image>{

    @Override
    public String getSelectQuery() {
        return "from Image";
    }
    
}
