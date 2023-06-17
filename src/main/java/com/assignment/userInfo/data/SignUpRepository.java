package com.assignment.userInfo.data;

import com.assignment.userInfo.entity.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp,String> {
}
