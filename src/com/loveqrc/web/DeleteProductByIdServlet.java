package com.loveqrc.web;

import com.loveqrc.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(
        urlPatterns = "/deleteProductById"
)
public class DeleteProductByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pid = req.getParameter("pid");
        try {
            new ProductService().deleteProductById(pid);
        } catch (SQLException e) {
            req.setAttribute("msg", "删除商品失败");
            req.getRequestDispatcher("/msg.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/findAll");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
