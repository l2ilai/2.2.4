package com.example._223.service;

import com.example._223.properties.LoanProperties;

import com.example.starter.incomeclient.service.IncomeClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LoanCalculation {

    @Autowired
    private LoanProperties loanProperties;

    @Autowired
    private IncomeClient incomeClient;

    @Autowired
    private CarService carService;

    private boolean isApprovedLoan(Integer incomeUser, Integer priceCar) {
        return incomeUser > loanProperties.getMinimalIncome() ||
                priceCar > loanProperties.getPriceCar();
    }

    private double calculateMaxAmountLoan(Integer incomeUser, Integer priceCar) {
        double halfAnnualIncome = incomeUser * 6.0;
        double thirtyPercentCostCar = priceCar * 0.3;
        return Math.max(halfAnnualIncome, thirtyPercentCostCar);
    }

    public double getLoan(Long id) {
        Integer incomeUser = incomeClient.getIncomeUserOrZero(id);
        Integer priceCar = carService.getPriceCarOrZero(id);
        if (isApprovedLoan(incomeUser, priceCar)) {
            return calculateMaxAmountLoan(incomeUser, priceCar);
        }
        return 0;
    }
}
