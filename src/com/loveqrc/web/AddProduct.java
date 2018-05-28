package com.loveqrc.web;

import com.loveqrc.domain.Product;
import com.loveqrc.service.ProductService;
import com.loveqrc.utils.UUIDUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;

@WebServlet(
        urlPatterns = "/addProduct"
)
public class AddProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String s_lingpai = (String) req.getSession().getAttribute("s_lingpai");

        String r_lingpai = req.getParameter("r_lingpai");

        req.getSession().removeAttribute("s_lingpai");
        if (s_lingpai == null || !s_lingpai.equals(r_lingpai)) {
            req.setAttribute("msg", "商品已经添加");
            req.getRequestDispatcher("/msg.jsp").forward(req, resp);
            return;
        }

        Product product = new Product();
        try {
            BeanUtils.populate(product, req.getParameterMap());

            product.setPid(UUIDUtils.getId());

            product.setPdate(new Date());

            new ProductService().addProduct(product);

            req.getRequestDispatcher("/findAll").forward(req, resp);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
