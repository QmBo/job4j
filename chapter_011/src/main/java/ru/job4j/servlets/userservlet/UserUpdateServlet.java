package ru.job4j.servlets.userservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.String.format;

/**
 * UserUpdateServlet
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 13.02.2020
 */
public class UserUpdateServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserUpdateServlet.class);
    private static final String TYPE = "text/html";
    private static final String UNF = "<h2>Users not found</h2>";
    private final ValidateService service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType(TYPE);
        try {
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            writer.append(this.updatePage(req));
            writer.flush();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            this.update(req);
            resp.sendRedirect(format("%s/edit/?id=%s", req.getContextPath(), req.getParameter("id")));
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    protected void update(HttpServletRequest req) {
        this.service.update(req);
    }

    /**
     * Page to update user.
     * @param req request
     * @return page
     */
    protected String updatePage(HttpServletRequest req) {
        User user = this.service.findById(req);
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html><html lang=\"en\">");
        sb.append("<head><meta charset=\"UTF-8\"><title>Users</title></head><body>");
        if (user != null) {
            sb.append("<table border=\"0\">");
            sb.append("<form action=\"edit\" method=\"post\">");
            sb.append("<input type=\"hidden\" name=\"id\" value=\"");
            sb.append(user.getId());
            sb.append("\">");
            sb.append("<tr><td>");
            sb.append("User name");
            sb.append("</td><td>");
            sb.append("<input placeholder=\"User name\" name=\"name\" value=\"");
            sb.append(user.getName());
            sb.append("\">");
            sb.append("</td></tr><tr><td>");
            sb.append("User login");
            sb.append("</td><td>");
            sb.append("<input placeholder=\"User login\" name=\"login\" value=\"");
            sb.append(user.getLogin());
            sb.append("\">");
            sb.append("</td></tr><tr><td>");
            sb.append("User email");
            sb.append("</td><td>");
            sb.append("<input type=\"email\" placeholder=\"User email\" name=\"email\" value=\"");
            sb.append(user.getEmail());
            sb.append("\">");
            sb.append("</td></tr><tr><td>");
            sb.append("<input type=\"submit\" value=\"Edit\"></form>");
            sb.append("</td><td>");
            sb.append("<form action=\"");
            sb.append(req.getContextPath());
            sb.append("\"><button>Users list</button></form>");
            sb.append("</td></tr>");
            sb.append("</table>");
        } else {
            sb.append(UNF);
        }
        sb.append("</body></html>");
        return sb.toString();
    }
}
