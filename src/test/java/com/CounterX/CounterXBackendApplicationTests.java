package com.CounterX;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import com.CounterX.repository.AdminRepository;

@SpringBootTest
class CounterXBackendApplicationTests {

	@Autowired
	private AdminRepository adminRepository;

	@Test
	void contextLoads() {
		System.out.println("====== ALL ADMINS ======");
		adminRepository.findAll().forEach(a -> {
			System.out.println("ID: " + a.getId() + ", Username: " + a.getUsername() + ", Email: " + a.getEmail() + ", Password: " + a.getPassword());
		});
		System.out.println("========================");
	}

}
