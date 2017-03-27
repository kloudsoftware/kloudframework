import SpakTestUtil.SparkTestUtil;
import io.kloudwork.MyWebApp;
import spark.Spark;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ExampleTest {

    @org.junit.Test
    public void example() throws Exception {
        assertTrue(true);

        SparkTestUtil sparkTestUtil = new SparkTestUtil(4567);

        new MyWebApp().start();
        Spark.awaitInitialization();

        SparkTestUtil.UrlResponse response =  sparkTestUtil.get("/");

        assertEquals(200, response.status);
    }
}
