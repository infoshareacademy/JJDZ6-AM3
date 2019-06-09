package servlets.Sprint;

import api.domain.Project;
import api.domain.Sprint;
import api.domain.User;
import api.service.ProjectService;
import api.service.SprintService;
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

@WebServlet("/sprint-edit")
public class EditSprintServlet extends HttpServlet {

    @Inject
    SprintService sprintService;
    @Inject
    ProjectService projectService;
    @Inject
    UserService userService;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Sprint sprint = sprintService.findById(id);

        List<Project> projects = projectService.findAll();
        List<User> users = userService.findAll();

        Map<String, Object> model = new HashMap<>();
        model.put("sprint", sprint);
        model.put("projects", projects);
        model.put("users", users);

        Template template = templateProvider.getTemplate(getServletContext(), "create-sprint.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
