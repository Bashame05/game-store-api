package com.makar.gamestoreapi.Services;

import com.makar.gamestoreapi.Models.User;
import com.makar.gamestoreapi.Models.UserPrincipal;
import com.makar.gamestoreapi.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    public MyUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User tempUser = userRepository.findByUserName(username);
        return new UserPrincipal(tempUser);
    }
}
