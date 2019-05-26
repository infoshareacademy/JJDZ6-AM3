package servlets.Task;

import api.domain.Project;
import api.domain.State;
import api.domain.Type;
import api.repository.ProjectRepository;
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

@WebServlet("/create-task")
public class CreateTaskServlet extends HttpServlet {

    @Inject
    TemplateProvider templateProvider;
    @Inject
    ProjectRepository projectRepository;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Type> types = Stream.of(Type.values()).collect(Collectors.toList());
        List<State> states = Stream.of(State.values()).collect(Collectors.toList());
        List<Project> projects = projectRepository.findAll();


        Map<String, Object> model = new HashMap<>();

        model.put("types", types);
        model.put("states", states);
        model.put("projects", projects);

        Template template = templateProvider.getTemplate(getServletContext(), "create-task.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
