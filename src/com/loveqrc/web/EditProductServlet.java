package com.loveqrc.web;

import com.loveqrc.domain.Product;
import com.loveqrc.service.ProductService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(
        urlPatterns = "/editProduct"
)
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Product product = new Product();

        try {
            BeanUtils.populate(product, req.getParameterMap());
            new ProductService().updateProduct(product);
            resp.sendRedirect(req.getContextPath() + "/findAll");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("msg", "修改商品出错");
            req.getRequestDispatcher("/msg.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
