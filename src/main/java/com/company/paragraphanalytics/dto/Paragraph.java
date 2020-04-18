package com.company.paragraphanalytics.dto;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class Paragraph {

	@Size(min = 1)
	private String paragraph;

	public Paragraph(String paragraph) {
		// TODO Auto-generated constructor stub
		this.paragraph = paragraph;
	}

	public Paragraph() {

	}

	public String getParagraph() {
		return paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return paragraph;
	}

}
