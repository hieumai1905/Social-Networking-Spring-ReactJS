package com.codegym.service.roleService;

import com.codegym.model.Role;
import com.codegym.repository.IRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService{
    @Autowired
    private IRoleRepo roleRepo;

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public boolean save(Role role) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Role findById(Long id) {
        return roleRepo.findById(id).orElse(new Role());
    }

    public Role findByName(String name){
        return roleRepo.findRoleByName(name);
    }
}
