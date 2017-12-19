package loan.model;

import loan.db.RequestRepository;
import loan.util.Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author A.Bogoslov
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private RequestRepository requestRepository;

    @MockBean
    private Utils utils;

    @Test
    public void testInsertedSqlRequest() {
        Request gotRequest = requestRepository.findOne(1);

        assertThat(gotRequest.getId()).isEqualTo(1);
        assertThat(gotRequest.getDate().toString()).isEqualTo("2017-12-06");
        assertThat(gotRequest.getSum().doubleValue()).isEqualTo(100000.0);
        assertThat(gotRequest.getDuration()).isEqualTo(6);
    }

    @Test
    public void testAssembledRequest() {
        Date date = new Date(System.currentTimeMillis());

        Request request = new Request();
        request.setDate(date);
        request.setSum(BigDecimal.valueOf(1200000));
        request.setDuration(12);
        BigDecimal charge = utils.calcMonthlyCharge(request);
        request.setMonthlyCharge(charge);

        entityManager.persist(request);
        Request gotRequest = requestRepository.findOne(2);

        assertThat(gotRequest.getId()).isEqualTo(2);
        assertThat(gotRequest.getDate()).isEqualTo(date);
        assertThat(gotRequest.getSum()).isEqualTo(BigDecimal.valueOf(1200000));
        assertThat(gotRequest.getDuration()).isEqualTo(12);
    }
}
