package com.caesar_84.sellhelper.service.user;

import com.caesar_84.sellhelper.LoggedUser;
import com.caesar_84.sellhelper.domain.User;
import com.caesar_84.sellhelper.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DetailedUserService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String lowerCaseEmail = email.toLowerCase();
        User user = repository.findByEmail(lowerCaseEmail);

        if (user == null) {
            throw new UsernameNotFoundException(lowerCaseEmail + " not found");
        }
        if (!user.isEnabled()) {
            throw new DisabledException(lowerCaseEmail + " is disabled here. Please, contact your administrator");
        }

        return new LoggedUser(user);
    }
}
