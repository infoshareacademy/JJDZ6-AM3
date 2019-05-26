package servlets.Task;

import api.domain.Task;
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

@WebServlet("/tasks")
public class TaskListServlet extends HttpServlet {

    @Inject
    TaskRepository taskRepository;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Task> tasks = taskRepository.findAll();


        Map<String, Object> model = new HashMap<>();
        model.put("tasks", tasks);

        Template template = templateProvider.getTemplate(getServletContext(), "tasks.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}

