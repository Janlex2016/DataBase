package eg.models;

public class History {
    
    private int historyId;
    private int userId;
    private int candidateId;
    private int votingId;

    public History() {
    }

    public History(int id, int candidateId, int votingId, int userId) {
        this.historyId = id;
        this.userId = userId;
        this.candidateId = candidateId;
        this.votingId = votingId;
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

    public int getVotingId() {
        return votingId;
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

    public void setVotingId(int votingId) {
        this.votingId = votingId;
    }
}