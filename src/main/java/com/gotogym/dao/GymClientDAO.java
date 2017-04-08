package com.gotogym.dao;

import java.util.Date;
import java.util.List;

import com.gotogym.error.ApplicationException;
import com.gotogym.model.GymClient;

public interface GymClientDAO {

	public List<GymClient> getAllGymClient() throws ApplicationException;

	public List<GymClient> getGymClientByName(String clientName) throws ApplicationException;

	public GymClient getGymClientByPhone(Long phone) throws ApplicationException;

	public List<GymClient> getGymClientByEmail(String email) throws ApplicationException;

	public List<GymClient> getGymClientByCity(String city) throws ApplicationException;

	public List<GymClient> getGymClientByRegDate(Date regDate) throws ApplicationException;

	public void registerGymClient(GymClient gymClient) throws ApplicationException;

	public void updateClientPassword(byte[] passHash, Long phone) throws ApplicationException;

}
