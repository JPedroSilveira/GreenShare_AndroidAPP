package com.si.greenshare.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class State extends AbstractEntity<State> implements Serializable {

	private Long id;

	private String name;

	private Country country;

	private List<City> cities;

	protected State() {
		super();
	}

	public State(String name, Country country) {
		super();
		this.name = name;
		this.country = country;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido");
		}

		if (isNull(this.country)) {
			this.validationErrors.add("Pais não pode ser nulo.");
		} else if (this.country.isNotValid()) {
			this.validationErrors.addAll(this.country.getValidationErrors());
		}

		return this.validationErrors.isEmpty();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public Country getCountry() {
		return country;
	}

	public List<City> getCities() {
		return cities;
	}

	@Override
	public void update(State e) {
		this.country = e.getCountry();
		this.name = e.getName();	
	}
}
