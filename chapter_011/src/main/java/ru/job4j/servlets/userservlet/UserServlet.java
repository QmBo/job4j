package ru.job4j.servlets.userservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class UserServlet extends HttpServlet {
    private static final Logger LOG = LogManager.getLogger(UserServlet.class);
    private static final String ID = "id";
    private static final String ADD = "add";
    private static final String ALL = "all";
    private static final String TYPE = "text/html";
    private static final String DEL = "del";
    private static final String UPDATE = "update";
    private final ValidateService logic = ValidateService.getInstance();
    private final Map<String, Function<HttpServletRequest, String>> getDispatch = new HashMap<>();
    private final Map<String, Function<HttpServletRequest, String>> postDispatch = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType(TYPE);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append(this.request(this.getDispatcherInit(), req));
            writer.flush();
        } catch (IOException e) {
            LOG.error("error IOException", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType(TYPE);
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append(this.request(this.postDispatcherInit(), req));
            writer.flush();
        } catch (IOException e) {
            LOG.error("error IOException", e);
        }
    }

    /**
     * Init get dispatcher.
     * @return get methods map.
     */
    private Map<String, Function<HttpServletRequest, String>> getDispatcherInit() {
        this.getDispatch.put(ID, this.findById());
        this.getDispatch.put(ALL, this.findAll());
        return this.getDispatch;
    }

    /**
     * Init post dispatcher.
     * @return post methods map.
     */
    private Map<String, Function<HttpServletRequest, String>> postDispatcherInit() {
        this.postDispatch.put(ADD, this.add());
        this.postDispatch.put(DEL, this.delete());
        this.postDispatch.put(UPDATE, this.update());
        return this.postDispatch;
    }

    /**
     * Update User.
     * @return answer.
     */
    private Function<HttpServletRequest, String> update() {
        return this.logic::update;
    }

    /**
     * Delete User.
     * @return answer.
     */
    private Function<HttpServletRequest, String> delete() {
        return this.logic::delete;
    }

    /**
     * Add new User.
     * @return answer.
     */
    private Function<HttpServletRequest, String> add() {
        return this.logic::add;
    }

    /**
     * Find User by id.
     * @return answer.
     */
    private Function<HttpServletRequest, String> findById() {
        return this.logic::findById;
    }

    /**
     * Find all User.
     * @return answer.
     */
    private Function<HttpServletRequest, String> findAll() {
        return this.logic::findAll;
    }

    /**
     * Dispatcher do.
     * @param dispatcher dispatcher map.
     * @param req request
     * @return answer
     */
    public String request(
            final Map<String, Function<HttpServletRequest, String>> dispatcher,
            final HttpServletRequest req) {
        return dispatcher.get(
                req.getParameterNames().nextElement()
        ).apply(req);
    }
}
