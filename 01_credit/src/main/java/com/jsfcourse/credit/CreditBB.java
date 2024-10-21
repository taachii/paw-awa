package com.jsfcourse.credit;

import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class CreditBB {
	private String amount;
	private String years;
	private String interestRate;
	private Double monthlyPayment;

	@Inject
	FacesContext ctx;

	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public Double getMonthlyPayment() {
		return monthlyPayment;
	}

	public boolean doTheMath() {
		try {
			Double amount = Double.parseDouble(this.amount);
			Double years = Double.parseDouble(this.years);
			Double interestRate = Double.parseDouble(this.interestRate);
			
			monthlyPayment = amount*(1 + interestRate/100)/(years*12);
			monthlyPayment = Double.parseDouble(String.format("%.2f", monthlyPayment));

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}

	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + monthlyPayment, null));
		}
		return null;
	}
}
