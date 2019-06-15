package servlets.Sprint;

import api.domain.Sprint;
import api.domain.User;
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

@WebServlet("/create-sprint")
public class CreateSprintServlet extends HttpServlet {

    @Inject
    TemplateProvider templateProvider;
    @Inject
    SprintService sprintService;
    @Inject
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Sprint> projects = sprintService.findAll();
        List<User> users = userService.findAll();


        Map<String, Object> model = new HashMap<>();

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
