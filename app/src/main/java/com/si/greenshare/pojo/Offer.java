package com.si.greenshare.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

import com.si.greenshare.pojo.enumeration.OfferStatus;
import com.si.greenshare.pojo.enumeration.OfferType;

/**
 * Created by joao.silva.
 */

public class Offer extends AbstractEntity<Offer> implements Serializable {

    private Long id;

    private Float unitPrice;

    private Integer remainingAmount;

    private Integer initialAmount;

    private Integer offerStatus;

    private Integer type;

    private User user;

    private List<OfferComment> offerComments;

    private FlowerShop flowerShop;

    private Species species;

    private Address address;

    private List<Request> requests;

    private String description;

    protected Offer() {
        super();
    }

    public Offer(Float unitPrice, Integer remainingAmount, User user, Species species, String description,
                 FlowerShop flowerShop) {
        super();
        if (this.unitPrice == null || this.unitPrice == (float) 0) {
            this.type = OfferType.Donation.getOfferType();
            this.unitPrice = (float) 0;
        } else {
            this.type = OfferType.Sale.getOfferType();
            this.unitPrice = unitPrice;
        }
        if (user.getIsLegalPerson()) {
            this.flowerShop = flowerShop;
            this.address = flowerShop.getAddress();
        } else {
            this.address = user.getAddress();
        }
        this.remainingAmount = remainingAmount;
        this.initialAmount = remainingAmount;
        this.species = species;
        this.offerStatus = OfferStatus.Active.getValue();
        this.description = description;
    }

    @JsonIgnore
    public boolean isValid() {
        this.validationErrors.clear();

        if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
            this.validationErrors.add("Descrição inválida.");
        }
        if (isNotNull(this.type) && isNotNull(this.unitPrice)) {
            if (this.type == OfferType.Sale.getOfferType() && is(this.unitPrice).smallerOrEqual(0)) {
                this.validationErrors.add("Preço unitário inválido para uma venda.");
            }
            if (this.type == OfferType.Donation.getOfferType() && is(this.unitPrice).notEqual(0)) {
                this.validationErrors.add("Preço unitário inválido para uma doação.");
            }
        } else {
            if (isNull(this.type)) {
                this.validationErrors.add("Tipo de oferta inválida.");
            }
            if (isNull(this.unitPrice)) {
                this.validationErrors.add("Preço unitário inválido.");
            }
        }
        if (isNull(this.remainingAmount) || is(this.remainingAmount).orSmallerThan(0).orBiggerThan(9999)) {
            this.validationErrors.add("Quantidade restante inválida.");
        }
        if (isNull(this.initialAmount) || is(this.initialAmount).orSmallerThan(1).orBiggerThan(9999)) {
            this.validationErrors.add("Quantidade inicial inválida.");
        }
        if (isNull(this.user)) {
            this.validationErrors.add("O usuário não pode ser nulo.");
        } else if (this.user.isNotValid()) {
            this.validationErrors.addAll(this.user.getValidationErrors());
        }
        if (isNull(this.species)) {
            this.validationErrors.add("A espécie não pode ser nula.");
        } else if (this.species.isNotValid()) {
            this.validationErrors.addAll(this.species.getValidationErrors());
        }
        if (isNotNull(this.flowerShop) && this.flowerShop.isNotValid()) {
            this.validationErrors.addAll(this.species.getValidationErrors());
        }
        if (isNull(this.address)) {
            this.validationErrors.add("O endereço não pode ser nulo.");
        } else if (this.address.isNotValid()) {
            this.validationErrors.addAll(this.address.getValidationErrors());
        }
        if(isNull(this.offerStatus)) {
            this.validationErrors.add("Status da oferta não pode ser nulo.");
        }else if(OfferStatus.exists(this.offerStatus)){
            this.validationErrors.add("Status de oferta inexistente.");
        }
        return this.validationErrors.isEmpty();
    }

    public Long getId() {
        return this.id;
    }

    public Float getUnitPrice() {
        return this.unitPrice;
    }

    public Integer getRemainingAmount() {
        return this.remainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Integer getInitialAmount() {
        return initialAmount;
    }

    public Integer getOfferStatus() {
        return this.offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus.getValue();
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(OfferType type) {
        this.type = type.getOfferType();
    }

    public User getUser() {
        return this.user;
    }

    public Species getSpecies() {
        return this.species;
    }

    public List<Request> getRequests() {
        return this.requests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OfferComment> getOfferComments() {
        return offerComments;
    }

    public FlowerShop getFlowerShop() {
        return flowerShop;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void update(Offer offer) {
        this.description = offer.getDescription();
    }
}
