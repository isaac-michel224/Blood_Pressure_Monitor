package com.tts.Heart.Rate.Monitor.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER")
public class EndUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @NotEmpty(message = "Must provide first name")
    private String firstName;

    @NotEmpty(message = "Must provide last name")
    private String lastName;

    @Email(message = "Must have a valid email")
    @NotEmpty(message = "Must have an email")
    private String email;

    @Length(min=10, message="minimum of 10 characters")
    @Length(max=60,  message="maximum of 20 characters")
    private String password;

    private String username;

//    @OneToOne(mappedBy= "enduser")
//    private Patient patient;

    @OneToMany(mappedBy="enduser")
    private Set<Record> records;

    @Column(name="enabled")
    private int enrolled; // "0" not in system -- "1" in system

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ToString.Exclude
    private Set<Role> roles;

    private int active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndUser endUser = (EndUser) o;
        return enrolled == endUser.enrolled && id.equals(endUser.id)
                && firstName.equals(endUser.firstName)
                && lastName.equals(endUser.lastName)
                && email.equals(endUser.email)
                && password.equals(endUser.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, email, password, enrolled);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

//Admin page for Patient
}
