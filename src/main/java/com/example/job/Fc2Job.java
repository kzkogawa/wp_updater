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
public class Fc2Job {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	@Qualifier("Fc2Service")
	private ICrawlService crawlService;

	@Bean
	public Job fc2() throws Exception {
		return jobBuilderFactory.get("fc2").incrementer(new RunIdIncrementer()).start(fc2Step1()).build();
	}

	@Bean
	public Step fc2Step1() {
		return stepBuilderFactory.get("fc2Step1").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				log.info("fc2Step1");
				crawlService.doCrawl("http://video.fc2.com/a/feed_ranking.php?m=week");
				return null;
			}
		}).build();
	}

}
