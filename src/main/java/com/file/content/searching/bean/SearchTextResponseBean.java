package com.file.content.searching.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchTextResponseBean {

	@JsonProperty("searchText")
	private String searchText;
	
	@JsonProperty("positionDetailsBeanList")
	private List<PositionDetailsBean> positionDetailsBeanList;

}
