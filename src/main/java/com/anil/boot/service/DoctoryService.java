package com.anil.boot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.anil.boot.model.Doctor;
import com.anil.boot.repository.DoctorRepository;

@Service
public class DoctoryService {

	@Autowired
	DoctorRepository doctorRepository;

	List<Doctor> listDoctor; // = doctorRepository.findAll();

	public Doctor saveDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	public List<Doctor> findAllDoctor() {
		List<Doctor> listDoctor = doctorRepository.findAll();

		return (List<Doctor>) listDoctor;
	}

	//when you hit 1st time, its got to db after that its not go to db.
	@Cacheable(cacheNames = "doctorCache", key = "id")
	public Doctor findById(long id) {
		// List<Doctor> user = doctorRepository.findAll();

		/*
		 * if (!user.isEmpty()) { return (Doctor) user; }
		 */
		listDoctor = doctorRepository.findAll();
		for (Doctor doc : listDoctor) {
			Long userId = doc.getId();
			if (userId == id) {
				return doc;
			}

		}
		return null;
	}

	public Optional<Doctor> findDocID(long id) {
		listDoctor = doctorRepository.findAll();
		return listDoctor.stream().filter(s -> s.getId() == id).findFirst();
	}

	public Doctor findByName(String name) {
		// List<Doctor> doc = c.findAll();
		// Doctor doctor = new Doctor();
		listDoctor = doctorRepository.findAll();

		for (Doctor doctor1 : listDoctor) {
			/*
			 * String doctorName = doctor1.getName(); long doctorId = doctor1.getId();
			 * String doctorSpe = doctor1.getSpecialize(); int doctorSalary =
			 * doctor1.getSalary(); if (name.equals(doctorName)) {
			 * doctor.setName(doctorName); doctor.setId(doctorId);
			 * doctor.setSpecialize(doctorSpe); doctor.setSalary(doctorSalary);
			 */
			String docName = doctor1.getName();
			if (!StringUtils.isEmpty(docName) && docName.equalsIgnoreCase(name)) {
				return doctor1;
			}

		}

		return null;
	}

	public Doctor deleteByDocId(Long id) {
		List<Doctor> listDoctor=doctorRepository.findAll();
		for (Doctor doc : listDoctor) {
			if (doc.getId() == id) {
				doctorRepository.deleteById(id);
			}
		}
		return null;
	}

	public List<Doctor> findDeptWithPagination(int page, int size) {

		int start = page * size;
		int end = start + size;
		return doctorRepository.findAllDoctorsWithPagination(end, start);
	}

}
