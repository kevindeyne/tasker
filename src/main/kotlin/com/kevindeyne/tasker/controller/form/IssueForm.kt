package com.kevindeyne.tasker.controller.form

import com.fasterxml.jackson.annotation.JsonCreator

data class IssueForm @JsonCreator constructor(
		val title: String,
		val description: String
){
	
	fun validate() : Map<String, String> = HashMap<String, String>()	
	
}

 