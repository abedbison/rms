import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import com.mitrais.rms.model.User;
import com.mitrais.rms.service.UserService;
import com.mitrais.rms.service.impl.UserServiceImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class UserTest {

    static UserService userService;

    @BeforeClass
    public static void doBeforeClass() {
        UserDao userDao = UserDaoImpl.getInstance();
        userDao.truncate();
        userDao.save(new User(Long.valueOf(0), "JohnDoe", "Password"));
        userDao.save(new User(Long.valueOf(0), "JaneDoe", "Password"));
        userDao.save(new User(Long.valueOf(0), "JackDoe", "Password"));
        userService = UserServiceImpl.getInstance();
    }

    @AfterClass
    public static void doAfterClass() {
        UserDao userDao = UserDaoImpl.getInstance();
        userDao.truncate();
    }

    public UserTest() { }

    @Test
    public void testSelect() {
        assertTrue(userService.findByUserName("JohnDoe").isPresent());
    }

    @Test
    public void testInsert() {
        User user = new User(Long.valueOf(0), "BabyHuey", "A Duck");
        userService.save(user);
        assertTrue(userService.findByUserName("BabyHuey").isPresent());
    }

    @Test
    public void testUpdate() {
        User user = userService.findByUserName("JaneDoe").get();
        user.setUserName("BabyDuey");
        userService.update(user);
        assertTrue(userService.findByUserName("BabyDuey").isPresent());
    }

    @Test
    public void testDelete() {
        User user = userService.findByUserName("JackDoe").get();
        userService.delete(user);
        assertFalse(userService.findByUserName("JackDoe").isPresent());

    }

}
