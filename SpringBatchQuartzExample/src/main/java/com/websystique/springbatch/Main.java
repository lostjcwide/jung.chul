package com.websystique.springbatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import resources.DummyClass;

public class Main {
	
	static final Logger logger = LoggerFactory.getLogger("stdLogger");
	
	@SuppressWarnings({ "unused" })
	public static void main(String[] args) throws SchedulerException, Exception {
//		String jobDetailName = args[0];
		String jobDetailName = "jobName88";
		MDC.put("jobName", jobDetailName);
//		String cronExp = args[1];
		
		logger.debug(">>>>>>>>> debug main start!!!!");
		logger.info(">>>>>>>>> info main start!!!!");
		
		String cronExp = "* */1 * * * ?";
		
		String[] paths = {"root-context.xml", "quartz-context.xml"};
		
		ApplicationContext context = new ClassPathXmlApplicationContext(paths, DummyClass.class);
		
		Map<String, Object> map = new HashMap<String, Object>();
		JobDataMap newJobDataMap = new JobDataMap(map);
		
		JobBuilder jobBuilder = JobBuilder.newJob(CustomJob1.class).storeDurably(true).withIdentity(jobDetailName, jobDetailName);
		JobDetail jobDetail = jobBuilder.build();
		
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setApplicationContext(context);
		schedulerFactoryBean.setAutoStartup(true);
		schedulerFactoryBean.setBeanName(jobDetailName);
		schedulerFactoryBean.setJobDetails(jobDetail);
		Properties quartzProperties = new Properties();
		quartzProperties.put("org.quartz.scheduler.instanceName", "MyClusteredScheduler");
		quartzProperties.put("org.quartz.scheduler.instanceId", "AUTO");
		quartzProperties.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
		quartzProperties.put("org.quartz.threadPool.threadCount", "25");
		quartzProperties.put("org.quartz.threadPool.threadPriority", "5");
		quartzProperties.put("org.quartz.jobStore.misfireThreshold", "60000");
		quartzProperties.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
		quartzProperties.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
		quartzProperties.put("org.quartz.jobStore.useProperties", "false");
		quartzProperties.put("org.quartz.jobStore.dataSource", "myDS");
		quartzProperties.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
		quartzProperties.put("org.quartz.jobStore.isClustered", "true");
		quartzProperties.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
		quartzProperties.put("org.quartz.dataSource.myDS.driver", "org.mariadb.jdbc.Driver");
		quartzProperties.put("org.quartz.dataSource.myDS.URL", "jdbc:mariadb:failover://berith-dev.csxgwp59sajy.ap-northeast-2.rds.amazonaws.com:3306/berith?useUnicode=true&characterEncoding=utf8&seLegacyDatetimeCode=false&serverTimezone=UTC");
		quartzProperties.put("org.quartz.dataSource.myDS.user", "berith");
		quartzProperties.put("org.quartz.dataSource.myDS.password", "berithdev12345");
		schedulerFactoryBean.setQuartzProperties(quartzProperties);
		schedulerFactoryBean.setTriggers(TriggerBuilder.newTrigger().forJob(jobDetailName, jobDetailName).withIdentity(jobDetailName+"Trigger", jobDetailName).withSchedule(CronScheduleBuilder.cronSchedule(cronExp)).build());
		schedulerFactoryBean.afterPropertiesSet();
		schedulerFactoryBean.getScheduler().pauseAll();
		schedulerFactoryBean.start();
		schedulerFactoryBean.getScheduler().resumeAll();
		
//		StdScheduler schedulerFactoryBean = context.getBean("quartzSchedulerFactoryBean3", StdScheduler.class);
//		schedulerFactoryBean.scheduleJob(jobDetailFactoryBean.getObject(), TriggerBuilder.newTrigger().forJob(jobDetailName, jobDetailName).withIdentity(jobDetailName+"Trigger", jobDetailName).withSchedule(CronScheduleBuilder.cronSchedule(cronExp)).build());
	}
}
