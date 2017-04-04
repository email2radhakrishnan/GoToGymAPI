package com.gotogym.dao;

import java.util.List;

import com.gotogym.error.ApplicationException;
import com.gotogym.model.Plan;

public interface PlanDAO {

	List<Plan> getAllPlans() throws ApplicationException;

	Plan getPlanByName(String planName) throws ApplicationException;

}
