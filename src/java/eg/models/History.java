package eg.models;

public class History {
    
    private int historyId;
    private int userId;
    private int candidateId;
    private int votionId;

    public History() {
    }

    public History(int id, int candidateId, int votionId, int userId) {
        this.historyId = id;
        this.userId = userId;
        this.candidateId = candidateId;
        this.votionId = votionId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public int getHistoryId() {
        return historyId;
    }

    public int getUserId() {
        return userId;
    }

    public int getVotionId() {
        return votionId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setVotionId(int votionId) {
        this.votionId = votionId;
    }
}