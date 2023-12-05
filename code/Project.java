import java.util.LinkedList;
import java.io.*;

enum RequirementCategory implements Serializable{
    UNASSIGNED,
    USER,
    SYSTEM,
    BUSINESS,
    REGULATORY,
    INTERFACE,
    DESIGN;

    public static RequirementCategory fromIndex(int i) {
        RequirementCategory c = UNASSIGNED;
        switch(i) {
            case 1: c = USER;
                    break;
            case 2: c = SYSTEM;
                    break;
            case 3: c = BUSINESS;
                    break;
            case 4: c = REGULATORY;
                    break;
            case 5: c = INTERFACE;
                    break;
            case 6: c = DESIGN; 
                    break;
        }
        return c;
    }

    public static int fromEnum(RequirementCategory c) {
        int i= 0;
        switch(c) {
            case USER: i = 1;
                       break;
            case SYSTEM: i = 2;
                         break;
            case BUSINESS: i = 3;
                           break;
            case REGULATORY: i = 4;
                             break;
            case INTERFACE: i = 5;
                            break;
            case DESIGN: i = 6;
                         break;
        }
        return i;
    }

    public static String toStringFromIndex(int i) {
        String s = "UNASSIGNED";
        switch(i) {
            case 1: s = "USER";
                    break;
            case 2: s = "SYSTEM";
                    break;
            case 3: s = "BUSINESS";
                    break;
            case 4: s = "REGULATORY";
                    break;
            case 5: s = "INTERFACE";
                    break;
            case 6: s = "DESIGN"; 
                    break;
        }
        return s;
    }
}

class Requirement implements Serializable{
    public String text;
    boolean isFunctional;
    RequirementCategory category;

    Requirement() {
        text = new String("Requirement text");
        isFunctional = false;
        category = RequirementCategory.UNASSIGNED;
    }

    @Override
    public String toString() {
        String result = "";
        if(!isFunctional) {
            result+="Non functional ";
        }
        else {
            result+="Functional ";
        }
        result += 
            "Requirement: "+text+"\n";
        result += "Category: ";
        result +=
        RequirementCategory.toStringFromIndex(RequirementCategory.fromEnum(category));
        return result;
    }
}

class Task implements Serializable{
    public String description;
    public String doneBy;

    public int year;
    public int month;
    public int day;

    public static int numberOfYears=2060-2023;
    public static int numberOfMonths=12;
    public static int numberOfDays=31;

    Task(Project project) {
        description = "Task description";
        doneBy = project.members.get(0);
        year=2023;month=1;day=1;
    }

    @Override
    public String toString() {
        String result="Task: ";
        result+=description+"\n";
        result+="Done by: "+doneBy;
        result+=" on ";
        result+=+month+"/"+day+"/"+year+"";
        return result;
    }
}

public class Project implements Serializable {
    String project_name;
    String project_manager;
    String project_description;

    LinkedList<String> members;
    LinkedList<Requirement> requirements;
    LinkedList<Task> tasks;
    static String default_member_name = "Member Name";

    Project(String name, String manager, String description) {
        project_name = name;
        project_manager = manager;
        project_description = description;
        members = new LinkedList<String>();
        AddMember();
        requirements = new LinkedList<Requirement>();
        AddRequirement();
        tasks = new LinkedList<Task>();
        AddTask();
    }

    public void AddTask() {
        tasks.add(new Task(this));
    }

    public void RemoveTask(int i) {
        tasks.remove(i);
    }

    public void AddRequirement() {
        requirements.add(new Requirement());
    }
    public void RemoveRequirement(int i) {
        requirements.remove(i);
    }

    public void RemoveMember(int i) {
        members.remove(i);
    }

    public void AddMember() {
        members.add(default_member_name);
    }
    public void AddMember(String name) {
        members.add(name);
    }

    @Override
    public String toString() {
        String result = ("Project Name: "+project_name+"\n" +
                         "Project Manager: "+project_manager+"\n" +
                         "Project Description: "+project_description+"\n" +
                         "Members: "+ "\n");
        for(int i=0;i<members.size();i++) {
            result += (members.get(i) + "\n");
        }
        result+=("Requirements: "+ "\n");
        for(int i=0;i<requirements.size();i++) {
            result += (requirements.get(i).toString() + "\n");
        }
        return result;
    }
}
