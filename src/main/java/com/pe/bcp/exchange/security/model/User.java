package com.pe.bcp.exchange.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

  private String username;
  private String password;

  private Boolean enabled;

  private List<Role> roles;

  public User(String username, List<Role> roles) {
    this.username = username;
    this.roles = roles;
  }

  @Override
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
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
    return this.enabled;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.roles.stream().map(authority ->
      new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return this.password;
  }


}
