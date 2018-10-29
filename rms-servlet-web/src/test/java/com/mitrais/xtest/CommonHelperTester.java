package com.mitrais.xtest;

import com.mitrais.rms.util.CommonHelper;
import java.sql.SQLException;
import org.hamcrest.core.Is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author Abednego_S810
 */
public class CommonHelperTester {
    
    public CommonHelperTester() { }
    
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
