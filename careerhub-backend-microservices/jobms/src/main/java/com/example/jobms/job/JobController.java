package com.example.jobms.job;


import com.example.jobms.dto.JobWithCompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/jobs")
public class JobController {
    private final JobService  jobService;

    public JobController (JobService jobService){

        this.jobService =jobService;

    }

    @GetMapping
    public ResponseEntity<List<JobWithCompanyDTO>> findAll(){

        List<JobWithCompanyDTO> jobs = (jobService.findAll());
       return ResponseEntity.ok(jobs);
    }

    @PostMapping
       public ResponseEntity<String> createJob(@RequestBody Job job){

        try {
            jobService.createJob(job);
            return new ResponseEntity<>("Job Added Successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to add job: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long Id){
         Job job = jobService.getJobById(Id);

         if (job == null)
             return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
         return new ResponseEntity<>(job, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
     public ResponseEntity<String> deleteJobById(@PathVariable Long id){

            boolean deleted =  jobService.deleteJobById(id);

            if(deleted)
                return new ResponseEntity<>("Job Deleted Successfully",HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }


        @PutMapping("/{id}")
      public ResponseEntity<String> updateJob(@PathVariable  Long id, @RequestBody Job updatedJob){
            boolean updated =  jobService.updateJob(id, updatedJob);

            if (updated)
                return new ResponseEntity<>("Job Updated Successfully",HttpStatus.OK);

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }



}

