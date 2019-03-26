package sprint;

import backlog.Backlog;
import backlog.BacklogController;
import org.apache.commons.lang3.StringUtils;
import userstory.UserStory;
import utils.FileUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Sprint {
    String sprintName;
    LocalDate sprintEndDate;
    BacklogController backlogController = new BacklogController();
    List<String> newSprintList = new ArrayList<>();
    UserStory userStory = new UserStory();


    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public LocalDate getSprintEndDate() {
        return sprintEndDate;
    }

    public void setSprintEndDate(LocalDate sprintEndDate) {
        this.sprintEndDate = sprintEndDate;
    }

    public void newSprint() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Create a new Sprint");
        System.out.println("Please type Sprint name");
        setSprintName(scanner.nextLine());
        System.out.println("Please type end of date the Sprint, for example 11-09-2011");
        boolean done = false;
        while (!done) {
            try {
                DateTimeFormatter intDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String date = scanner.nextLine();
                LocalDate date1 = LocalDate.parse(date, intDate);
                setSprintEndDate(date1);
                done = true;
            } catch (Exception e) {
                System.out.println("Incorrect date, type again date ");
            }
        }

        System.out.println("New Sprint " + getSprintName() + " should reach the target in date " + getSprintEndDate());

        System.out.println("Sprint Backlog to add");
        System.out.println("Co to da " + toString());


        System.out.println(backlogController.showBacklogTasks(23));


    }

}


/*
Product Owner > możliwość utworzenia nowego Sprintu
    - dodania do niego zadań z Backlogu
    - ustawić datę jego zakończenia

Po rozpoczęciu Sprintu
    - wszystkie zadania w Sprincie powinny automatycznie zostać zaktualizowane o numer Sprintu.
 */