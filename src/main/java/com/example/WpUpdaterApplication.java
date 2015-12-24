package com.example;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WpUpdaterApplication {
	private static final Logger log = LoggerFactory.getLogger(WpUpdaterApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(WpUpdaterApplication.class, args);
		JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
		try {
			JobExecution eronet = jobLauncher.run(applicationContext.getBean("eronet", Job.class), new JobParameters());
			log.debug(ToStringBuilder.reflectionToString(eronet));
		} catch (Exception e) {
			log.error("eronet", e);
		}
		try {
			JobExecution fc2 = jobLauncher.run(applicationContext.getBean("fc2", Job.class), new JobParameters());
			log.debug(ToStringBuilder.reflectionToString(fc2));
		} catch (Exception e) {
			log.error("fc2", e);
		}
		try {
			JobExecution thisAv = jobLauncher.run(applicationContext.getBean("thisAv", Job.class), new JobParameters());
			log.debug(ToStringBuilder.reflectionToString(thisAv));
		} catch (Exception e) {
			log.error("thisAv", e);
		}
		try {
			JobExecution xvideoJpCom = jobLauncher.run(applicationContext.getBean("xvideoJpCom", Job.class), new JobParameters());
			log.debug(ToStringBuilder.reflectionToString(xvideoJpCom));
		} catch (Exception e) {
			log.error("xvideoJpCom", e);
		}
		try {
			JobExecution shareMovie = jobLauncher.run(applicationContext.getBean("shareMovie", Job.class), new JobParameters());
			log.debug(ToStringBuilder.reflectionToString(shareMovie));
		} catch (Exception e) {
			log.error("shareMovie", e);
		}
		
		
		try {
			JobExecution finish = jobLauncher.run(applicationContext.getBean("finish", Job.class), new JobParameters());
			log.debug(ToStringBuilder.reflectionToString(finish));
		} catch (Exception e) {
			log.error("xvideoJpCom", e);
		}

	}
}
