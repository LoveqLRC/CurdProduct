package com.loveqrc.web;

import com.loveqrc.domain.PageBean;
import com.loveqrc.domain.Product;
import com.loveqrc.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(
        urlPatterns = "/showProductsByPage"
)
public class ShowProductsByPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currPage = Integer.parseInt(req.getParameter("currPage"));
        int pageSize = 3;
        PageBean<Product> bean = null;

        try {
            bean = new ProductService().showProductsByPage(currPage, pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("pb", bean);
        req.getRequestDispatcher("/product_page.jsp")
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
