package quan.example.demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import quan.example.demo.model.Person;

import java.util.List;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            // Truy vấn H2 để kiểm tra dữ liệu đã được ghi
            jdbcTemplate.query("SELECT first_name, last_name FROM PEOPLE",
                            (rs, row) -> new Person(rs.getString(1), rs.getString(2)))
                    .forEach(person -> log.info("Found <" + person + "> in the database."));
        } else {
            log.info("!!! JOB FAILED! Status: " + jobExecution.getStatus());
            // Có thể thêm logic xử lý khi job thất bại
        }
    }
}
