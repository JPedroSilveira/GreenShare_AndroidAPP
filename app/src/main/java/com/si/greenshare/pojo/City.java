package com.si.greenshare.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class City extends AbstractEntity<City> implements Serializable {

	private Long id;

	private String name;

	private State state;

	private List<Address> addresses;

	protected City() {
		super();
	}

	public City(String name, State state) {
		super();
		this.name = name;
		this.state = state;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido");
		}

		if (isNull(this.state)) {
			this.validationErrors.add("Estado não pode ser nulo.");
		} else if (this.state.isNotValid()) {
			this.validationErrors.addAll(this.state.getValidationErrors());
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

	public State getState() {
		return state;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}
	
	public void update(City city) {
		this.name = city.getName();
		this.state = city.getState();
	}
}
