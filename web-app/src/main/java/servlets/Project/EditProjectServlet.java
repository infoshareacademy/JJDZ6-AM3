package servlets.Project;

import api.domain.Project;
import api.domain.Role;
import api.domain.State;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Marek on 11.06.2019.
 */
@WebServlet("/project-edit")
public class EditProjectServlet extends HttpServlet {

    @Inject
    ProjectRepository projectRepository;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Project project = projectRepository.findById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("projects", project);

        Template template = templateProvider.getTemplate(getServletContext(), "create-project.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
