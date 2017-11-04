package pojo;

import java.io.Serializable;

import java.util.List;

/**
 * Created by joao.silva.
 */
public class Growth extends AbstractEntity<Growth> implements Serializable {

	private Long id;

	private String name;

	private String description;

	private List<Species> species;

	protected Growth() {
		super();
	}
	
	public Growth(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	
	public boolean isValid() {
		this.validationErrors.clear();
		
		if(isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(100)){
			this.validationErrors.add("Descrição inválida.");
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

	public List<Species> getSpecies() {
		return this.species;
	}

	public void setEspecies(List<Species> species) {
		this.species = species;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void update(Growth e) {
		this.description = e.getDescription();
		this.name = e.getName();
	}
}