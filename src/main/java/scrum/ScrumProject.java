package scrum;

import com.google.gson.reflect.TypeToken;
import utils.FileUtils;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Marek on 24.03.2019.
 */
public class ScrumProject {

    private Scanner scanner = new Scanner(System.in);
    private static final String PROJECT_SCRUM_JSON_FILE = Paths.get("Scrum.json").toString();
    private static final Type collectionType = new TypeToken<List<Project>>() {}.getType();

    public void createScrumProject() {

        List<Project> projects = FileUtils.readListFromJsonFile(PROJECT_SCRUM_JSON_FILE, collectionType);
        Project project = new Project();

        //Project name
        System.out.println("Please type project full name:");
        project.setFullNameOfProject(scanner.nextLine());

        //Key generator
        String key = KeyGen.getKey();
        System.out.println("Key:");
        System.out.println(key);

        //Product Owner
        System.out.println("Please type Product Owner full name");
        project.setFullNameOfProductOwner(scanner.nextLine());

        //list of developers
        ReadModel readModel = new ReadModel();
        readModel.getDeveloper();
        project.setDeveloperName(scanner.nextLine());
        project.setKey(key);

        projects.add(project);
        FileUtils.saveListToJsonFile(projects, PROJECT_SCRUM_JSON_FILE);
    }
}
