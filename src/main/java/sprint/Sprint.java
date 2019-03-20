package sprint;

import backlog.BacklogController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;


public class Sprint {
    String sprintName;
    LocalDate sprintEndDate;


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
        BacklogController backlogController = new BacklogController();
        System.out.println("Create a new Sprint");
        System.out.println("Please type Sprint name");
        setSprintName(scanner.nextLine());
        System.out.println("Please type end of date the Sprint for example 11-09-2011");
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

        System.out.println(getSprintName());
        System.out.println(getSprintEndDate());

        System.out.println("Sprint Backlog to add");
        backlogController.showBacklogTasks();

    }


}


/*
Product Owner > możliwość utworzenia nowego Sprintu
    - dodania do niego zadań z Backlogu
    - ustawić datę jego zakończenia

Po rozpoczęciu Sprintu
    - wszystkie zadania w Sprincie powinny automatycznie zostać zaktualizowane o numer Sprintu.
 */