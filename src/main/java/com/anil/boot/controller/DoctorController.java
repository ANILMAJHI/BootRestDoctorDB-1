package com.anil.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anil.boot.exception.DoctorException;
import com.anil.boot.exception.ResourceNotfoundException;
import com.anil.boot.model.Doctor;
import com.anil.boot.service.DoctoryService;

@RestController
@RequestMapping("/api")
public class DoctorController {

	@Autowired
	DoctoryService service;

	@GetMapping("/test")
	public String testMessage() {
		return "testing message...:";
	}

	@PostMapping("/savedoctor")
	// @PutMapping("/savedoctor")
	public ResponseEntity<Doctor> saveDoctor(@RequestBody Doctor doctor) {

		return new ResponseEntity<Doctor>(service.saveDoctor(doctor), HttpStatus.OK);
	}

	@GetMapping("/alldoctor")
	public ResponseEntity<List<Doctor>> listAllDoctor() {

		List<Doctor> list = service.findAllDoctor();
		if (list == null) {
			throw new ResourceNotfoundException("Resource not availabe in DATABASE");
		}
		return new ResponseEntity<>(list, HttpStatus.OK);
		// return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
	}

	@GetMapping("/docId/{id}")
	public ResponseEntity<Doctor> getById(@PathVariable("id") long id) throws Exception {
		Doctor doctor = service.findById(id);
		if (doctor != null) {
			return new ResponseEntity<Doctor>(doctor, HttpStatus.FOUND);

		}
		/*
		 * return new ResponseEntity( new DoctorException(id +
		 * " doctor is not available in DB,Please check in other doctorID ...")
		 * .getMessage(), HttpStatus.NOT_FOUND);
		 */
		throw new ResourceNotfoundException(id + ":Resource not availabe in DB");

		// return new ResponseEntity<Doctor>(service.saveDoctor(doctor), HttpStatus.OK);
	}

	@GetMapping("/docname/{name}")
	public ResponseEntity<Doctor> getByName(@PathVariable("name") String name) throws DoctorException {
		// Doctor doctor=service.findById(id);
		Doctor doctor = service.findByName(name);
		if (doctor.getName() != null) {
			return new ResponseEntity<Doctor>(doctor, HttpStatus.FOUND);
		}

		// DoctorException doc = new DoctorException("Please check the doctor name...");
		return new ResponseEntity(new DoctorException("Please check the doctor msg...").getMessage(),
				HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deldoc/{id}")
	public ResponseEntity<Doctor> deleteByDocId(@PathVariable("id") long id) throws Exception {
		Doctor doctor = service.deleteByDocId(id);

		if (doctor != null) {
			return new ResponseEntity(new DoctorException(id + " Doctor Not Available in DB... ").getMessage(),
					HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity(new DoctorException(id + " Doctor Successfully deleted... ").getMessage(),
				HttpStatus.NO_CONTENT);

		// return new ResponseEntity<Doctor>(service.saveDoctor(doctor), HttpStatus.OK);
	}

	
	/*
	 * for testing in postman this is url below
	 * 
	 * http://localhost:8080/api/findPagination?page=0&size=2
	 * 
	 */
	//@GetMapping("/findPagination/{page}/{size}")
	@GetMapping("/findPagination")
	public ResponseEntity<List<Doctor>> getAllDoctorsWithPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3") int size) {
		List<Doctor> doctors = service.findDeptWithPagination(page, size);
		return new ResponseEntity<>(doctors, HttpStatus.OK);
		
		

	}

}
