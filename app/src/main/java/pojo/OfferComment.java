package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import helpers.IsHelper;

/**
 * Created by joao.silva.
 */
public class OfferComment extends AbstractEntity<OfferComment> implements Serializable {

    private Long id;

    private String text;

    private User user;

    private Offer offer;

    protected OfferComment() {
        super();
    }

    public OfferComment(String text, User user, Offer offer) {
        super();
        this.text = text;
        this.user = user;
        this.offer = offer;
    }

    public Long getId() {
        return this.id;
    }

    public boolean isValid() {
        this.validationErrors.clear();

        if (isNullOrEmpty(this.text) || is(this.text).orSmallerThan(1).orBiggerThan(250)) {
            this.validationErrors.add("O texto deve conter de 1 a 250 caracteres");
        }
        if (isNull(this.user) || !this.user.getIsLegalPerson()) {
            this.validationErrors.add("Usuário inválido.");
        } else if (this.user.isNotValid()) {
            this.validationErrors.addAll(this.user.getValidationErrors());
        }
        if (isNull(this.offer)) {
            this.validationErrors.add("Oferta não pode ser nula.");
        } else if (this.user.isNotValid()) {
            this.validationErrors.addAll(this.user.getValidationErrors());
        }

        return this.validationErrors.isEmpty();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void update(OfferComment e) {
        this.text = e.getText();
    }

}
