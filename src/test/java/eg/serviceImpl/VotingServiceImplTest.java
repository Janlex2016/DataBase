package eg.serviceImpl;

import eg.models.User;
import eg.models.Voting;
import eg.models.enums.Access;
import eg.service.UserService;
import eg.service.VotingService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class VotingServiceImplTest {

    VotingService votingService = ServiceFactory.getVotingService();
    UserService userService = ServiceFactory.getUserService();

    @Test
    public void testAddCandidateToVoting() throws Exception {
        Voting testVoting = new Voting(0, "testTitle");

        User testCandidate = new User(0, "testName", "testLogin", "testPassword", Access.CANDIDATE.name());

        votingService.add(testVoting.getTitle());
        userService.addCandidate(testCandidate.getName(), testCandidate.getAccess().name());

        int testCandidateId = userService.getCandidateByName(testCandidate.getName()).getId();
        int testVotingId = votingService.getByName(testVoting.getTitle()).getId();
        votingService.addCandidateToVoting(testCandidateId, testVotingId);

        String[] candidateArray = votingService.getCandidatesDedicatedToVoting(testVoting.getTitle());

        boolean candidateExists = false;

        for(String str:candidateArray){
            if(str.equals(testCandidate.getName())) candidateExists = true;
        }

        Assert.assertTrue(candidateExists);

        votingService.deleteById(testVotingId);
        userService.deleteCandidateById(testCandidateId);


    }

}