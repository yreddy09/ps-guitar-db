package com.guitar.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.guitar.db.model.Location;
import com.guitar.db.repository.LocationJpaRepository;
import com.guitar.db.repository.LocationRepository;

@ContextConfiguration(locations={"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class LocationPersistenceTests {
	@Autowired
	private LocationRepository locationRepository;

	@Autowired
	private LocationJpaRepository locationJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	public void testJpaFind() {
		List<Location> locations = locationJpaRepository.findAll();
		assertNotNull(locations);
	}

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Location location = new Location();
		location.setCountry("Canada");
		location.setState("British Columbia");
		location = locationJpaRepository.saveAndFlush(location);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		Location otherLocation = locationJpaRepository.findOne(location.getId());
		assertEquals("Canada", otherLocation.getCountry());
		assertEquals("British Columbia", otherLocation.getState());
		
		//delete BC location now
		locationJpaRepository.delete(otherLocation);
	}

	/*
	 * @Test public void testFindWithLike() throws Exception { List<Location>
	 * locs = locationJpaRepository.findByStateLike("New%"); for (Location
	 * location : locs) { System.out.println("New Location : " +
	 * location.getCountry() + " : " + location.getState()); }
	 * 
	 * Location myLocation =
	 * locationJpaRepository.findByStateAndCountry("Texas", "United States"); if
	 * (myLocation != null) { System.out.println("Texas : " +
	 * myLocation.getCountry() + " : " + myLocation.getState()); } else {
	 * System.out.println("Location is null"); }
	 * 
	 * List<Location> myLocations1 =
	 * locationJpaRepository.findByStateOrCountry("Texas", ""); for (Location
	 * location : myLocations1) { System.out.println("Location OR : " +
	 * location.getCountry() + " : " + location.getState()); }
	 * 
	 * assertEquals(4, locs.size());
	 * 
	 * locs = locationJpaRepository.findByStateNotLike("New%"); assertEquals(46,
	 * locs.size());
	 * 
	 * }
	 * 
	 * @Test public void testFindStartingWith() throws Exception {
	 * List<Location> locs =
	 * locationJpaRepository.findByStateStartingWith("New"); for (Location
	 * location : locs) { System.out.println("New Location : " +
	 * location.getCountry() + " : " + location.getState()); } assertEquals(4,
	 * locs.size()); }
	 * 
	 * @Test public void testFindWithAnd() throws Exception { Location
	 * myLocation = locationJpaRepository.findByStateAndCountry("Texas",
	 * "United States"); if (myLocation != null) { System.out.println("Texas : "
	 * + myLocation.getCountry() + " : " + myLocation.getState()); } else {
	 * System.out.println("Location is null"); }
	 * 
	 * List<Location> myLocations1 =
	 * locationJpaRepository.findByStateOrCountry("Texas", ""); for (Location
	 * location : myLocations1) { System.out.println("Location OR : " +
	 * location.getCountry() + " : " + location.getState()); }
	 * 
	 * assertEquals("Texas", myLocation.getState()); }
	 * 
	 * @Test public void testFindWithOr() throws Exception {
	 * 
	 * List<Location> myLocations1 =
	 * locationJpaRepository.findByStateOrCountry("", "United States"); for
	 * (Location location : myLocations1) { System.out.println("Location OR : "
	 * + location.getCountry() + " : " + location.getState()); }
	 * 
	 * assertEquals(50, myLocations1.size()); }
	 * 
	 * @Test public void testFindStateIs() throws Exception {
	 * 
	 * List<Location> myLocations1 =
	 * locationJpaRepository.findByStateIs("New Jersey"); for (Location location
	 * : myLocations1) { System.out.println("State is New  Jersy : " +
	 * location.getCountry() + " : " + location.getState()); }
	 * 
	 * assertEquals("New Jersey", myLocations1.get(0).getState()); }
	 * 
	 * @Test public void testFindState() throws Exception {
	 * 
	 * List<Location> myLocations1 =
	 * locationJpaRepository.findByState("New Jersey"); for (Location location :
	 * myLocations1) { System.out.println("State New  Jersy : " +
	 * location.getCountry() + " : " + location.getState()); }
	 * 
	 * assertEquals("New Jersey", myLocations1.get(0).getState()); }
	 * 
	 * @Test public void testFindStateEquals() throws Exception {
	 * 
	 * List<Location> myLocations1 =
	 * locationJpaRepository.findByState("New Jersey"); for (Location location :
	 * myLocations1) { System.out.println("State Equals New  Jersy : " +
	 * location.getCountry() + " : " + location.getState()); }
	 * 
	 * assertEquals("New Jersey", myLocations1.get(0).getState()); }
	 * 
	 * @Test public void testFindStateOrderBy() throws Exception {
	 * 
	 * List<Location> myLocations1 =
	 * locationJpaRepository.findByState("New Jersey"); for (Location location :
	 * myLocations1) { System.out.println("State Equals New  Jersy : " +
	 * location.getCountry() + " : " + location.getState()); }
	 * 
	 * assertEquals("New Jersey", myLocations1.get(0).getState()); }
	 */
	@Test
	@Transactional  //note this is needed because we will get a lazy load exception unless we are in a tx
	public void testFindWithChildren() throws Exception {
		Location arizona = locationJpaRepository.findOne(3L);
		assertEquals("United States", arizona.getCountry());
		assertEquals("Arizona", arizona.getState());
		
		assertEquals(1, arizona.getManufacturers().size());
		
		assertEquals("Fender Musical Instruments Corporation", arizona.getManufacturers().get(0).getName());
	}

	@Test
	@Transactional // note this is needed because we will get a lazy load
					// exception unless we are in a tx
	public void testFirstByStateIgnoreCaseStartingWith() throws Exception {
		Location arizona = locationJpaRepository.findFirstByStateIgnoreCaseStartingWith("New");
		assertEquals("United States", arizona.getCountry());
		assertEquals("Arizona", arizona.getState());
	}
}
