package servlets.Task;

import api.domain.Project;
import api.domain.Status;
import api.domain.Task;
import api.domain.Type;
import api.domain.User;
import api.service.ProjectService;
import api.service.TaskService;
import api.service.UserService;
import config.TemplateProvider;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/task-edit")
public class EditTaskServlet extends HttpServlet {

    @Inject
    TaskService taskService;
    @Inject
    ProjectService projectService;
    @Inject
    UserService userService;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));
        Task task = taskService.findById(id);

        List<Type> types = Stream.of(Type.values()).collect(Collectors.toList());
        List<Status> statuses = Stream.of(Status.values()).collect(Collectors.toList());
        List<Project> projects = projectService.findAll();
        List<User> users = userService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("task", task);
        model.put("types", types);
        model.put("statuses", statuses);
        model.put("projects", projects);
        model.put("users", users);

        Template template = templateProvider.getTemplate(getServletContext(), "create-task.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
