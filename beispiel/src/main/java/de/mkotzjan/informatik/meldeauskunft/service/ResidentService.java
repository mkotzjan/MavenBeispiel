package de.mkotzjan.informatik.meldeauskunft.service;

import java.util.List;

import de.mkotzjan.informatik.meldeauskunft.domain.Resident;
import de.mkotzjan.informatik.meldeauskunft.repository.ResidentRepository;

/**
 * @author Stefan Betermieux
 */
public interface ResidentService {

  Resident getUniqueResident(Resident filterResident) throws ResidentServiceException;

  List<Resident> getFilteredResidentsList(Resident filterResident);

  void setResidentRepository(ResidentRepository stub);
}