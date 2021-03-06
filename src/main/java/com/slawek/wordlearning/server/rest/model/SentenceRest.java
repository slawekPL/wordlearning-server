package com.slawek.wordlearning.server.rest.model;

public class SentenceRest {
	private String sentence;
	private String translation;

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public String getTranslation() {
		return translation;
	}

	public void setTranslation(String translation) {
		this.translation = translation;
	}

	@Override public String toString() {
		return "SentenceRest{" +
				"sentence='" + sentence + '\'' +
				", translation='" + translation + '\'' +
				'}';
	}
}
