package com.example.CourseWork.Services;

import com.example.CourseWork.Repositories.*;
import com.example.CourseWork.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleUserRepository roleUserRepository;

    public void AddItem(User user)
    {
        user.setPassworduser(md5Custom(user.getPassworduser()));
        RoleUser roleUser = roleUserRepository.findById(1).get();
        user.setRoleuser(roleUser);
        userRepository.save(user);
    }

    public User getItemByLoginUserAndPassword(String username, String password)
    {
       return userRepository.findByLoginuserAndPassworduser(username,md5Custom(password));
    }

    public User getItemByLoginUser(String username)
    {
        return userRepository.findByLoginuser(username);
    }

    public static String md5Custom(String st) {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(st.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while( md5Hex.length() < 32 ){
            md5Hex = "0" + md5Hex;
        }

        return md5Hex;
    }
}
