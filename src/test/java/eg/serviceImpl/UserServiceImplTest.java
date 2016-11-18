package eg.serviceImpl;

import eg.exceptions.UserNotFound;
import eg.models.User;
import eg.models.enums.Access;
import eg.service.UserService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {
    UserService userService = new UserServiceImpl();

    @Test
    public void testEnter() throws Exception {
//        User user = new User(0, "TestUser", "TestLogin", "TestPassword", Access.CANDIDATE.name());
//        userService.addUser(user.getName(), user.getLogin(), user.getPassword(), user.getAccess().name());
        try{
            userService.enter("RandomLogin","RandomPassword"); //Login & password wouldn't match
            throw new Exception();
        }catch (UserNotFound ex){

        }

        try{
            userService.enter("log","pas"); //Too short login & password
            throw new Exception();
        }catch (UserNotFound ex){

        }

//        userService.deleteById(userService.getUserByName(user.getName()).getId());
//
//        User testUser = userService.getUserByName(user.getName());
//        Assert.assertNull(testUser);
    }
}