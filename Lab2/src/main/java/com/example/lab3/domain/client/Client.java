package com.example.lab2.domain.client;

import com.example.lab2.domain.models.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "client_sequence"
    )
    @Id
    @Column(nullable = false)
    private Long clientId;
    @NotNull
    @Column(nullable = false, unique = true)
    private String username;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    private String password;
    private Boolean enabled = true;
    private Boolean notLocked = true;
    /** A user may have more than one role, so we create a
     * many-to-many relationship and join the corresponding columns
     * into the new table: owner_roles.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "client_roles", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    public Client(String username, String email, String encode) {
        this.username = username;
        this.email = email+"@pm.com";
        this.password = encode;
    }

//    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


//    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

//    @Override
    public boolean isAccountNonLocked() {
        return notLocked;
    }

//    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

//    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
