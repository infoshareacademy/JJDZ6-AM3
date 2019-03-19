package sprint;

import backlog.BacklogController;

import java.util.Date;
import java.util.Scanner;

public class Sprint {
    String sprintName;
    Date sprintEndDate;


    public Sprint() {
    }

    public Sprint(String sprintName,Date sprintEndDate ) {
        this.sprintName = sprintName;
        this.sprintEndDate = sprintEndDate;

    }



    public String getSprintName() {
        return sprintName;
    }

    public void setSprintName(String sprintName) {
        this.sprintName = sprintName;
    }

    public Date getSprintEndDate() {
        return sprintEndDate;
    }

    public void setSprintEndDate(Date sprintEndDate) {
        this.sprintEndDate = sprintEndDate;
    }

    //    private Scanner scaner = new Scanner(System.in);
//
//    public void newSprint(){
//        BacklogController backlogController = new BacklogController();
//        System.out.println("JZ6AM-8 (Create a new Sprint)");
//        System.out.println("Please type Sprint name");
//        String setSprintName = scaner.nextLine();
//        System.out.println(getSprintName());
//
//
//        backlogController.showBacklogTasks(23); // 23 = Tree menu choice 2 -> 3
//
//
//    }
    @Override
    public String toString() {
        return "{" + "sprint='" + sprintName + '\'' + ", End date='" + sprintEndDate + '}';
            }

}



/*
Product Owner > możliwość utworzenia nowego Sprintu
    - dodania do niego zadań z Backlogu
    - ustawić datę jego zakończenia

Po rozpoczęciu Sprintu
    - wszystkie zadania w Sprincie powinny automatycznie zostać zaktualizowane o numer Sprintu.
 */