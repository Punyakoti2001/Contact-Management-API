package com.buildbot.contactsmanagement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "address",schema = "cma")
public class Address 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    /**
     * Street name of the Address.
     */
    @Size(max = 100)
    @Column(name = "street_name")
    private String streetName;

    /**
     * City of the Address.
     */
    @Size(max = 100)
    @Column(name = "city")
    private String city;

    /**
     * State of the Address.
     */
    @Size(max = 100)
    @Column(name = "state")
    private String state;

    /**
     * Zip code (or postal code) of the Address.
     */
    @Size(max = 10)
    @Column(name = "zip_code")
    private String pinCode;

    /**
     * Country of the Address.
     */
    @Size(max = 100)
    @Column(name = "country")
    private String country;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id",referencedColumnName = "contactId")
    private Contact contact;

}