package org.fondazioneits.academy.stub.soap.weather.clientsample;

import org.fondazioneits.academy.stub.soap.weather.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        GlobalWeather service1 = new GlobalWeather();
	        System.out.println("Create Web Service...");
	        GlobalWeatherSoap port1 = service1.getGlobalWeatherSoap();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.getCitiesByCountry("Italy"));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.getWeather("Aviano Usaf","Italy"));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Create Web Service...");
//	        GlobalWeatherSoap port2 = service1.getGlobalWeatherSoap();
//	        System.out.println("Call Web Service Operation...");
//	        System.out.println("Server said: " + port2.getCitiesByCountry(null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Server said: " + port2.getWeather(null,null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Create Web Service...");
//	        GlobalWeatherSoap port3 = service1.getGlobalWeatherSoap12();
//	        System.out.println("Call Web Service Operation...");
//	        System.out.println("Server said: " + port3.getCitiesByCountry(null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Server said: " + port3.getWeather(null,null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Create Web Service...");
//	        GlobalWeatherSoap port4 = service1.getGlobalWeatherSoap12();
//	        System.out.println("Call Web Service Operation...");
//	        System.out.println("Server said: " + port4.getCitiesByCountry(null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Server said: " + port4.getWeather(null,null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Create Web Service...");
//	        GlobalWeatherHttpPost port5 = service1.getGlobalWeatherHttpPost();
//	        System.out.println("Call Web Service Operation...");
//	        System.out.println("Server said: " + port5.getCitiesByCountry(null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Server said: " + port5.getWeather(null,null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Create Web Service...");
//	        GlobalWeatherHttpPost port6 = service1.getGlobalWeatherHttpPost();
//	        System.out.println("Call Web Service Operation...");
//	        System.out.println("Server said: " + port6.getCitiesByCountry(null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Server said: " + port6.getWeather(null,null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Create Web Service...");
//	        GlobalWeatherHttpGet port7 = service1.getGlobalWeatherHttpGet();
//	        System.out.println("Call Web Service Operation...");
//	        System.out.println("Server said: " + port7.getCitiesByCountry(null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Server said: " + port7.getWeather(null,null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Create Web Service...");
//	        GlobalWeatherHttpGet port8 = service1.getGlobalWeatherHttpGet();
//	        System.out.println("Call Web Service Operation...");
//	        System.out.println("Server said: " + port8.getCitiesByCountry(null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("Server said: " + port8.getWeather(null,null));
//	        //Please input the parameters instead of 'null' for the upper method!
//	
//	        System.out.println("***********************");
//	        System.out.println("Call Over!");
	}
}
