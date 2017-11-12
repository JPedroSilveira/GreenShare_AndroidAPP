package com.si.greenshare.pojo;

import java.io.Serializable;

/**
 * Created by joao.silva.
 */
public class Suggestion extends AbstractEntity<Suggestion> implements Serializable {

	private Long id;

	private User user;

	private Species species;

	protected Suggestion() {
		super();
	}

	public Suggestion(User user, Species species) {
		super();
		this.user = user;
		this.species = species;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNull(this.user)) {
			this.validationErrors.add("Usuário não pode ser nulo.");
		} else if (this.user.isNotValid()) {
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if (isNull(this.species)) {
			this.validationErrors.add("Espécie não pode ser nula.");
		} else if (this.species.isNotValid()) {
			this.validationErrors.addAll(this.species.getValidationErrors());
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}

	public User getUser() {
		return this.user;
	}

	public Species getSpecies() {
		return this.species;
	}

	@Override
	public void update(Suggestion e) {
	}
}