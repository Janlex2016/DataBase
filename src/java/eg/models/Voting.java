package eg.models;

import java.util.List;

public class Voting {
    
    private int id;
    private String title;
    private List<User> candidates;

    public Voting() {
    }

    public Voting(int id, String title){
        this.id = id;
        this.title = title;
    }

    public List getCandidates() {
        return candidates;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    
    public void setCandidateIds(List candidateIds) {
        this.candidates = candidateIds;
    }

    public void setId(int date) {
        this.id = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
