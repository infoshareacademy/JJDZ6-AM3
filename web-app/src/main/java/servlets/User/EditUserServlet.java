package servlets.User;

import api.domain.Role;
import api.domain.User;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("user-edit")
public class EditUserServlet extends HttpServlet {
    @Inject
    TemplateProvider templateProvider;
    @Inject
    UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long id = Long.valueOf(req.getParameter("id"));

        List<Role> roles = Stream.of(Role.values()).collect(Collectors.toList());
        User user = userService.findById(id);


        Map<String, Object> model = new HashMap<>();

        model.put("roles", roles);
        model.put("user", user);

        Template template = templateProvider.getTemplate(getServletContext(), "create-user.ftlh");

        try {
            template.process(model, resp.getWriter());
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
