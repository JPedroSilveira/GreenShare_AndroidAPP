package com.si.greenshare.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import com.si.greenshare.pojo.enumeration.AddressType;

/**
 * Created by joao.silva.
 */
public class Address extends AbstractEntity<Address> implements Serializable {

    private Long id;

    private Integer number;

    private Integer type;

    private String neighborhood;

    private String addressName;

    private String complement;

    private String reference;

    private City city;

    private User user;

    private Offer offer;

    private FlowerShop flowerShop;

    private List<String> validationErrors;

    protected Address() {
        super();
    }

    public Address(City city, Integer number, String neighborhood, String addressName, String complement,
                   String reference, Integer type) {
        super();
        this.city = city;
        this.number = number;
        this.neighborhood = neighborhood;
        this.addressName = addressName;
        if (isNotNull(complement) && complement.isEmpty()) {
            this.complement = null;
        } else {
            this.complement = complement;
        }
        if (isNotNull(reference) && reference.isEmpty()) {
            this.reference = null;
        } else {
            this.reference = reference;
        }
        this.type = type;
    }

    @JsonIgnore
    public boolean isValid() {
        this.validationErrors.clear();

        if (isNull(this.city)) {
            this.validationErrors.add("Cidade nula.");
        } else if (this.city.isNotValid()) {
            this.validationErrors.addAll(this.city.getValidationErrors());
        }
        if (isNull(this.number) || is(this.number).smallerThan(1)) {
            this.validationErrors.add("O número não pode ser nulo ou menor que um.");
        }
        if (isNull(this.neighborhood) || is(this.neighborhood).orSmallerThan(1).orBiggerThan(200)) {
            this.validationErrors.add("O bairro não pode ser nulo e deve conter entre 1 e 200 caracteres.");
        }
        if (isNull(this.addressName) || is(this.addressName).orSmallerThan(1).orBiggerThan(200)) {
            this.validationErrors.add("O endereço não pode ser nulo e deve conter entre 1 e 200 caracteres.");
        }
        if (isNull(this.complement) || is(this.complement).orSmallerThan(1).orBiggerThan(200)) {
            this.validationErrors.add("O complemento não pode ser nulo e deve conter entre 1 e 200 caracteres.");
        }
        if (isNull(this.reference) || is(this.reference).orSmallerThan(1).orBiggerThan(200)) {
            this.validationErrors.add("A referencia não pode ser nula e deve conter entre 1 e 200 caracteres");
        }
        if (isNull(this.type) || AddressType.exists(this.type)) {
            this.validationErrors.add("O tipo de endereço é inválido.");
        }
        if (isNull(this.user) && isNull(this.flowerShop)) {
            this.validationErrors.add("O endereço deve conter um usuário ou uma floricultura.");
        }
        return this.validationErrors.isEmpty();
    }

    public boolean isValidNeighborhood(){
        this.validationErrors.clear();
        if (isNull(this.neighborhood) || is(this.neighborhood).orSmallerThan(1).orBiggerThan(200)) {
            this.validationErrors.add("O bairro não pode ser nulo e deve conter entre 1 e 200 caracteres.");
        }
        return this.validationErrors.isEmpty();
    }

    public boolean isValidAddressName(){
        this.validationErrors.clear();
        if (isNull(this.addressName) || is(this.addressName).orSmallerThan(1).orBiggerThan(200)) {
            this.validationErrors.add("O endereço não pode ser nulo e deve conter entre 1 e 200 caracteres.");
        }
        return this.validationErrors.isEmpty();
    }

    public boolean isValidNumber(){
        this.validationErrors.clear();
        if (isNull(this.number) || is(this.number).smallerThan(1)) {
            this.validationErrors.add("O número não pode ser nulo ou menor que um.");
        }
        return this.validationErrors.isEmpty();
    }

    public boolean isValidAddressType(){
        this.validationErrors.clear();
        if (isNull(this.type) || AddressType.exists(this.type)) {
            this.validationErrors.add("O tipo de endereço é inválido.");
        }
        return this.validationErrors.isEmpty();
    }

    public Long getId() {
        return this.id;
    }

    public User getUser() {
        return this.user;
    }

    public City getCity() {
        return city;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getType() {
        return type;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getAddressName() {
        return addressName;
    }

    public String getComplement() {
        return complement;
    }

    public String getReference() {
        return reference;
    }

    public Offer getOffer() {
        return offer;
    }

    public FlowerShop getFlowerShop() {
        return flowerShop;
    }

    public void update(Address address) {
        this.number = address.getNumber();
        this.neighborhood = address.getNeighborhood();
        this.addressName = address.getAddressName();
        this.complement = address.getComplement();
        this.reference = address.getReference();
        this.city = address.getCity();
        this.type = address.getType();
    }
}
