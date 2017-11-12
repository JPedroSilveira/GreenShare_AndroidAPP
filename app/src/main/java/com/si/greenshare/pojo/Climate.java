package com.si.greenshare.pojo;


import java.io.Serializable;

import java.util.List;


/**
 * Created by joao.silva.
 */
public class Climate extends AbstractEntity<Climate> implements Serializable {

	private Long id;

	private String description;

	private String name;

	private List<Species> species;

	protected Climate() {
		super();
	}

	public Climate(String description, String name) {
		super();
		this.description = description;
		this.name = name;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Espécie inválida.");
		}
		if (isNullOrEmpty(this.name)) {
			this.validationErrors.add("O nome não pode ser nulo.");
		}else if(is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("O nome deve conter entre 1 e 100 caracteres.");
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
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

	public List<Species> getSpecies() {
		return this.species;
	}

	public void update(Climate climate) {
		this.description = climate.getDescription();
		this.name = climate.getName();
	}
}