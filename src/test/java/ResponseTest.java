import org.junit.jupiter.api.Test;
import pl.serweron.serweronLib.utils.Response;

import static org.junit.jupiter.api.Assertions.*;

class ResponseTest {

    @Test
    void testSuccessResponse() {
        Response<String> res = Response.success("ok");
        assertTrue(res.isSuccess());
        assertEquals("ok", res.getData());
        assertNull(res.getErrorMessage());
    }

    @Test
    void testFailureResponse() {
        Response<Object> res = Response.failure("fail");
        assertTrue(res.isFailure());
        assertEquals("fail", res.getErrorMessage());
        assertNull(res.getData());
    }

    @Test
    void testNotImplementedResponse() {
        Response<Object> res = Response.notImplemented();
        assertTrue(res.isNotImplemented());
        assertEquals("Not implemented", res.getErrorMessage());
    }
}
