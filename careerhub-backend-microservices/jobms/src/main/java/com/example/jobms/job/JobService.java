package com.example.jobms.job;

import com.example.jobms.dto.JobWithCompanyDTO;

import java.util.List;

public interface JobService {

   List<JobWithCompanyDTO> findAll();
   void createJob(Job job);

   Job getJobById (Long id);

   boolean deleteJobById(Long Id);

   boolean updateJob(Long id, Job updatedJob);
}
