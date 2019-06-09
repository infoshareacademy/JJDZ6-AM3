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
import java.util.List;
import java.util.Map;

@WebServlet("/sprints")
public class SprintListServlet extends HttpServlet {

    @Inject
    SprintService sprintService;

    @Inject
    TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<Sprint> sprints = sprintService.findAll();


        Map<String, Object> model = new HashMap<>();
        model.put("sprints", sprints);

        Template template = templateProvider.getTemplate(getServletContext(), "sprints.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}

