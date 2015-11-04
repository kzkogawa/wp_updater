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
public class EronetJob {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	@Qualifier("EronetService")
	private ICrawlService crawlService;

	@Bean
	public Job eronet() throws Exception {
		return jobBuilderFactory.get("eronet").incrementer(new RunIdIncrementer()).start(eronetStep1()).next(eronetStep2()).next(eronetStep3()).build();
	}

	@Bean(name = "eronetStep1")
	public Step eronetStep1() {
		return stepBuilderFactory.get("eronetStep1").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				log.info("eronetStep1");
				crawlService.doCrawl("http://xxeronetxx.info/ranking1day.html");
				return RepeatStatus.FINISHED;
			}
		}).allowStartIfComplete(true).build();
	}

	@Bean(name = "eronetStep2")
	public Step eronetStep2() {
		return stepBuilderFactory.get("eronetStep2").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				log.info("eronetStep2");
				crawlService.doCrawl("http://xxeronetxx.info/jk001.html");
				return RepeatStatus.FINISHED;
			}
		}).allowStartIfComplete(true).build();
	}

	@Bean(name = "eronetStep3")
	public Step eronetStep3() {
		return stepBuilderFactory.get("eronetStep3").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				log.info("eronetStep3");
				crawlService.doCrawl("http://xxeronetxx.info/kn001.html");
				return RepeatStatus.FINISHED;
			}
		}).allowStartIfComplete(true).build();
	}
}
