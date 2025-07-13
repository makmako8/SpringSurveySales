package com.example.springsurveysales.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.springsurveysales.entity.User;
import com.example.springsurveysales.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // 初期ユーザーが存在しなければ作成
        if (userRepository.findByUsername("testuser").isEmpty()) {

            User user = new User();
            user.setUsername("testuser");
            user.setPassword(passwordEncoder.encode("password123")); // 必ず暗号化
            user.setRole("USER"); // ロール設定

            userRepository.save(user);

            System.out.println("初期ユーザー (testuser) が作成されました");
        } else {
            System.out.println("初期ユーザーは既に存在しています");
        }
    }
}
