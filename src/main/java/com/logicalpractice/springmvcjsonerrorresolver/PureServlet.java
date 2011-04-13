package com.logicalpractice.springmvcjsonerrorresolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gareth
 */
public class PureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if (req.getParameter("reason") == null) {
            resp.setStatus(404);
        } else {
            // using sendError... fails, but using the deprecated setStatus(int,String) works
            resp.sendError(404, req.getParameter("reason"));
        }
        resp.getWriter().print("{'key':'value'}");
    }
}
