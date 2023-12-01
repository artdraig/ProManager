import java.util.LinkedList;

class Requirement {
    public String requirement_text;
    boolean is_functional;

    Requirement() {
        requirement_text = new String("Requirement text");
        is_functional = false;
    }

    @Override
    public String toString() {
        String result = "";
        if(!is_functional) {
            result+="Non functional ";
        }
        else {
            result+="Functional ";
        }
        result += 
            "Requirement: "+requirement_text+"\n";
        return result;
    }
}

public class Project {
    String project_name;
    String project_manager;
    String project_description;

    LinkedList<String> members;
    LinkedList<Requirement> requirements;
    static String default_member_name = "Member Name";

    Project(String name, String manager, String description) {
        project_name = name;
        project_manager = manager;
        project_description = description;
        members = new LinkedList<String>();
        AddMember();
        requirements = new LinkedList<Requirement>();
        AddRequirement();
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
