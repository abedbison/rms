import com.mitrais.rms.util.CommonHelper;
import org.hamcrest.core.Is;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 *
 * @author Abednego_S810
 */
public class CommonHelperTest {
    
    public CommonHelperTest() { }
    
    @Test
    public void testParseLong() {
        assertThat(Long.valueOf(891134), Is.is(CommonHelper.parseLong("891134")));
    }
    
    @Test
    public void testParseLongError() {
        assertThat(Long.valueOf(-1), Is.is(CommonHelper.parseLong("ABC")));
    }
    
    @Test
    public void testParseLongEmptyString() {
        assertThat(Long.valueOf(0), Is.is(CommonHelper.parseLong("")));
    }
    
    @Test
    public void testParseLongNull() {
        assertThat(Long.valueOf(0), Is.is(CommonHelper.parseLong(null)));
    }
    
}
