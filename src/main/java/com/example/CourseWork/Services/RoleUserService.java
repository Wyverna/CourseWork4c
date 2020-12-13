package com.example.CourseWork.Services;

import com.example.CourseWork.Models.RoleUser;
import com.example.CourseWork.Repositories.RoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleUserService {
    @Autowired
    private RoleUserRepository roleUserRepository;

    public Optional<RoleUser> GetByID(Integer id)
    {
        return roleUserRepository.findById(id);
    }
}
