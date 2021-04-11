package com.training.spring.jpa.h2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.jpa.h2.model.ApplicantInfo;
import com.bezkoder.spring.jpa.h2.repository.ApplicantRepository;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	ApplicantRepository applicantRepository;

	@GetMapping("/data")
	public ResponseEntity<List<ApplicantInfo>> getAllApplicants(@RequestParam(required = false) String name) {
		try {
			List<ApplicantInfo> applicants = new ArrayList<ApplicantInfo>();

			if (name == null)
				applicantRepository.findAll().forEach(applicants::add);
			else
				applicantRepository.findByNameContaining(name).forEach(applicants::add);

			if (applicants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(applicants, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/data/{id}")
	public ResponseEntity<ApplicantInfo> getApplicantById(@PathVariable("id") long id) {
		Optional<ApplicantInfo> applicantData = applicantRepository.findById(id);

		if (applicantData.isPresent()) {
			return new ResponseEntity<>(applicantData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/alldata")
	public ResponseEntity<ApplicantInfo> createApplicant(@RequestBody ApplicantInfo applicant) {
		try {
			ApplicantInfo applicantInfo = applicantRepository
					.save(new ApplicantInfo(applicant.getName(),applicant.getComments(),applicant.isFlagged(),applicant.getTravelHistory(),applicant.getPassportNum()
							,applicant.getProfession()));
			return new ResponseEntity<>(applicantInfo, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@PutMapping("/alldata/{id}")
	public ResponseEntity<ApplicantInfo> updateApplicant(@PathVariable("id") long id, @RequestBody ApplicantInfo applicant) {
		Optional<ApplicantInfo> data = applicantRepository.findById(id);

		if (data.isPresent()) {
			ApplicantInfo applicantInfo = data.get();
			applicantInfo.setName(applicant.getName());
			applicantInfo.setComments(applicant.getComments());
			applicantInfo.setPassportNum(applicant.getPassportNum());
			applicantInfo.setTravelHistory(applicant.getTravelHistory());
			return new ResponseEntity<>(applicantRepository.save(applicantInfo), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/alldata/{id}")
	public ResponseEntity<HttpStatus> deleteApplicant(@PathVariable("id") long id) {
		try {
			applicantRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/alldata")
	public ResponseEntity<HttpStatus> deleteAllApplicants() {
		try {
			applicantRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/alldata/flagged")
	public ResponseEntity<List<ApplicantInfo>> findByFlagged() {
		try {
			List<ApplicantInfo> applicants = applicantRepository.findByFlagged(true);

			if (applicants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(applicants, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@GetMapping("/alldata/{passportNum}")
	public ResponseEntity<ApplicantInfo> findByPassportNum(@PathVariable("passportNum") String passportNum) {
		try {
			List<ApplicantInfo> applicants = new ArrayList<ApplicantInfo>();
			
			ApplicantInfo applicant = new ApplicantInfo() ;
			
			Boolean found = false;
			
			applicantRepository.findAll().forEach(applicants::add);
			
			if(applicants.size()>0)
			{
				for(int i=0;i<applicants.size();i++)
				{
					if(applicants.get(i).getPassportNum().equalsIgnoreCase(passportNum))
					{
						applicant= new ApplicantInfo(applicants.get(i));
						found=true;
						break;
						
					}
					
				}
				
			}
			
		//	ApplicantInfo applicant = applicantRepository.findByPassportNum(passportNum);

			if(!found)
			{
				applicant.setName("NA");
				applicant.setComments("NA");
				applicant.setTravelHistory("NA");
				applicant.setName("NA");
				
			}	

			return new ResponseEntity<>(applicant, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
=
}
