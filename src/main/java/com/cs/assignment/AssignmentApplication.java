package com.cs.assignment;

import com.cs.assignment.dto.ServerLogDto;
import com.cs.assignment.model.ServerLog;
import com.cs.assignment.parser.LogFileParser;
import com.cs.assignment.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
public class AssignmentApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(AssignmentApplication.class, args);
		LogService service = applicationContext.getBean(LogService.class);

		List<ServerLogDto> serverLogDtoList = LogFileParser.parseFile(args[0]);
		service.saveServerLogs(serverLogDtoList);
	}
}
