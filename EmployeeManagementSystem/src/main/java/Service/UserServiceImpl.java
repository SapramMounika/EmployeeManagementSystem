package Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import DTO.UserRegistrationDto;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

    @Service
    public class UserServiceImpl implements UserService{

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private BCryptPasswordEncoder passwordEncoder;


        @Override
        public User save(UserRegistrationDto registrationDto) {
            User user = new User(registrationDto.getFirstName(),
                    registrationDto.getLastName(), registrationDto.getEmail(),
                    passwordEncoder.encode(registrationDto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
            return userRepository.save(user);
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            model.User user = userRepository.findByEmail(username);
            if(user == null) {
                throw new UsernameNotFoundException("Invalid username or password.");
            }
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        }

        private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        }

        @Override
        public User save(UserRegistrationDto registrationDto) {
            return null;
        }
    }
