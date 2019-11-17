/*
 * User.java
 *
 * created at 2019-10-25 by n.kyurkchiyski <YOURMAILADDRESS>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package com.example.shop.base.model;


import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "t_Users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cUserId")
    private Integer id;

    @Column(name = "cUserFirstName")
    private String firstName;

    @Column(name = "cUserLastName")
    private String lastName;

    @Column(name = "cUserEmail")
    private String email;

    @Column(name = "cUserPassword")
    private String password;

    @Column(name = "cUserCreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "cUserActive")
    private boolean isActive;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "t_UserRoles", //
            joinColumns = {@JoinColumn(name = "cUserRoleUserId")}, //
            inverseJoinColumns = {@JoinColumn(name = "cUserRoleRoleId")}, //
            foreignKey = @ForeignKey(name = "FK_UserRoles_Users"), //
            inverseForeignKey = @ForeignKey(name = "FK_UserRoles_Roles"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", targetEntity = Order.class)
    private Set<Order> orders;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cUserAddressId", foreignKey = @ForeignKey(name = "FK_Users_Addresses"))
    private Address address;


    public User() {
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
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


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public boolean isActive() {
        return isActive;
    }


    public void setActive(boolean active) {
        isActive = active;
    }


    public Set<Role> getRoles() {
        return roles;
    }


    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }


    public Set<Order> getOrders() {
        return orders;
    }


    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }


    public void addRole(Role role) {
        this.roles.add(role);
    }


    public void removeRole(Role role) {
        this.roles.remove(role);
    }

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }

    public boolean isAdmin() {
        return this.roles.stream().anyMatch(x -> x.getName().equalsIgnoreCase("admin"));
    }
}
