package br.com.progweb.evento.managedbean;

import java.util.List;

import javax.faces.bean.ManagedBean;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.progweb.evento.entitys.Evento;

@ManagedBean
public class EventoMB {
	
	public List<Evento> getEventosCadastrados(){
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/evento/rest/evento/contatos");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<List<Evento>>(){}.getType());
	}	
	
	public Evento deletarEventosId(){
		Client c = Client.create();
		WebResource wr = c.resource("http://localhost:8080/evento/rest/evento/contatos/evento/{id}");
		String json = wr.get(String.class);
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<List<Evento>>(){}.getType());
	}
}
