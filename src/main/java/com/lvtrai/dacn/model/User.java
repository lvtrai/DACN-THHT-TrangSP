package com.lvtrai.dacn.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lvtrai.dacn.sercurity.Authority;
import com.lvtrai.dacn.sercurity.UserRole;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users", uniqueConstraints = {
		 @UniqueConstraint(columnNames = {
		            "username"
		        }),
		        @UniqueConstraint(columnNames = {
		            "email"
		        })
})
@NamedEntityGraph(
		name= "UserComplete",
		attributeNodes= { @NamedAttributeNode(value="userRoles", subgraph="role-subgraph") },
		subgraphs= { 
			@NamedSubgraph(name = "role-subgraph", attributeNodes = {  @NamedAttributeNode("role") }
		)}
	)
@SuppressWarnings("serial")
public class User implements UserDetails{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@NotNull
	private String username;
	private String fullname;
	@NotNull
	@Email
	private String email;
    private String password;
    private String address;
    private String phone;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<UserRole> userRoles = new HashSet<>();

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		userRoles.forEach(userRole -> authorites.add(new Authority(userRole.getRole().getName())));
		return authorites;
	}
	
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
    public User() {

    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}