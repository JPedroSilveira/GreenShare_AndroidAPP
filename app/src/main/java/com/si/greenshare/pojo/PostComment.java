package com.si.greenshare.pojo;

import java.io.Serializable;

/**
 * Created by joao.silva.
 */
public class PostComment extends AbstractEntity<PostComment> implements Serializable {

	private Long id;

	private String text;

	private User user;

	protected PostComment() {
		super();
	}

	public PostComment(String text, User user, Post post) {
		super();
		this.text = text;
		this.user = user;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
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

		return this.validationErrors.isEmpty();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return this.user;
	}

	@Override
	public void update(PostComment e) {
		this.text = e.getText();
	}

}
