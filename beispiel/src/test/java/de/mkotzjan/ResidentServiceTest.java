package de.mkotzjan;

import org.junit.Test;
import static org.junit.Assert.*;

import static org.easymock.EasyMock.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import de.mkotzjan.informatik.meldeauskunft.domain.*;
import de.mkotzjan.informatik.meldeauskunft.repository.*;
import de.mkotzjan.informatik.meldeauskunft.service.*;

public class ResidentServiceTest {
	
	@Test
	public void testGetFilteredResidentsList1()
	{
		List<Resident> list = new LinkedList<Resident>();
		ResidentRepository stub = new ResidentRepositoryStub(list);
		
		ResidentService rs = new BaseResidentService();
		((BaseResidentService) rs).setResidentRepository(stub);
		List<Resident> result = rs.getFilteredResidentsList(new Resident("*", "*", "*", "", new Date()));
		
		assertEquals(0, result.size());
	}
	
	@Test
	public void testGetFilteredResidentsList2()
	{
		List<Resident> list = new LinkedList<Resident>();
		Resident r = new Resident();
		r.setGivenName("Max");
		r.setFamilyName("Mustermann");
		r.setStreet("Musterstraﬂe");
		r.setCity("Musterstadt");
		r.setDateOfBirth(new Date());
		list.add(r);
		list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
		list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date()));
		ResidentRepository stub = new ResidentRepositoryStub(list);
		
		ResidentService rs = new BaseResidentService();
		((BaseResidentService) rs).setResidentRepository(stub);
		List<Resident> result = rs.getFilteredResidentsList(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
		
		assertEquals(1, result.size());
		assertEquals("Martina", result.get(0).getGivenName());
		assertEquals("Mustermann", result.get(0).getFamilyName());
		assertEquals("Albertstraﬂe", result.get(0).getStreet());
		assertEquals("Musterdorf", result.get(0).getCity());
	}
	
	@Test
	public void testGetFilteredResidentsList3()
	{
		List<Resident> list = new LinkedList<Resident>();
		list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date()));
		list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
		list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date()));
		ResidentRepository stub = new ResidentRepositoryStub(list);
		
		ResidentService rs = new BaseResidentService();
		((BaseResidentService) rs).setResidentRepository(stub);
		List<Resident> result = rs.getFilteredResidentsList(new Resident("Ma*", "Mu*", "*straﬂe", "Muster*", new Date()));
		
		assertEquals(2, result.size());

		assertEquals("Max", result.get(0).getGivenName());
		assertEquals("Mustermann", result.get(0).getFamilyName());
		assertEquals("Musterstraﬂe", result.get(0).getStreet());
		assertEquals("Musterstadt", result.get(0).getCity());
		assertEquals("Martina", result.get(1).getGivenName());
		assertEquals("Mustermann", result.get(1).getFamilyName());
		assertEquals("Albertstraﬂe", result.get(1).getStreet());
		assertEquals("Musterdorf", result.get(1).getCity());
	}
	
	@Test
	public void testGetUniqueResident1()
	{
		List<Resident> list = new LinkedList<Resident>();
		ResidentRepository stub = new ResidentRepositoryStub(list);
		
		ResidentService rs = new BaseResidentService();
		((BaseResidentService) rs).setResidentRepository(stub);
		
		try {
			Resident result = rs.getUniqueResident(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", null));
			fail("getUniqueResident on empty List should throw exception");
		} catch (ResidentServiceException e) {
			assertEquals("Suchanfrage lieferte kein eindeutiges Ergebnis!", e.getMessage());
		}
	}
	
	@Test
	public void testGetUniqueResident2()
	{
		List<Resident> list = new LinkedList<Resident>();
		ResidentRepository stub = new ResidentRepositoryStub(list);
		
		ResidentService rs = new BaseResidentService();
		((BaseResidentService) rs).setResidentRepository(stub);
		
		try {
			Resident result = rs.getUniqueResident(new Resident("*", "*", "*", "*", null));
			fail("getUniqueResident with should throw exception");
		} catch (ResidentServiceException e) {
			assertEquals("Wildcards (*) sind nicht erlaubt!", e.getMessage());
		}
	}
	
	@Test
	public void testGetUniqueResident3()
	{
		List<Resident> list = new LinkedList<Resident>();
		list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date()));
		list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
		list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date()));
		ResidentRepository stub = new ResidentRepositoryStub(list);
		
		ResidentService rs = new BaseResidentService();
		((BaseResidentService) rs).setResidentRepository(stub);
		
		try {
			Resident result = rs.getUniqueResident(new Resident("Max", "Mustermann", null, null, null));
			assertEquals("Max", result.getGivenName());
			assertEquals("Mustermann", result.getFamilyName());
			assertEquals("Musterstraﬂe", result.getStreet());
			assertEquals("Musterstadt", result.getCity());
		} catch (ResidentServiceException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUniqueResidentMock()
	{
		List<Resident> list = new LinkedList<Resident>();
		list.add(new Resident("Max", "Mustermann", "Musterstraﬂe", "Musterstadt", new Date()));
		list.add(new Resident("Martina", "Mustermann", "Albertstraﬂe", "Musterdorf", new Date()));
		list.add(new Resident("Emil", "Dick", "Waldweg", "Musterdorf", new Date()));
		
		ResidentRepository mock = createMock(ResidentRepository.class);
		expect(mock.getResidents()).andReturn(list);
		replay(mock);
		
		ResidentService rs = new BaseResidentService();
		((BaseResidentService) rs).setResidentRepository(mock);
		
		try {
			Resident result = rs.getUniqueResident(new Resident("Max", "Mustermann", null, null, null));
			assertEquals("Max", result.getGivenName());
			assertEquals("Mustermann", result.getFamilyName());
			assertEquals("Musterstraﬂe", result.getStreet());
			assertEquals("Musterstadt", result.getCity());
		} catch (ResidentServiceException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		verify(mock);
	}
}
