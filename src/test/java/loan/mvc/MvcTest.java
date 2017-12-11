package loan.mvc;

import loan.service.RequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author A.Bogoslov
 */

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RequestService requestService;

    @Test
    public void testIndex() throws Exception {
        mvc.perform(post("/")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

    @Test
    public void testSuccess() throws Exception {
        mvc.perform(get("/success")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }
}
