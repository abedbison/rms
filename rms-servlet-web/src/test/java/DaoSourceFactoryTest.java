/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mitrais.rms.dao.DataSourceFactory;
import com.mitrais.rms.dao.UserDao;
import com.mitrais.rms.dao.impl.UserDaoImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hamcrest.core.Is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Abednego_S810
 */
public class DaoSourceFactoryTest {
    
    @BeforeClass
    public static void doBeforeClass() { }
    
    public DaoSourceFactoryTest() { }
    
    @Test
    public void testDbProp() {
        Properties prop = DataSourceFactory.getDbProperties();
        assertNotNull(prop.get("databaseName"));
        assertNotNull(prop.get("serverName"));
        assertNotNull(prop.get("port"));
        assertNotNull(prop.get("user"));
        assertNotNull(prop.get("password"));
    }
    
    @Test
    public void testConnection() throws SQLException {
        try(Connection connection = DataSourceFactory.getConnection()) {
            assertNotNull(connection);
        }
    }
    
    @Test
    public void testNotEmptyUser() throws SQLException {
        UserDao userDao = UserDaoImpl.getInstance();
        assertFalse(userDao.findAll().isEmpty());
    }
    
}
