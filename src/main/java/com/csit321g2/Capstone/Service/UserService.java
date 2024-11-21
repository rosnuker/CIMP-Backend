package com.csit321g2.Capstone.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.csit321g2.Capstone.Entity.UserEntity;
import com.csit321g2.Capstone.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository urepo;

    public UserEntity registerUser(UserEntity user) {
        BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
        String encryptedPwd = bcrypt.encode(user.getPassword());
        user.setPassword(encryptedPwd);
		user.setDeleted(false);
		user.setType(user.getType().toLowerCase());
        urepo.save(user);

		UserEntity copy = new UserEntity();
		copy.setUid(user.getUid());
		copy.setFname(user.getFname());
		copy.setLname(user.getLname());
		copy.setUsername(user.getUsername());
		copy.setType(user.getType());

		return copy;
    }

    public List<UserEntity> getAllUsers() {
        return urepo.findAllUsersWithoutPassword();
    }

	@SuppressWarnings("finally")
	public UserEntity updateUser(long uid, UserEntity newuserDetails) {
		UserEntity user = new UserEntity();
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

		try {
			
			user = urepo.findById(uid).orElseThrow(() -> 
            	new NoSuchElementException("User " + uid + " does not exist!"));
			
			//update
			user.setFname(newuserDetails.getFname());
			user.setLname(newuserDetails.getLname());
			user.setUsername(newuserDetails.getUsername());
			user.setType(newuserDetails.getType());
			user.setDepartment(newuserDetails.getDepartment());
			user.setDesignation(newuserDetails.getDesignation());

			if(newuserDetails.getPassword() != null) {
				String encryptedPwd = bcrypt.encode(newuserDetails.getPassword());
				user.setPassword(encryptedPwd);
			}
			
		} catch (NoSuchElementException ex){
			throw new NoSuchElementException("user " + uid + " does not exist!");
		} finally {
			urepo.save(user);

			UserEntity copy = urepo.findByUsernameWithoutPassword(user.getUsername());

			return copy;
		}
	}
	
	public String deleteUser(long uid) {
		String msg = "";
		
		try {
			Optional<UserEntity> userOptional = urepo.findById(uid);
			if (userOptional.isPresent()) {
				UserEntity user = userOptional.get();
				user.setDeleted(true);
				msg = "User with ID " + uid + " is successfully deleted!";
				urepo.save(user);
			} else {
				msg = "User with ID " + uid + " does not exist.";
			}
		} catch (Exception e) {
			msg = "An error occurred while deleting the user with ID " + uid + ": " + e.getMessage();
		}
		
		return msg;
	}
	
    public boolean validateUserCredentials(String username, String password) {

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		UserEntity user = urepo.findByUsername(username);
        
        if (user != null && bcrypt.matches(password, user.getPassword())) {
            return true;
        }

		return false;
    }

	public ResponseEntity<UserEntity> login(@RequestBody UserEntity loginData) {
        String username = loginData.getUsername();
        String password = loginData.getPassword();

        boolean isValidCredentials = validateUserCredentials(username, password);

        if (isValidCredentials) {
            UserEntity user = urepo.findByUsernameWithoutPassword(username);
			
			return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

	public List<String> getFullNamesForAccPerson() {
		return urepo.findFullNameByAccPersonType();
	}

	public String getFullNameByUid(Long uid) {
        return urepo.findFullNameByUid(uid);
    }
}
