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

import com.example.service.ICrawlService;

@Configuration
@EnableBatchProcessing
public class XvideoJpComJob {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	@Qualifier("XvideoJpComService")
	private ICrawlService crawlService;

	@Bean
	public Job xvideoJpCom() throws Exception {
		return jobBuilderFactory.get("xvideoJpCom").incrementer(new RunIdIncrementer()).start(xvideoJpComStep1()).build();
	}

	@Bean
	public Step xvideoJpComStep1() {
		return stepBuilderFactory.get("xvideoJpComStep1").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				log.info("xvideoJpComStep1");
				crawlService.doCrawl("http://xvideos-field.com/rr_xvjp_js/get_site_ranking.js");
				return null;
			}
		}).build();
	}

}
