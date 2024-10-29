package com.jsfcourse.credit;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
public class CreditBB {
	private Integer amount;
	private Integer years;
	private Double interestRate;
	private Double monthlyPayment;

	@Inject
	FacesContext ctx;

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getYears() {
		return years;
	}

	public void setYears(Integer years) {
		this.years = years;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	public void calculateMonthlyPayment() {
		monthlyPayment = amount*(1 + interestRate/100)/(years*12);
		monthlyPayment = (double) Math.round(monthlyPayment*100)/100;
		
		String formattedPayment = monthlyPayment + " zł";
		
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie!", null));
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Miesięczna rata wynosi: " + formattedPayment, null));
	}

	public String calc() {
		calculateMonthlyPayment();		
		return "showresult";
	}

	public String calc_AJAX() {
		calculateMonthlyPayment();
		return null;
	}
}
