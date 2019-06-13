package servlets.Sprint;

import api.domain.Project;
import api.domain.Sprint;
import api.repository.ProjectRepository;
import api.repository.SprintRepository;
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
@WebServlet("/create-sprint")
public class CreateSprintServlet extends HttpServlet {

    @Inject
    TemplateProvider templateProvider;
    @Inject
    SprintRepository sprintRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Sprint> sprints = sprintRepository.findAll();

        Map<String, Object> model = new HashMap<>();

        model.put("sprints", sprints);

        Template template = templateProvider.getTemplate(getServletContext(), "create-sprint.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
