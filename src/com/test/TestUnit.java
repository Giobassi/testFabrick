package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;

public class TestUnit {
	
	// Test OK
	@Test
	public void testSaldo() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append("14537780");
			urlS.append("/balance");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Auth-Schema", "S2S");
			conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
			conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
			System.out.println("TestSaldo = " + conn.getResponseCode() + " - Atteso 200");
			assertEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
	// Test senza parametro
	@Test
	public void testSaldo1() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/balance");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Auth-Schema", "S2S");
			conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
			conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
			System.out.println("TestSaldo1 = " + conn.getResponseCode() + " - Atteso 403");
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
    
	// Test senza Auth
	@Test
	public void testSaldo2() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append("14537780");
			urlS.append("/balance");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			System.out.println("TestSaldo2 = " + conn.getResponseCode()  + " - Atteso 403");
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
	// Test OK
	@Test
	public void testListaTransazioni() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append("14537780");
			urlS.append("/transactions?fromAccountingDate=");
			urlS.append("2019-01-01");
			urlS.append("&toAccountingDate=");
			urlS.append("2019-12-01");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Auth-Schema", "S2S");
			conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
			conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
			System.out.println("TestListaTransazioni = " + conn.getResponseCode() + " - Atteso 200");
			assertEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}

	// Test con parametri errati
	@Test
	public void testListaTransazioni1() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append("14537780");
			urlS.append("/transactions?fromAccountingDate=");
			urlS.append("2019/01/01");
			urlS.append("&toAccountingDate=");
			urlS.append("2019/12/01");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Auth-Schema", "S2S");
			conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
			conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
			System.out.println("testListaTransazioni1 = " + conn.getResponseCode() + " - Atteso 400");
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
	// Test senza Auth
	@Test
	public void testListaTransazioni2() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append("14537780");
			urlS.append("/transactions?fromAccountingDate=");
			urlS.append("2019-01-01");
			urlS.append("&toAccountingDate=");
			urlS.append("2019-12-01");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			System.out.println("testListaTransazioni2 = " + conn.getResponseCode() + " - Atteso 403");
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
	// Test OK - Ci sono problemi con questo servizio, la documentazione non è aggiornata
	@Test
	public void testBonifico() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append("14537780");
			urlS.append("/payments/money-transfers");
			URL url = new URL(urlS.toString());
			
	        HttpURLConnection conn = (HttpURLConnection)  url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");       
	        conn.setRequestProperty("Auth-Schema", "S2S");
	        conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
	        conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            
            String body = "{\r\n" + 
            		"  \"creditor\": {\r\n" + 
            		"    \"name\": \"John Doe\",\r\n" + 
            		"    \"account\": {\r\n" + 
            		"      \"accountCode\": \"IT23A0336844430152923804660\",\r\n" + 
            		"      \"bicCode\": \"SELBIT2BXXX\"\r\n" + 
            		"    },\r\n" + 
            		"    \"address\": {\r\n" + 
            		"      \"address\": null,\r\n" + 
            		"      \"city\": null,\r\n" + 
            		"      \"countryCode\": null\r\n" + 
            		"    }\r\n" + 
            		"  },\r\n" + 
            		"  \"executionDate\": \"2019-04-01\",\r\n" + 
            		"  \"uri\": \"REMITTANCE_INFORMATION\",\r\n" + 
            		"  \"description\": \"Payment invoice 75/2017\",\r\n" + 
            		"  \"amount\": 800,\r\n" + 
            		"  \"currency\": \"EUR\",\r\n" + 
            		"  \"isUrgent\": false,\r\n" + 
            		"  \"isInstant\": false,\r\n" + 
            		"  \"feeType\": \"SHA\",\r\n" + 
            		"  \"feeAccountId\": \"45685475\",\r\n" + 
            		"  \"taxRelief\": {\r\n" + 
            		"    \"taxReliefId\": \"L449\",\r\n" + 
            		"    \"isCondoUpgrade\": false,\r\n" + 
            		"    \"creditorFiscalCode\": \"56258745832\",\r\n" + 
            		"    \"beneficiaryType\": \"NATURAL_PERSON\",\r\n" + 
            		"    \"naturalPersonBeneficiary\": {\r\n" + 
            		"      \"fiscalCode1\": \"MRLFNC81L04A859L\",\r\n" + 
            		"      \"fiscalCode2\": null,\r\n" + 
            		"      \"fiscalCode3\": null,\r\n" + 
            		"      \"fiscalCode4\": null,\r\n" + 
            		"      \"fiscalCode5\": null\r\n" + 
            		"    },\r\n" + 
            		"    \"legalPersonBeneficiary\": {\r\n" + 
            		"      \"fiscalCode\": null,\r\n" + 
            		"      \"legalRepresentativeFiscalCode\": null\r\n" + 
            		"    }\r\n" + 
            		"  }\r\n" + 
            		"}";
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            
            System.out.println("testBonifico = " + conn.getResponseCode() + " - Atteso 200");
			assertEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
	// Test senza Auth
	@Test
	public void testBonifico1() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append("14537780");
			urlS.append("/payments/money-transfers");
			URL url = new URL(urlS.toString());
			
	        HttpURLConnection conn = (HttpURLConnection)  url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            
            String body = "{\r\n" + 
            		"  \"creditor\": {\r\n" + 
            		"    \"name\": \"John Doe\",\r\n" + 
            		"    \"account\": {\r\n" + 
            		"      \"accountCode\": \"IT23A0336844430152923804660\",\r\n" + 
            		"      \"bicCode\": \"SELBIT2BXXX\"\r\n" + 
            		"    },\r\n" + 
            		"    \"address\": {\r\n" + 
            		"      \"address\": null,\r\n" + 
            		"      \"city\": null,\r\n" + 
            		"      \"countryCode\": null\r\n" + 
            		"    }\r\n" + 
            		"  },\r\n" + 
            		"  \"executionDate\": \"2019-04-01\",\r\n" + 
            		"  \"uri\": \"REMITTANCE_INFORMATION\",\r\n" + 
            		"  \"description\": \"Payment invoice 75/2017\",\r\n" + 
            		"  \"amount\": 800,\r\n" + 
            		"  \"currency\": \"EUR\",\r\n" + 
            		"  \"isUrgent\": false,\r\n" + 
            		"  \"isInstant\": false,\r\n" + 
            		"  \"feeType\": \"SHA\",\r\n" + 
            		"  \"feeAccountId\": \"45685475\",\r\n" + 
            		"  \"taxRelief\": {\r\n" + 
            		"    \"taxReliefId\": \"L449\",\r\n" + 
            		"    \"isCondoUpgrade\": false,\r\n" + 
            		"    \"creditorFiscalCode\": \"56258745832\",\r\n" + 
            		"    \"beneficiaryType\": \"NATURAL_PERSON\",\r\n" + 
            		"    \"naturalPersonBeneficiary\": {\r\n" + 
            		"      \"fiscalCode1\": \"MRLFNC81L04A859L\",\r\n" + 
            		"      \"fiscalCode2\": null,\r\n" + 
            		"      \"fiscalCode3\": null,\r\n" + 
            		"      \"fiscalCode4\": null,\r\n" + 
            		"      \"fiscalCode5\": null\r\n" + 
            		"    },\r\n" + 
            		"    \"legalPersonBeneficiary\": {\r\n" + 
            		"      \"fiscalCode\": null,\r\n" + 
            		"      \"legalRepresentativeFiscalCode\": null\r\n" + 
            		"    }\r\n" + 
            		"  }\r\n" + 
            		"}";
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            
            System.out.println("testBonifico1 = " + conn.getResponseCode() + " - Atteso 403");
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
	// Test con parametri errati (Data formattata diversamete)
	@Test
	public void testBonifico2() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append("14537780");
			urlS.append("/payments/money-transfers");
			URL url = new URL(urlS.toString());
			
	        HttpURLConnection conn = (HttpURLConnection)  url.openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Auth-Schema", "S2S");
	        conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
	        conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            
            String body = "{\r\n" + 
            		"  \"creditor\":{\r\n" + 
            		"    \"name\":\"John Doe\",\r\n" + 
            		"    \"account\":{\r\n" + 
            		"      \"accountCode\":\"IT23A0336844430152923804660\",\r\n" + 
            		"      \"bicCode\": \"SELBIT2BXXX\"\r\n" + 
            		"    }\r\n" + 
            		"  },\r\n" + 
            		"  \"executionDate\":\"2019/04/01\",\r\n" + 
            		"  \"description\":\"Payment invoice\",\r\n" + 
            		"  \"amount\":\"800\",\r\n" + 
            		"  \"currency\":\"EUR\"\r\n" + 
            		"}";
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            
            System.out.println("testBonifico2 = " + conn.getResponseCode() + " - Atteso 400");
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
}