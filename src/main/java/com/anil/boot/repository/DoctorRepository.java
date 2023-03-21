package com.anil.boot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.anil.boot.model.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

	@Query(value = "SELECT * FROM (SELECT d.*, ROWNUM rnum FROM (SELECT * FROM doctor ORDER BY id) d WHERE ROWNUM <= ?1) WHERE rnum >= ?2", nativeQuery = true)
	List<Doctor> findAllDoctorsWithPagination(int end, int start);

}
