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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mapper.WpPostmetaMapper;
import com.example.model.WpPostmeta;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private WpPostmetaMapper postmetaMapper;

	@Bean
	public Job job1(Step step1) throws Exception {
		return jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer()).start(step1).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				WpPostmeta postmeta = postmetaMapper.selectByPrimaryKey(new Long(1));
				log.debug("aaaaaaaaaaaaaaaaaaa");
				return null;
			}
		}).build();
	}
	@Bean
	public Job job2(Step step2) throws Exception {
		return jobBuilderFactory.get("job2").incrementer(new RunIdIncrementer()).start(step2).build();
	}

	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2").tasklet(new Tasklet() {
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {
				WpPostmeta postmeta = postmetaMapper.selectByPrimaryKey(new Long(1));
				log.debug("bbbbbbbbbbbbbb");
				return null;
			}
		}).build();
	}
}
