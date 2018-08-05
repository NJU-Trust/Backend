package nju.trust.service;

import nju.trust.dao.PrimaryUserDao;
import nju.trust.entity.user.BaseUser;
import nju.trust.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * All rights Reserved, Designed by Popping Lim
 *
 * @Author: Popping Lim
 * @Date: 2018/7/9
 * @Todo:
 */
@Service
public class UserPrincipalService implements UserDetailsService {
    @Autowired
    PrimaryUserDao userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        BaseUser user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username : " + username)
                );


        return UserPrincipal.create(user);
    }

}
