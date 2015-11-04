package com.example.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.service.crawl.ICrawlService;

@Configuration
@EnableBatchProcessing
public class ThisAvJob {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	@Qualifier("ThisAvService")
	private ICrawlService crawlService;

	@Bean
	public Job thisAv() throws Exception {
		return jobBuilderFactory.get("thisAv").incrementer(new RunIdIncrementer()).start(thisAvStep1()).build();
	}

	@Bean(name = "thisAvStep1")
	public Step thisAvStep1() {
		return stepBuilderFactory.get("thisAvStep1").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				log.info("thisAvStep1");
				crawlService.doCrawl("http://www.thisav.com/videos?c=28");
				return null;
			}
		}).build();
	}

}
