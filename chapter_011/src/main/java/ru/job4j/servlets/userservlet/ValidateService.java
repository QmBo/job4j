package ru.job4j.servlets.userservlet;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
/**
 * ValidateService
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.02.2020
 */
public class ValidateService {
    private static final String ID = "id";
    private static final String DEL = "del";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final ValidateService SERVICE = new ValidateService();
    private final Store logic = DbStore.getInstance();

    /**
     * Private constructor.
     */
    private ValidateService() {
    }

    /**
     * Get instance.
     * @return instance
     */
    public static ValidateService getInstance() {
        return SERVICE;
    }

    /**
     * Add User.
     * @param req request
     * @return answer
     */
    public User add(final HttpServletRequest req) {
        User user = new User(
                req.getParameter(NAME),
                req.getParameter(EMAIL),
                req.getParameter(LOGIN)
        );
        return this.logic.add(user);
    }

    /**
     * Find User by id.
     * @param req request
     * @return answer
     */
    public User findById(final HttpServletRequest req) {
        return this.logic.findById(new User().setId(req.getParameter(ID)));
    }

    /**
     * Find all Users.
     * @return answer
     */
    public Set<User> findAll() {
        return this.logic.findAll();
    }

    /**
     * Delete User.
     * @param req request
     * @return answer
     */
    public User delete(final HttpServletRequest req) {
        return this.logic.delete(new User().setId(req.getParameter(DEL)));
    }

    /**
     * Update User.
     * @param req request
     * @return answer
     */
    public User update(final HttpServletRequest req) {
        User result = null;
        String id = req.getParameter(ID);
        User oldUser = this.logic.findById(new User().setId(id));
        if (oldUser != null) {
            User user = new User(
                    req.getParameter(NAME),
                    req.getParameter(EMAIL),
                    req.getParameter(LOGIN)
            ).setId(id);
            result = this.logic.update(user);
        }
        return result;
    }
}
