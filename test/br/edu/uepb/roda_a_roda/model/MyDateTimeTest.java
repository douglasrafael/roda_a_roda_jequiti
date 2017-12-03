package br.edu.uepb.roda_a_roda.model;

import br.edu.uepb.roda_a_roda.tools.MyDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Teste da class MyDateTime do pacote tools
 * 
 * @author Douglas Rafael
 */
public class MyDateTimeTest {
    
    public MyDateTimeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void toStringDateTimeTest() {
        String date_time = "2016-09-05 02:27:25";
        String format_date = "dd/MM/yyyy hh:mm:ss";
        System.out.println(MyDateTime.toStringDateTime(date_time, format_date));
        Assert.assertEquals("05/09/2016 02:27:25", MyDateTime.toStringDateTime(date_time, format_date));
    }
}
