package testing;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.gregorian.Datum;

public class testDatum {
	private Datum testSubject1;
	private Datum testSubject2;
	
	@Before
	public void setUp() throws Exception {
		testSubject1 = new Datum();
		testSubject2 = new Datum(1,2,2003);
	}

	@Test
	public void testDatum_EmptyConstructor_OK() {
		//fail("Not yet implemented");
		assertTrue(testSubject1!=null);
		assertTrue(testSubject1.getDatumInAmerikaansFormaat()!=null);
	}

	@Test
	public void testDatumDatum() {
		Datum test = new Datum(testSubject2);
		assertTrue( test != null );
		assertTrue( testSubject2 != null );
		assertTrue( test.getDatumInEuropeesFormaat().compareTo(testSubject2.getDatumInEuropeesFormaat()) == 0);
	}

	@Test
	public void testDatumIntIntInt() {
		//fail("Not yet implemented");
		assertTrue( testSubject2.getDatumInEuropeesFormaat().compareTo("1/2/2003") == 0);
	}

	@Test
	public void testDatumString() {
		Datum test = new Datum("09/10/1999");
		assertTrue(test.getDatumInEuropeesFormaat().compareTo("9/10/1999") == 0);
	}

	@Test
	public void testSetDatum() {
		Datum test = new Datum();
		test.setDatum(9, 10, 1999);
		assertTrue(test.getDatumInEuropeesFormaat().compareTo("9/10/1999") == 0);
	}

	@Test
	public void testGetDatumInAmerikaansFormaat() {
		assertTrue( testSubject2 != null);
		assertTrue( testSubject2.getDatumInAmerikaansFormaat().compareTo( "2/1/2003") == 0);
	}

	@Test
	public void testGetDatumInEuropeesFormaat() {
		assertTrue( testSubject2.getDatumInEuropeesFormaat().compareTo("1/2/2003") == 0);
	}

}
