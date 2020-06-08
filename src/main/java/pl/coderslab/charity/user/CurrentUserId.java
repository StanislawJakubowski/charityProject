package pl.coderslab.charity.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CurrentUserId extends User {

    private final long userId;

    /**
     * @param username
     * @param password
     * @param authorities
     * @param userId
     */
    public CurrentUserId(String username, String password,
                         Collection<? extends GrantedAuthority> authorities,
                         long userId) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

}