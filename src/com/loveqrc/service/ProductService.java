package com.loveqrc.service;

import com.loveqrc.dao.ProductDao;
import com.loveqrc.domain.PageBean;
import com.loveqrc.domain.Product;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    public List<Product> findAll() throws SQLException {
        return new ProductDao().findAll();
    }

    public void addProduct(Product product) throws SQLException {

        new ProductDao().addProduct(product);
    }

    public Product getProductById(String pid) throws SQLException {
        return new ProductDao().getProductById(pid);
    }

    public void updateProduct(Product product) throws SQLException {
        new ProductDao().updateProduct(product);
    }

    public void deleteProductById(String pid) throws SQLException {
        new ProductDao().deleteProductById(pid);
    }

    public void deleteManyProducts(String[] pids) throws SQLException {
        ProductDao productDao = new ProductDao();
        for (String pid : pids) {
            productDao.deleteProductById(pid);

        }
    }

    public List<Product> findProductByCondition(String name, String kw) throws SQLException {
        return new ProductDao().findProductByCondition(name, kw);
    }


    public PageBean<Product> showProductsByPage(int currPage, int pageSize) throws SQLException {
        ProductDao productDao = new ProductDao();
        List<Product> mlist = productDao.findProductByPage(currPage, pageSize);
        int count = productDao.getCount();

        return new PageBean<>(mlist, currPage, pageSize, count);
    }
}
