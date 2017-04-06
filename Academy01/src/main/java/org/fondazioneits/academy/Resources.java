package org.fondazioneits.academy;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.fondazioneits.academy.stub.soap.weather.GlobalWeather;
import org.fondazioneits.academy.stub.soap.weather.GlobalWeatherSoap;

public class Resources {

	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}

	@Produces
	@ApplicationScoped
	public GlobalWeatherSoap produceGlobalWeatherSoap() {
		GlobalWeather service1 = new GlobalWeather();
		GlobalWeatherSoap port1 = service1.getGlobalWeatherSoap();
		return port1;
	}

}
