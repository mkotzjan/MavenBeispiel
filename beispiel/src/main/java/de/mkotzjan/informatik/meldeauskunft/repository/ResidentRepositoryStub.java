package de.mkotzjan.informatik.meldeauskunft.repository;

import java.util.List;

import de.mkotzjan.informatik.meldeauskunft.domain.Resident;

public class ResidentRepositoryStub implements ResidentRepository {
	
	private List<Resident> residents;
	
	public ResidentRepositoryStub(List<Resident> list)
	{
		residents = list;
	}
	
	public List<Resident> getResidents() {
		return residents;
	}
}
