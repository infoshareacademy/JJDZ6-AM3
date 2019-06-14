package servlets.Project;

import api.domain.Project;
import api.domain.Task;
import api.repository.ProjectRepository;
import api.repository.TaskRepository;
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

/**
 * Created by Marek on 11.06.2019.
 */
@WebServlet("/projects")
public class ProjectListServlet extends HttpServlet {

    @Inject
    ProjectRepository projectRepository;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Project> projects = projectRepository.findAll();


        Map<String, Object> model = new HashMap<>();
        model.put("projects", projects);

        Template template = templateProvider.getTemplate(getServletContext(), "projects.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

}
