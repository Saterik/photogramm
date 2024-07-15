package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.demo.entity.enums.ERole;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity

public class User implements UserDetails {
    @Id
    @GeneratedValue
    private  Long id;
    @Column(nullable = false)
    private  String name;
    @Column(unique = true, updatable = false)
    private  String username;
    @Column(nullable = false)
    private  String lastname;
    @Column(unique = true)
    private  String email;
    @Column(columnDefinition = "text")
    private  String bio;
    @Column(length = 3000)
    private  String password;


    @ElementCollection(targetClass = ERole.class)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> role = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
    private  ArrayList<Post> posts = new ArrayList<>();
    @JsonFormat(pattern = "yyy-mm-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User(){

    }

    public User(Long id,
                String username,
                String email,
                String password,
                Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @PrePersist
    protected void onCreate(){
        this.createdDate = LocalDateTime.now();
    }

    @Override
    public String getPassword(){
        return password;
    }

    //SECURITY
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

}
