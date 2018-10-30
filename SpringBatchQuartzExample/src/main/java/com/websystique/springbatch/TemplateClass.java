package com.websystique.springbatch;

import java.io.Serializable;

import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.configuration.JobLocator;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;

import com.websystique.springbatch.quartz.ApplicationContextUtil;

public abstract class TemplateClass implements Serializable, Job{
	
	private static final long serialVersionUID = -1187645504737071623L;
	
	private String jobName;
	
	private JobLocator jobLocator;
	
	private JobLauncher jobLauncher;
	
	public abstract void performJob();
	
	private SqlSession sqlSession = null;
	
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setJobLocator(JobLocator jobLocator) {
		this.jobLocator = jobLocator;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ApplicationContext applicationContext = ApplicationContextUtil.getApplicationContext();
		sqlSession = applicationContext.getBean("sqlSession", SqlSession.class);
		performJob();
	}
	
	public Object getMapper(Class<?> clz) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return this.sqlSession.getMapper(clz);
	}
	
}
