package com.gotogym.dao;

import java.util.Date;
import java.util.List;

import com.gotogym.error.ApplicationException;
import com.gotogym.model.PaymentDetails;

public interface PaymentDetailsDAO {

	List<PaymentDetails> getDetailsByClientName(String clientName) throws ApplicationException;

	List<PaymentDetails> getDetailsByDate(Date payDate) throws ApplicationException;

}
