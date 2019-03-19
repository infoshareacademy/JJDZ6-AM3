package sprint;

import backlog.BacklogController;

import java.util.Scanner;

public class Sprint {
    private Scanner scaner = new Scanner(System.in);

    public void newSprint(){
        BacklogController backlogController = new BacklogController();
        System.out.println("JZ6AM-8 (Create a new Sprint)");
        System.out.println("Please type Sprint name");
        String sprintName = scaner.nextLine();
        System.out.println(sprintName);


        backlogController.showBacklogTasks(23);


    }
}



/*
Product Owner > możliwość utworzenia nowego Sprintu
    - dodania do niego zadań z Backlogu
    - ustawić datę jego zakończenia

Po rozpoczęciu Sprintu
    - wszystkie zadania w Sprincie powinny automatycznie zostać zaktualizowane o numer Sprintu.
 */