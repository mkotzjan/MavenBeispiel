package de.mkotzjan.informatik.meldeauskunft.repository;

import java.util.List;

import de.mkotzjan.informatik.meldeauskunft.domain.Resident;

/**
 * @author Stefan Betermieux
 */
public interface ResidentRepository {

  List<Resident> getResidents();

}