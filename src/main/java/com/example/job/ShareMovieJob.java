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
public class ShareMovieJob {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	@Qualifier("ShareMovieService")
	private ICrawlService crawlService;

	@Bean
	public Job shareMovie() throws Exception {
		return jobBuilderFactory.get("shareMovie").incrementer(new RunIdIncrementer()).start(shareMovieStep1()).build();
	}

	@Bean(name = "shareMovieStep1")
	public Step shareMovieStep1() {
		return stepBuilderFactory.get("shareMovieStep1").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				log.info("shareMovieStep1");
				crawlService.doCrawl("http://smv.to/list/ranking");
				return RepeatStatus.FINISHED;
			}
		}).allowStartIfComplete(true).build();
	}

}
