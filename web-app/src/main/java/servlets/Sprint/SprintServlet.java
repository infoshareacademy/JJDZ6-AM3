package servlets.Sprint;

import api.domain.Sprint;
import api.service.SprintService;
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
import java.util.Map;

@WebServlet("/sprint")
public class SprintServlet extends HttpServlet {

    @Inject
    SprintService taskService;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        Sprint task = taskService.findById(id);

        Map<String, Object> model = new HashMap<>();
        model.put("task", task);

        Template template = templateProvider.getTemplate(getServletContext(), "sprint.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}

