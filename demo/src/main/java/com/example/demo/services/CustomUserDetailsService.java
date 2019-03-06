package com.example.demo.services;


import com.example.demo.entities.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);
        if(userEntity==null) new UsernameNotFoundException("User not found");
        return userEntity;
    }

    @Transactional
    public UserEntity loadUserById(Integer id){
        UserEntity userEntity = userRepository.getById(id);
        if(userEntity==null) new UsernameNotFoundException("User not found");
        return userEntity;
    }
}
