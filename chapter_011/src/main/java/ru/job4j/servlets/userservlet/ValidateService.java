package ru.job4j.servlets.userservlet;

import com.google.common.base.Joiner;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;
/**
 * ValidateService
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 05.02.2020
 */
public class ValidateService {
    private static final String UR = "Uncorrected Request!";
    private static final String UNF = "Users not Found!";
    private static final String ANU = "Add new";
    private static final String OLD_USER = "Old User:";
    private static final String UPDATE_TO = "Update to:";
    private static final String UPDATE = "update";
    private static final String ID = "id";
    private static final String DELETE = "Deleted";
    private static final String DEL = "del";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final String LS = "<br>";
    private static final ValidateService SERVICE = new ValidateService();
    private final Store logic = MemoryStore.getInstance();

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
    public String add(final HttpServletRequest req) {
        String result = UR;
        if (req.getParameterMap().size() == 4) {
            User user = new User(
                    req.getParameter(NAME),
                    req.getParameter(EMAIL),
                    req.getParameter(LOGIN)
            );
            this.logic.add(user);
            result = Joiner.on(" ").join(ANU, user);
        }
        return result;
    }

    /**
     * Find User by id.
     * @param req request
     * @return answer
     */
    public String findById(final HttpServletRequest req) {
        String result = UNF;
        String id = req.getParameter(ID);
        User user = this.logic.findById(new User().setId(id));
        if (user != null) {
            result = user.toString();
        }
        return result;
    }

    /**
     * Find all Users.
     * @param req request
     * @return answer
     * @noinspection unused
     */
    public String findAll(final HttpServletRequest req) {
        String result = UNF;
        Set<User> users = this.logic.findAll();
        if (!users.isEmpty()) {
            result = Joiner.on(LS).join(users);
        }
        return result;
    }

    /**
     * Delete User.
     * @param req request
     * @return answer
     */
    public String delete(final HttpServletRequest req) {
        String result = UNF;
        String id = req.getParameter(DEL);
        User user = this.logic.delete(new User().setId(id));
        if (user != null) {
            result = Joiner.on(" ").join(DELETE, user);
        }
        return result;
    }

    /**
     * Update User.
     * @param req request
     * @return answer
     */
    public String update(final HttpServletRequest req) {
        String result = UNF;
        if (req.getParameterMap().size() == 4) {
            String id = req.getParameter(UPDATE);
            User oldUser = this.logic.findById(new User().setId(id));
            if (oldUser != null) {
                User user = new User(
                        req.getParameter(NAME),
                        req.getParameter(EMAIL),
                        req.getParameter(LOGIN)
                ).setId(req.getParameter(UPDATE));
                this.logic.update(user);
                result = Joiner.on(LS).join(OLD_USER, oldUser, UPDATE_TO, user);
            }
        }
        return result;
    }
}
