package com.greenghost107.ourHouse.service.impl;

import com.greenghost107.ourHouse.config.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class HttpServletRequestImplTest {

    @Autowired
    private HttpServletRequestImpl httpServletRequest;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    private HashMap<String,String> userNamePass;

    private HashMap<User,String> usersDetails;

    @BeforeEach
    void setUp(){
        usersDetails = new HashMap<>();
        userNamePass = generateUserNamesAndPasswords();
        Iterator it = userNamePass.entrySet().iterator();
        while(it.hasNext())
        {
            HttpServletRequest request = mock(HttpServletRequest.class);
            Map.Entry pair = (Map.Entry) it.next();
            Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
            SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_USER");
            roles.add(role);
            User userDetails = new User(pair.getKey().toString(),pair.getValue().toString(),roles);
            usersDetails.put(userDetails,jwtTokenUtil.generateToken(userDetails));
        }



    }

    private HashMap<String, String> generateUserNamesAndPasswords() {
        HashMap<String, String> ans = new HashMap<>();
        for(int i=0;i<5;i++)
        {
            Random random = new Random();
            int randomWithNextInt = random.nextInt(30);
            int randomWithNextInt2 = random.nextInt(20)+10;
            ans.put(generateRandomAlphaNumericStringOfLength(randomWithNextInt),generateRandomAlphaNumericStringOfLength(randomWithNextInt2));

        }
        return ans;
    }

    private String generateRandomAlphaNumericStringOfLength(int targetStringLength)
    {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
//        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    @Test
    void getUserNameFromRequest() {
        final HttpServletRequest request = mock(HttpServletRequest.class);
        Iterator it = usersDetails.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry) it.next();
            when(request.getHeader("Authorization")).thenReturn("Bearer " + pair.getValue());
            User user = (User) pair.getKey();
            assertEquals(user.getUsername(),httpServletRequest.getUserNameFromRequest(request));
            System.out.println(user.getUsername() + " " + user.getPassword());
        }

    }
}