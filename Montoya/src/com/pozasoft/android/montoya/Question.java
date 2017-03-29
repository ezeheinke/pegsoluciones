package com.pozasoft.android.montoya;

import java.util.List;

public class Question {

	private String question;
	private List<String> choices;
	private String correctAnswer;
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<String> getChoices() {
		return choices;
	}
	public void setChoices(List<String> choices) {
		this.choices = choices;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public String getCorrectAnswerText() {		
		return choices.get(Integer.parseInt(correctAnswer));
	}
}
