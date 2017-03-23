import io.kloudwork.util.PostParser;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class PostParserTest {

    @Test
    public void it_parses_a_post_request() {
        final String postBody = "----------------------------478327912681872670487948\n" +
                "Content-Disposition: form-data; name=\"bar\"\r\n\r" +
                "\n" +
                "foo\r\n" +
                "----------------------------478327912681872670487948\n" +
                "Content-Disposition: form-data; name=\"another\"\r\n\r" +
                "\n" +
                "param\r\n" +
                "----------------------------478327912681872670487948--\n";

        Map<String, String> postParams = PostParser.parse(postBody);

        assertEquals(2,postParams.size());
        assertEquals("foo", postParams.get("bar"));
        assertEquals("param", postParams.get("another"));
    }

    @Test
    public void it_parses_an_empty_post_request() {
        final String postBody = "";

        Map<String, String> postParams = PostParser.parse(postBody);

        assertEquals(0,postParams.size());
    }

    @Test
    public void it_parses_a_post_request_with_an_email_in_it() {
        final String postBody = "----------------------------478327912681872670487948\n" +
                "Content-Disposition: form-data; name=\"name\"\r\n\r" +
                "\n" +
                "fred\r\n" +
                "----------------------------478327912681872670487948\n" +
                "Content-Disposition: form-data; name=\"mail\"\r\n\r" +
                "\n" +
                "fred@kloud.com\r\n" +
                "----------------------------478327912681872670487948--\n";

        Map<String, String> postParams = PostParser.parse(postBody);

        assertEquals(2,postParams.size());
        assertEquals("fred", postParams.get("name"));
        assertEquals("fred@kloud.com", postParams.get("mail"));
    }
}
