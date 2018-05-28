package com.loveqrc.dao;

import com.loveqrc.domain.Product;
import com.loveqrc.utils.DataSourceUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    public List<Product> findAll() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product";
        return queryRunner.query(sql, new BeanListHandler<>(Product.class));
    }

    public void addProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());

        String sql = "insert into product(pid,pname,market_price,shop_price,pdate,pdesc) values(?,?,?,?,?,?) ";
        queryRunner.update(sql, product.getPid(), product.getPname(),
                product.getMarket_price(), product.getShop_price(), product.getPdate(), product.getPdesc());
    }

    public Product getProductById(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where pid =?";

        return queryRunner.query(sql, new BeanHandler<>(Product.class), pid);
    }

    public void updateProduct(Product product) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "update product set pname = ? ,market_price = ? ,shop_price =?,pdesc =? where pid = ?";
        queryRunner.update(sql, product.getPname(), product.getMarket_price(), product.getShop_price(),
                product.getPdesc(), product.getPid());
    }

    public void deleteProductById(String pid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "delete from product  where pid = ?";
        queryRunner.update(sql, pid);
    }


    public List<Product> findProductByCondition(String name, String kw) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product where 1=1";

        ArrayList<String> params = new ArrayList<>();
        if (name != null && name.trim().length() > 0) {
            sql += " and pname like ?";
//            sql+=(" and pname like ? ");
            params.add("%" + name + "%");
        }

        if (kw != null && kw.trim().length() > 0) {
            sql += "add pdesc like ?";
            params.add("%" + kw + "%");
        }


        return queryRunner.query(sql, new BeanListHandler<>(Product.class), params.toArray());
    }


    public List<Product> findProductByPage(int currPage, int pageSize) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select * from product limit ?,?";

        return queryRunner.query(sql, new BeanListHandler<>(Product.class),
                (currPage - 1) * pageSize, pageSize);
    }

    public int getCount() throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DataSourceUtils.getDataSource());
        String sql = "select count(*) from product";
        return ((Long) queryRunner.query(sql, new ScalarHandler())).intValue();

    }
}
