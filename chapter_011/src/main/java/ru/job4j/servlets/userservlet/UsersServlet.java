package ru.job4j.servlets.userservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import static java.lang.String.format;

/**
 * UsersServlet
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 13.02.2020
 */
public class UsersServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UsersServlet.class);
    private static final String TYPE = "text/html";
    private static final String UNF = "<h2>Users not found</h2>";
    private final ValidateService service = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType(TYPE);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append(this.userTab());
            writer.flush();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        this.deleteUser(req);
        try {
            resp.sendRedirect(format("%s/", req.getContextPath()));
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    /**
     * Delete user.
     * @param req request
     */
    private void deleteUser(HttpServletRequest req) {
        this.service.delete(req);
    }

    /**
     * Page users list.
     * @return page
     */
    protected String userTab() {
        Set<User> users = this.service.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html><html lang=\"en\">");
        sb.append("<head><meta charset=\"UTF-8\"><title>Users</title></head><body>");
        sb.append("<form action=\"create\"><button>Create User</button></form>");
        if (!users.isEmpty()) {
            sb.append("<table border=\"0\">");
            for (User user : users) {
                String id = user.getId();
                sb.append("<tr><td>");
                sb.append(user);
                sb.append("</td>");
                sb.append("<td><form action=\"edit\" method=\"get\">");
                sb.append("<input type=\"hidden\" name=\"id\" value=\"");
                sb.append(id);
                sb.append("\"/><input type=\"submit\" value=\"Update\"/>");
                sb.append("</form></td>");
                sb.append("<td><form action=\"list\" method=\"post\">");
                sb.append("<input type=\"hidden\" name=\"del\" value=\"");
                sb.append(id);
                sb.append("\"/><input type=\"submit\" value=\"Delete\"/>");
                sb.append("</form></td></tr>");
            }
            sb.append("</table>");
        } else {
            sb.append(UNF);
        }
        sb.append("</body></html>");
        return sb.toString();
    }
}
