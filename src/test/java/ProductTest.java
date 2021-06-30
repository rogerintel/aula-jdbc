import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductTest {

    @Test
    public void test(){

        Product p = new Product("test", BigDecimal.TEN);
        Assert.assertEquals("test", p.getName());
        Assert.assertEquals(BigDecimal.TEN, p.getPrice());

}
}