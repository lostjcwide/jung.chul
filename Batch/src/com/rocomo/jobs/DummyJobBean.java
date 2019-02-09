package com.rocomo.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.rocomo.tasks.DummyTask;

public class DummyJobBean extends QuartzJobBean {

	private DummyTask dummyTask;

	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println(2);
		dummyTask.print();
	}

	public void setDummyTask(DummyTask dummyTask) {
		this.dummyTask = dummyTask;
	}

}
