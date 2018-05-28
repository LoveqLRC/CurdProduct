package com.loveqrc.web;

import com.loveqrc.domain.Product;
import com.loveqrc.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(
        urlPatterns = "/findProductByCondition"
)
public class FindProductByConditionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String name = req.getParameter("name");
        String kw = req.getParameter("kw");

        try {
            List<Product> productList = new ProductService().findProductByCondition(name, kw);
            req.setAttribute("list", productList);
            req.getRequestDispatcher("/findAll.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);

    }
}
