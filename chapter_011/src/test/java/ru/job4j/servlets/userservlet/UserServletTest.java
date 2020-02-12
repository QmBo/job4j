package ru.job4j.servlets.userservlet;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServletTest {
    private static final String UPDATE = "update";
    private static final String UPDATE_TO = "User update to:";
    private static final String LS = "<br>";
    private static final String ID = "id";
    private static final String DELETE = "Deleted";
    private static final String UNF = "Users not Found!";
    private static final String DEL = "del";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final String ANU = "Add new";

    @Mock
    HttpServletRequest request;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    private void addUser(UserServlet service) {
        when(request.getParameter(NAME)).thenReturn("Test");
        when(request.getParameter(LOGIN)).thenReturn("Test");
        when(request.getParameter(EMAIL)).thenReturn("Test");
        Map<String, String[]> map = new HashMap<>();
        map.put(ID, new String[]{""});
        map.put(NAME, new String[]{""});
        map.put(LOGIN, new String[]{""});
        map.put(EMAIL, new String[]{""});
        when(request.getParameterMap()).thenReturn(map);
        service.add().apply(request);
    }

    @Test
    public void whenAddThenAddUser() {
        UserServlet service = new UserServlet();
        addUser(service);
        String exp = Joiner.on(" ").join(ANU, "User{id='");
        assertThat(service.add().apply(request), is(startsWith(exp)));
    }

    @Test
    public void whenDeleteThenDeleteUser() {
        UserServlet service = new UserServlet();
        addUser(service);
        addUser(service);
        when(request.getParameter(DEL)).thenReturn("1");
        String exp = Joiner.on(" ").join(DELETE, "User{id='");
        assertThat(service.delete().apply(request), is(startsWith(exp)));
    }

    @Test
    public void whenUncorrectedIdThenUNF() {
        UserServlet service = new UserServlet();
        addUser(service);
        when(request.getParameter(ID)).thenReturn("test");
        assertThat(service.findById().apply(request), is(UNF));
    }

    @Test
    public void whenFindAllThenAllUsersPrint() {
        UserServlet service = new UserServlet();
        addUser(service);
        addUser(service);
        assertThat(service.findAll().apply(request), allOf(startsWith("User{id='"), containsString(LS + "User{id='")));
    }

    @Test
    public void whenUpdateThenUserUpdated() {
        UserServlet service = new UserServlet();
        addUser(service);
        when(request.getParameter(UPDATE)).thenReturn("1");
        when(request.getParameter(NAME)).thenReturn("1");
        when(request.getParameter(LOGIN)).thenReturn("1");
        when(request.getParameter(EMAIL)).thenReturn("1");
        when(request.getParameter(ID)).thenReturn("1");
        String mask = Joiner.on(" ").join(UPDATE_TO, "User{id='1");
        assertThat(service.update().apply(request), is(startsWith(mask)));
    }
}