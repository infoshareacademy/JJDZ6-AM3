package servlets.Task;

import api.domain.State;
import api.domain.Task;
import api.domain.Type;
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

@WebServlet("/task-edit")
public class EditTaskServlet extends HttpServlet {

    @Inject
    TaskRepository taskRepository;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        Task task = taskRepository.findById(id);

        List<Type> types = Stream.of(Type.values()).collect(Collectors.toList());
        List<State> states = Stream.of(State.values()).collect(Collectors.toList());

        Map<String, Object> model = new HashMap<>();
        model.put("task", task);
        model.put("types", types);
        model.put("states", states);

        Template template = templateProvider.getTemplate(getServletContext(), "create-task.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
