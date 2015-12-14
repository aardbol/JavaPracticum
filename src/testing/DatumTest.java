package testing;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import model.datum.Datum;
//import model.gregorian.Datum;


public class DatumTest {

	private Datum datum, datumVandaag, datumMinEenJaar, datumMinTienJaar, datumVandaagConstructor,
	datumMinTweeHonderdJaar, datumMetSchrikkeljaar, datumPlusTienJaar, datumPlusEenJaar,
	datumPlusEenMaand, datumPlusNegenMaanden, datumPlusDertienMaanden, datumMinVijfMaanden, datumVandaagDate;

	@Before
	public void setUp() throws Exception
	{
		datum = new Datum(1, 1, 2015);
		datumVandaagDate = new Datum(new Date());
		datumVandaagConstructor = new Datum();
		datumVandaag = new Datum(19, 10, 2015);
		datumMinEenJaar = new Datum(1, 1, 2014);
		datumMinTienJaar = new Datum(1, 1, 2005);
		datumMetSchrikkeljaar = new Datum(1, 1, 2000);
		datumMinTweeHonderdJaar = new Datum(1, 1, 1815);
		datumPlusEenJaar = new Datum(1, 1, 2016);
		datumPlusTienJaar = new Datum(1, 1, 2025);
		datumPlusEenMaand = new Datum(1, 2, 2015);
		datumPlusNegenMaanden = new Datum(1, 10, 2015);
		datumPlusDertienMaanden = new Datum(1, 2, 2016);
		datumMinVijfMaanden = new Datum(1, 8, 2014);
	}
	
	@Test
	public void test_toString_GeeftJuisteTerug()
	{
		assertEquals("1 januari 2015", datum.toString());
	}

	@Test(expected = NullPointerException.class)
	public void test_kleinerDanAlsParameterNull_GeeftException()
	{
		datum.kleinerDan(null);
	}
	
	@Test
	public void test_kleinerDanAlsDatumGelijk_GeeftFalse()
	{
		assertFalse(datum.kleinerDan(datum));
	}
	
	@Test
	public void test_kleinerDanAlsParameterGroterDan_GeeftFalse()
	{
		assertFalse(datum.kleinerDan(datumVandaag));
		assertFalse(datum.kleinerDan(datumPlusTienJaar));
	}
	
	@Test
	public void test_kleinerDanAlsParameterKleinerDan_GeeftTrue()
	{
		assertTrue(datum.kleinerDan(datumMinEenJaar));
		assertTrue(datum.kleinerDan(datumMinTienJaar));
		assertTrue(datum.kleinerDan(datumMetSchrikkeljaar));
		assertTrue(datum.kleinerDan(datumMinTweeHonderdJaar));
	}
	
	@Test
	public void test_getDatumInEuropeesFormaat_GelijkAan()
	{
		assertEquals("Europees formaat", "01/01/2015", datum.getDatumInEuropeesFormaat());
		assertEquals("Europees formaat", "19/10/2015", datumVandaag.getDatumInEuropeesFormaat());
	}

	@Test
	public void test_getDatumInAmerikaansFormaat_GelijkAan()
	{
		assertEquals("Amerikaans formaat", "2015/01/01", datum.getDatumInAmerikaansFormaat());
		assertEquals("Amerikaans formaat", "2015/10/19", datumVandaag.getDatumInAmerikaansFormaat());
	}
	
	@Test
	public void test_verschilInMaandenEenMaandVerschil_GeeftEen() throws Exception
	{
		assertEquals(1, datum.verschilInMaanden(datumPlusEenMaand));
	}
	
	@Test
	public void test_verschilInMaandenNegenMaandenVerschil_GeeftNegen() throws Exception
	{
		assertEquals(9, datum.verschilInMaanden(datumPlusNegenMaanden));
	}
	
	@Test
	public void test_verschilInMaandenDertienMaandenVerschil_GeeftDertien() throws Exception
	{
		assertEquals(13, datum.verschilInMaanden(datumPlusDertienMaanden));
	}
	
	@Test
	public void test_verschilinMaandenMinVijfMaandenVerschil_GeeftVijf() throws Exception
	{
		assertEquals(5, datum.verschilInMaanden(datumMinVijfMaanden));
	}
	
	@Test
	public void test_verschilInJarenEenJaarVerschil_GeeftEen() throws Exception
	{
		assertEquals(1, datum.verschilInJaren(datumPlusEenJaar));
	}
	
	@Test
	public void test_verschilInJarenTienJaarVerschil_GeeftTien() throws Exception
	{
		assertEquals(10, datum.verschilInJaren(datumPlusTienJaar));
	}
	
	@Test
	public void test_verschilInJarenMinEenJaarVerschil_GeeftEen() throws Exception
	{
		assertEquals(1, datum.verschilInJaren(datumMinEenJaar));
	}
	
	@Test
	public void test_getJaarDatumIntParameters_Geeft2015Terug()
	{
		assertEquals(2015, datum.getJaar());
	}
	
	@Test
	public void test_getJaarDatumParameterDate_GeeftDitJaarTerug()
	{
		assertEquals((new Date()).getYear() + 1900, datumVandaagDate.getJaar());
	}
	
	@Test
	public void test_getJaarDatumConstructor_GeeftDitJaarTerug()
	{
		assertEquals((new Date()).getYear() + 1900, datumVandaagConstructor.getJaar());
	}
}
