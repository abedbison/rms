import com.mitrais.rms.util.CommonHelper;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertThat;

/**
 *
 * @author Abednego_S810
 */
public class CommonHelperTest {
    
    public CommonHelperTest() { }
    
    @Test
    public void testParseLong() throws SQLException {
        assertThat(Long.valueOf(891134), Is.is(CommonHelper.parseLong("891134")));
    }
    
    @Test
    public void testParseLongError() throws SQLException {
        assertThat(Long.valueOf(-1), Is.is(CommonHelper.parseLong("ABC")));
    }
    
    @Test
    public void testParseLongEmptyString() throws SQLException {
        assertThat(Long.valueOf(0), Is.is(CommonHelper.parseLong("")));
    }
    
    @Test
    public void testParseLongNull() throws SQLException {
        assertThat(Long.valueOf(0), Is.is(CommonHelper.parseLong(null)));
    }
    
}
