package com.file.content.searching.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionDetailsBean {

	@JsonProperty("lineNumber")
	private Integer lineNumber;
	
	@JsonProperty("startingPosition")
	private Integer startingPosition;
	
	@JsonProperty("endingPosition")
	private Integer endingPosition;
}
