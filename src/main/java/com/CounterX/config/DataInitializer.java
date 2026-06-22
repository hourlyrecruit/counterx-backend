package com.CounterX.config;

import com.CounterX.entity.Admin;
import com.CounterX.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(AdminRepository adminRepository) {
        return args -> {
            if (adminRepository.count() == 0) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setPassword("admin123");
                adminRepository.save(admin);
                
                Admin staff = new Admin();
                staff.setUsername("staff");
                staff.setEmail("staff@counterx.com");
                staff.setPassword("staff123");
                adminRepository.save(staff);
                
                System.out.println("Default users created: admin@example.com / admin123, staff@counterx.com / staff123");
            }
        };
    }
}
