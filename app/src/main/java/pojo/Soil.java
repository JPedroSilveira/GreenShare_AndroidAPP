package pojo;

import java.io.Serializable;

import java.util.List;


/**
 * Created by joao.silva.
 */
public class Soil extends AbstractEntity<Soil> implements Serializable {

	private Long id;

	private String description;

	private String name;

	private List<Species> species;

	protected Soil() {
		super();
	}
	
	public Soil(String description, String name) {
		super();
		this.description = description;
		this.name = name;
	}
	
	@Override
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)){
			this.validationErrors.add("Descrição inválida.");
		}
		if(isNullOrEmpty(this.name) || is(this.name).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome inválido.");
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

	public void setSpecies(List<Species> species) {
		this.species = species;
	}

	@Override
	public void update(Soil e) {
		this.description = e.getDescription();
		this.name = e.getName();
	}
}