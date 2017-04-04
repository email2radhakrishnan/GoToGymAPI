package com.gotogym.dao;

import java.util.Date;
import java.util.List;

import com.gotogym.error.ApplicationException;
import com.gotogym.model.GymLogs;

public interface GymLogsDAO {

	public List<GymLogs> getLogsByClientName(String clientName) throws ApplicationException;

	public List<GymLogs> getLogsByUserName(String userName) throws ApplicationException;

	public List<GymLogs> getLogsByPlanName(String planName) throws ApplicationException;

	public List<GymLogs> getLogsByDate(Date logDate) throws ApplicationException;

	public List<GymLogs> getPaidLogs() throws ApplicationException;

	public List<GymLogs> getUnPaidLogs() throws ApplicationException;

}
