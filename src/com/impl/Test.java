package com.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Test {
	public static void main(String[] args) {
		saldo("1453778");
		//transfers();
		//listaTransazioni();
	}

	public static void saldo(String accounts) {
		try{
			StringBuilder urlS = new StringBuilder("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/");
			urlS.append(accounts);
			urlS.append("/balance");
			URL url = new URL(urlS.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Auth-Schema", "S2S");
            conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
            conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Errore : " + conn.getResponseCode());
            }
            
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            StringBuilder risposta = new StringBuilder(""); 
            String output;
            while ((output = br.readLine()) != null) {
            	risposta.append(output);
            }
            
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(risposta.toString());
            JSONObject obj = (JSONObject) data_obj.get("payload");
            System.out.println("Saldo : " + obj.get("balance"));
            
            conn.disconnect();
		} catch (Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
		
	public static void transfers() {
		try{
			URL url = new URL("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/payments/money-transfers");
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
            		"  \"executionDate\":\"2019-04-01\",\r\n" + 
            		"  \"description\":\"Payment invoice\",\r\n" + 
            		"  \"amount\":800,\r\n" + 
            		"  \"currency\":\"EUR\"\r\n" + 
            		"}";
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            
            if(conn.getResponseCode() == 400) {
            	InputStream errorstream = conn.getErrorStream();
            	String response = "";
            	String line;
            	BufferedReader br = new BufferedReader(new InputStreamReader(errorstream));
            	while ((line = br.readLine()) != null) {
            	    response += line;
            	}
            	
            	System.out.println(response);
            } else if (conn.getResponseCode() != 200 && conn.getResponseCode() != 400) {
            	throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            } else {
            	InputStreamReader in = new InputStreamReader(conn.getInputStream());
            	BufferedReader br = new BufferedReader(in);
            	String output;
            	while ((output = br.readLine()) != null) {
            		System.out.println(output);
            	}
            }
            conn.disconnect();
            
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error : " + e.getMessage());
		}
	}
	
	public static void listaTransazioni() {
		try{
			URL url = new URL("https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/14537780/transactions?fromAccountingDate=2019-01-01&toAccountingDate=2019-12-01");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Auth-Schema", "S2S");
            conn.setRequestProperty("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
            conn.setRequestProperty("X-Time-Zone", "Europe/Rome");
                    
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
		}catch (Exception e) {
			System.out.println("Error listaTransazioni");
		}
	}
}