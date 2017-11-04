package pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joao.silva.
 */
public class Post extends AbstractEntity<Post> implements Serializable {

	private Long id;

	private User user;

	private Species species;

	private String text;

	private Boolean hasImage;

	private List<PostComment> postComments;

	protected Post() {
		super();
	}

	public Post(User user, Species species, String text) {
		super();
		this.user = user;
		this.species = species;
		this.text = text;
		this.hasImage = false;
	}

	@Override
	public boolean isValid() {
		this.validationErrors.clear();

		if (isNullOrEmpty(this.text) || is(this.text).orSmallerThan(1).orBiggerThan(500)) {
			this.validationErrors.add("Texto inválido.");
		}
		if (isNull(this.hasImage)) {
			this.validationErrors.add("Definição inválida para imagem.");
		}
		if (isNull(this.user)) {
			this.validationErrors.add("O usuário não pode ser nulo.");
		} else if (this.user.isNotValid()) {
			this.validationErrors.addAll(this.user.getValidationErrors());
		}
		if (isNotNull(this.species) && this.species.isNotValid()) {
			this.validationErrors.addAll(this.species.getValidationErrors());
		}
		return this.validationErrors.isEmpty();
	}

	public Long getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public Species getSpecies() {
		return this.species;
	}

	public void setSpecies(Species species) {
		this.species = species;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<PostComment> getPostComments() {
		return postComments;
	}

	public Boolean getHasImage() {
		return this.hasImage;
	}

	public void update(Post post) {
		this.text = post.getText();
		this.species = post.getSpecies();
	}

}
