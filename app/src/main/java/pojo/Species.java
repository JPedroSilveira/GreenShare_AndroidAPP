package pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class Species extends AbstractEntity<Species> implements Serializable {

	private Long id;

	private Boolean enabled;

	private Boolean attractBirds;

	private String description;

	private String cultivationGuide;

	private Boolean isMedicinal;

	private Boolean attractBees;

	private String scientificName;

	private String commonName;

	private Boolean isOrnamental;

	private Integer averageHeight;

	private Integer rootDepth;

	private Growth growth;

	private List<Climate> climates;

	private List<Soil> soils;

	private Flower flower;

	private Fruit fruit;

	private List<Offer> offers;

	private List<Post> posts;

	private Suggestion suggestions;

	private Boolean hasImage;

	protected Species() {
		super();
	}

	public Species(Boolean attractBirds, String description, String cultivationGuide, Boolean isMedicinal,
			Boolean attractBees, String scientificName, String commonName, Boolean isOrnamental, Integer averageHeight,
			Growth growth, Integer rootDepth, List<Climate> climates, List<Soil> soils, Flower flower, Fruit fruit) {
		super();
		this.enabled = false;
		this.attractBirds = attractBirds;
		this.description = description;
		this.cultivationGuide = cultivationGuide;
		this.isMedicinal = isMedicinal;
		this.attractBees = attractBees;
		this.scientificName = scientificName;
		this.commonName = commonName;
		this.isOrnamental = isOrnamental;
		this.averageHeight = averageHeight;
		this.growth = growth;
		this.rootDepth = rootDepth;
		this.climates = climates;
		this.soils = soils;
		this.flower = flower;
		this.fruit = fruit;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.description) || is(this.description).orSmallerThan(1).orBiggerThan(2500)) {
			this.validationErrors.add("Descrição inválida.");
		}
		if (isNullOrEmpty(this.cultivationGuide) || is(this.cultivationGuide).orSmallerThan(1).orBiggerThan(5000)) {
			this.validationErrors.add("Guia de cultivo inválido.");
		}
		if (isNull(this.enabled)) {
			this.validationErrors.add("Definição de aprovação inválida.");
		}
		if (isNull(this.attractBees)) {
			this.validationErrors.add("Definição de atração de abelhas inválida.");
		}
		if (isNull(this.attractBirds)) {
			this.validationErrors.add("Definição de atração de pássaros inválida.");
		}
		if (isNull(this.isMedicinal)) {
			this.validationErrors.add("Definição de planta medicinal inválida.");
		}
		if (isNull(this.isOrnamental)) {
			this.validationErrors.add("Definição de planta ornamental inválida.");
		}
		if (isNull(this.averageHeight) || is(this.averageHeight).orSmallerThan(1).orBiggerThan(50000)) {
			this.validationErrors.add("Altura inválida, deve estar entre 1 e 50000 centímetros.");
		}
		if (isNull(this.rootDepth) || is(this.rootDepth).orSmallerThan(1).orBiggerThan(50000)) {
			this.validationErrors.add("Profundidade da raiz inválida, deve estar entre 1 e 50000 centímetros.");
		}
		if (isNull(this.commonName) || is(this.commonName).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome popular inválido.");
		}
		if (isNull(this.scientificName) || is(this.scientificName).orSmallerThan(1).orBiggerThan(100)) {
			this.validationErrors.add("Nome científico inválido.");
		}
		if (isNull(this.growth)) {
			this.validationErrors.add("Nível de crescimento não pode ser nulo.");
		} else if (this.growth.isNotValid()) {
			this.validationErrors.addAll(this.growth.getValidationErrors());
		}
		if (isNull(this.soils) || this.soils.isEmpty()) {
			this.validationErrors.add("Solos não podem ser nulos ou vazios.");
		} else {
			for(Soil s:this.soils){
				if(s.isNotValid()){
					this.validationErrors.add("Solo inválido.");
				}
				break;
			}
		}
		if (isNotNull(this.climates)) {
			for(Climate c:this.climates){
				if(c.isNotValid()){
					this.validationErrors.add("Clima inválido.");
				}
				break;
			}
		}
		if (isNotNull(this.flower) && this.flower.isNotValid()) {
			this.validationErrors.addAll(this.flower.getValidationErrors());
		}
		if (isNotNull(this.fruit) && this.fruit.isNotValid()) {
			this.validationErrors.addAll(this.fruit.getValidationErrors());
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return this.id;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public Boolean getAttractBirds() {
		return this.attractBirds;
	}

	public String getDescription() {
		return this.description;
	}

	public String getCultivationGuide() {
		return this.cultivationGuide;
	}

	public Boolean getIsMedicinal() {
		return this.isMedicinal;
	}

	public Boolean getAttractBees() {
		return this.attractBees;
	}

	public String getScientificName() {
		return this.scientificName;
	}

	public String getCommonName() {
		return this.commonName;
	}

	public Boolean getIsOrnamental() {
		return this.isOrnamental;
	}

	public Integer getAverageHeight() {
		return this.averageHeight;
	}

	public Growth getGrowth() {
		return this.growth;
	}

	public List<Climate> getClimates() {
		return this.climates;
	}

	public List<Soil> getSoils() {
		return this.soils;
	}

	public Flower getFlower() {
		return this.flower;
	}

	public Fruit getFruit() {
		return this.fruit;
	}

	public List<Offer> getOffers() {
		return this.offers;
	}

	public List<Post> getPosts() {
		return this.posts;
	}

	public Suggestion getSuggestions() {
		return this.suggestions;
	}

	public Boolean getHasImage() {
		return this.hasImage;
	}

	public Integer getRootDepth() {
		return rootDepth;
	}

	@Override
	public void update(Species e) {
		this.enabled = e.getEnabled();
		this.attractBees = e.getAttractBees();
		this.attractBirds = e.getAttractBirds();
		this.averageHeight = e.getAverageHeight();
		this.climates = e.getClimates();
		this.commonName = e.getCommonName();
		this.cultivationGuide = e.getCultivationGuide();
		this.description = e.getDescription();
		this.flower = e.getFlower();
		this.fruit = e.getFruit();
		this.growth = e.getGrowth();
		this.isMedicinal = e.getIsMedicinal();
		this.isOrnamental = e.getIsOrnamental();
		this.rootDepth = e.getRootDepth();
	}

}