package com.kevindeyne.tasker.service

import com.kevindeyne.tasker.nlp.OpenNLPProcessor
import org.tartarus.snowball.ext.EnglishStemmer

object KeywordGeneration {
	
	fun generateKeywords(text : String, lang : String) : Set<String> {
		val re = Regex("[^a-z]")
		val newText = text.toLowerCase().replace(re, " ").replace("\\s+".toRegex(), " ")
		var splitValues : Set<String> = OpenNLPProcessor.getInstance(lang).process(newText)
		val resultSet = HashSet<String>()

		val stemmer : EnglishStemmer = EnglishStemmer()
		
		for(splitValue in splitValues){
			val value = splitValue.replace(re, " ")
			if(value.length > 2 && isActiveWord(value)){
				stemmer.setCurrent(value)
				stemmer.stem()
				resultSet.add(stemmer.getCurrent())	
			}
		}
		
		return resultSet
	}
	
	fun isActiveWord(value : String) = !("http").equals(value)
	
}