package org.jboss.as.quickstarts.tasksrs.service;

import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.jboss.as.quickstarts.tasksrs.category.UnitTest;
import org.jboss.as.quickstarts.tasksrs.model.Task;
import org.jboss.as.quickstarts.tasksrs.model.TaskDao;
import org.jboss.as.quickstarts.tasksrs.model.User;
import org.jboss.as.quickstarts.tasksrs.model.UserDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

@RunWith(MockitoJUnitRunner.class)
@Category(UnitTest.class)
public class TaskResourceTest {
    @InjectMocks
    private TaskResource taskResource;

    @Mock
    private UserDao userDao;

    @Mock
    private TaskDao taskDao;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private UriInfo uriInfo;

    @Before
    public void setupMocks() throws URISyntaxException {
        Principal principal = mock(Principal.class);
        when(securityContext.getUserPrincipal()).thenReturn(principal);
        when(principal.getName()).thenReturn("sally");


        // mock response
        URI uri = new URI("/response");
        UriBuilder uriBuilder = mock(UriBuilder.class);
        when(uriInfo.getAbsolutePath()).thenReturn(uri);
        when(uriInfo.getAbsolutePathBuilder()).thenReturn(uriBuilder);
        when(uriBuilder.replacePath(anyString())).thenReturn(uriBuilder);
        when(uriBuilder.build()).thenReturn(uri);

        // set id on new tasks
        Mockito.doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Task task = (Task)invocation.getArgument(1);
                task.setId(System.currentTimeMillis());
                return null;
            }
        }).when(taskDao).createTask(any(User.class), any(Task.class));
    }


    @Test
    public void createTaskWithNewUser() {
        taskResource.createTask(uriInfo, securityContext, "run a demo!");

        verify(userDao).createUser(any(User.class));
        verify(taskDao).createTask(any(User.class), any(Task.class));
    }


    @Test
    public void getTaskByIdWithNonExistingUser() {
        when(taskDao.getAll(any(User.class))).thenReturn(asList(newTask(123L)));

        taskResource.getTaskById(securityContext, 123L);

        verify(userDao).getForUsername("sally");
        verify(userDao).createUser(any(User.class));
        verify(taskDao).getAll(any(User.class));
    }


    @Test
    public void deleteTaskById() {
        when(taskDao.getAll(any(User.class))).thenReturn(asList(newTask(123L)));

        taskResource.deleteTaskById(securityContext, 123L);

        verify(userDao).getForUsername("sally");
        verify(userDao).createUser(any(User.class));
        verify(taskDao).getAll(any(User.class));
        verify(taskDao).deleteTask(any(Task.class));

    }

    private Task newTask(Long id) {
        Task task = new Task("Task 1");
        task.setId(id);
        return task;
    }
}
