import com.mitrais.rms.dao.DataSourceFactory;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Abednego_S810
 */
public class DaoSourceFactoryTest {

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
    
}
