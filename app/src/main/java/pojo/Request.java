package pojo;

import java.io.Serializable;

/**
 * Created by joao.silva.
 */
public class Request extends AbstractEntity<Request> implements Serializable {

	private Long id;

	private Integer amount;

	private Boolean wasAccepted;

	private Offer offer;

	private User user;

	protected Request() {
		super();
	}

	public Request(Integer amount, Offer offer, User user) {
		super();
		this.amount = amount;
		this.offer = offer;
		this.user = user;
		this.wasAccepted = false;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.amount) || is(this.amount).orSmallerThan(1).orBiggerThan(9999)) {
			this.validationErrors.add("Quantidade inválida.");
		}
		if (isNull(this.offer)) {
			this.validationErrors.add("A oferta não pode ser nula.");
		} else if (this.offer.isNotValid()) {
			this.validationErrors.addAll(this.offer.getValidationErrors());
		}
		if (isNull(this.user)) {
			this.validationErrors.add("O usuário não pode ser nulo.");
		} else if (this.user.isNotValid()) {
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if(isNull(this.wasAccepted)) {
			this.validationErrors.add("Valor wasAccepted não pode ser nulo.");
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Boolean getWasAccepted() {
		return wasAccepted;
	}

	public void setWasAccepted(Boolean wasAccepted) {
		this.wasAccepted = wasAccepted;
	}

	public Offer getOffer() {
		return this.offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void update(Request e) {
		this.amount = e.getAmount();
	}
}