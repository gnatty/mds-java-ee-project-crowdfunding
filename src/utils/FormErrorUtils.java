package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import entity.FormErrorEntity;

public class FormErrorUtils {
	
	private List<FormErrorEntity> errors;
	private FormErrorUtils initFrom;

	public FormErrorUtils() {
		errors = new ArrayList<FormErrorEntity>();
	}
	
	public List<FormErrorEntity> getErrors() {
		return errors;
	}

	public void setErrors(List<FormErrorEntity> errors) {
		this.errors = errors;
	}
	
	public Object getInitFrom() {
		return initFrom;
	}

	public void setInitFrom(FormErrorUtils initFrom) {
		if(initFrom != null) {
			errors = initFrom.getErrors();
		}
	}

	public boolean isError() {
		return errors.size() > 0;
	}
	
	public int getTotalErrors() {
		return errors.size();
	}
	
	public void add(String name, String code, String message) {
		FormErrorEntity el = new FormErrorEntity(name, code, message);
		errors.add(el);
	}
	
	public boolean isErrorField(String name) {
		List<FormErrorEntity> res = errors
				.stream()
				.filter(filterByName(name))
				.collect(Collectors.toList());
		return res.size() > 0;
	}
	
	public String getMessage(String name, String code) {
		Optional<FormErrorEntity> res = errors.stream()
				.filter(filterByNameAndCode(name, code))
				.findFirst();
		if(res.isPresent())
			return res.get().getMessage();
		return "";
	}

	
	/**
	 ******************************
	 ********* PREDICATE **********
	 ******************************
	 */
	
	public Predicate<FormErrorEntity> filterByName(String name) {
		return (e) -> (e.getName().equals(name));
	}
	
	public Predicate<FormErrorEntity> filterByNameAndCode(String name, String code) {
		return (e) -> (e.getName().equals(name) && e.getCode().equals(code));
	}
	
}
