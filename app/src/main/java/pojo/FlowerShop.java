package pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class FlowerShop extends AbstractEntity<FlowerShop> implements Serializable {

    private Long id;

    private String cnpj;

    private String description;

    private String name;

    private Boolean enabled;

    private Address address;

    private Boolean hasImage;

    private User user;

    private List<Offer> offers;

    protected FlowerShop() {
        super();
    }

    public FlowerShop(String cnpj, String description, User user) {
        super();
        this.cnpj = cnpj;
        this.description = description;
        this.user = user;
        this.hasImage = false;
    }

    @JsonIgnore
    public boolean isValid() {
        this.validationErrors.clear();

        if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
            this.validationErrors.add("Descrição inválida");
        }
        if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
            this.validationErrors.add("Nome inválido.");
        }
        if (isNull(this.user) || !this.user.getIsLegalPerson()) {
            this.validationErrors.add("Usuário inválido.");
        } else if (this.user.isNotValid()) {
            this.validationErrors.addAll(this.user.getValidationErrors());
        }
        if (isNull(this.address)) {
            this.validationErrors.add("Endereço não pode ser nulo.");
        } else if (this.address.isNotValid()) {
            this.validationErrors.addAll(this.address.getValidationErrors());
        }
        if (isNull(this.cnpj) || is(this.cnpj).equal(14)) {
            this.validationErrors.add("CNPJ inválido");
        }
        return this.validationErrors.isEmpty();
    }

    public Long getId() {
        return this.id;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return this.user;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Address getAddress() {
        return address;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public Boolean getHasImage() {
        return this.hasImage;
    }

    public void update(FlowerShop flowerShop) {
        this.address = flowerShop.getAddress();
        this.description = flowerShop.getDescription();
    }
}
