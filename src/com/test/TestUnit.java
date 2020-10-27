package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;
import java.net.HttpURLConnection;
import java.net.URL;
import org.junit.Test;

public class TestUnit {
	
	@Test
	public void testSaldo() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts");
			urlS.append("?accountId=");
			urlS.append("14537780");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Auth-Schema", "S2S");
			conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
			conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
			System.out.println("TestSaldo = " + conn.getResponseCode());
			assertEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
	@Test
	public void testSaldo1() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Auth-Schema", "S2S");
			conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
			conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
			System.out.println("TestSaldo1 = " + conn.getResponseCode());
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}

	@Test
	public void testSaldo2() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			System.out.println("TestSaldo2 = " + conn.getResponseCode());
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
	@Test
	public void testListaTransazioni() {
		try {
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts");
			URL url = new URL(urlS.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			System.out.println("TestSaldo2 = " + conn.getResponseCode());
			assertNotEquals(conn.getResponseCode(), 200);
		} catch (Exception e) {
			fail("Errore connessione");
		}
	}
	
}