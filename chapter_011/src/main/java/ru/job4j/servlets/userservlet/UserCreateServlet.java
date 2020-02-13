package ru.job4j.servlets.userservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * UserCreateServlet
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 13.02.2020
 */
public class UserCreateServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserCreateServlet.class);
    private final ValidateService service = ValidateService.getInstance();
    private static final String TYPE = "text/html";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType(TYPE);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append(this.createUserForm());
            writer.flush();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType(TYPE);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            this.addUser(req);
            writer.append(this.createUserForm());
            writer.flush();
        } catch (IOException e) {
            LOG.error("IOException", e);
        }
    }

    /**
     * Add new user.
     * @param req request
     */
    protected void addUser(HttpServletRequest req) {
        this.service.add(req);
    }

    /**
     * Page to add user.
     * @return page
     */
    protected String createUserForm() {
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html><html lang=\"en\">");
        sb.append("<head><meta charset=\"UTF-8\"><title>Users</title></head><body>");
        sb.append("<table border=\"0\">");
        sb.append("<tr><td>");
        sb.append("<form action=\"create\" method=\"post\">");
        sb.append("User name");
        sb.append("</td><td>");
        sb.append("<input placeholder=\"User name\" name=\"name\">");
        sb.append("</td></tr><tr><td>");
        sb.append("User login");
        sb.append("</td><td>");
        sb.append("<input placeholder=\"User login\" name=\"login\">");
        sb.append("</td></tr><tr><td>");
        sb.append("User email");
        sb.append("</td><td>");
        sb.append("<input type=\"email\" placeholder=\"User email\" name=\"email\">");
        sb.append("</td></tr><tr><td>");
        sb.append("<input type=\"submit\" value=\"Create\"></form>");
        sb.append("</td><td>");
        sb.append("<form action=\"list\"><button>Users list</button></form>");
        sb.append("</td></tr>");
        sb.append("</table>");
        sb.append("</body></html>");
        return sb.toString();
    }
}
