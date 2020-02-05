package ru.job4j.servlets.userservlet;

import com.google.common.base.Joiner;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ValidateServiceTest {
    private static final String UPDATE = "update";
    private static final String OLD_USER = "Old User:";
    private static final String UPDATE_TO = "Update to:";
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

    private void addUser(ValidateService service) {
        when(request.getParameter(NAME)).thenReturn("Test");
        when(request.getParameter(LOGIN)).thenReturn("Test");
        when(request.getParameter(EMAIL)).thenReturn("Test");
        Map<String, String[]> map = new HashMap<>();
        map.put(ID, new String[]{""});
        map.put(NAME, new String[]{""});
        map.put(LOGIN, new String[]{""});
        map.put(EMAIL, new String[]{""});
        when(request.getParameterMap()).thenReturn(map);
        service.add(request);
    }

    @Test
    public void whenAddThenAddUser() {
        ValidateService service = ValidateService.getInstance();
        addUser(service);
        String exp = Joiner.on(" ").join(ANU, "User{id='");
        assertTrue(service.add(request).startsWith(exp));
    }

    @Test
    public void whenDeleteThenDeleteUser() {
        ValidateService service = ValidateService.getInstance();
        addUser(service);
        addUser(service);
        when(request.getParameter(DEL)).thenReturn("1");
        String exp = Joiner.on(" ").join(DELETE, "User{id='");
        assertTrue(service.delete(request).startsWith(exp));
    }

    @Test
    public void whenUncorrectedIdThenUNF() {
        ValidateService service = ValidateService.getInstance();
        addUser(service);
        when(request.getParameter(ID)).thenReturn("test");
        assertThat(service.findById(request), is(UNF));
    }

    @Test
    public void whenFindAllThenAllUsersPrint() {
        ValidateService service = ValidateService.getInstance();
        addUser(service);
        addUser(service);
        String mask = Joiner.on(LS).join("User\\{id='(.*)}", "User\\{id='(.*)}");
        assertTrue(service.findAll(request).matches(mask));
    }

    @Test
    public void whenUpdateThenUserUpdated() {
        ValidateService service = ValidateService.getInstance();
        addUser(service);
        when(request.getParameter(UPDATE)).thenReturn("1");
        when(request.getParameter(NAME)).thenReturn("1");
        when(request.getParameter(LOGIN)).thenReturn("1");
        when(request.getParameter(EMAIL)).thenReturn("1");
        when(request.getParameter(ID)).thenReturn("1");
        String mask =  Joiner.on(LS).join(OLD_USER, "User\\{id='(.*)}", UPDATE_TO, "User\\{id='(.*)}");
        assertTrue(service.update(request).matches(mask));
        String newMask = "User\\{id='(.*)name='1'(.*)}";
        assertTrue(service.findById(request).matches(newMask));
    }
}