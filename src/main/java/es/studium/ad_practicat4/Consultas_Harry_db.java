package es.studium.ad_practicat4;

import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class Consultas_Harry_db {
	
	public static void main(String[] args) {
		//conexion
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		//obtenemos base de datos
		MongoDatabase database = conexion.getDatabase("harry");
		
		System.out.println("conexion a base de datos:"+database.getName());
		//obtenemos coleccion studium
		MongoCollection<Document> magos = database.getCollection("harry potter");
		
		
		// 1.Mostrar todos los personajes cuyo atributo "species" tenga como valor "human".
		FindIterable<Document> buscaHuman = magos.find(Filters.eq("species", "human"));
		for (Object mago : buscaHuman) {
		System.out.println(((Document) mago).toJson());
		}
		
		/* 2. Mostrar todos los personajes cuyo atributo "yearOfBirth" sea anterior 
		 a 1979, no vale igual a 1979.*/
		FindIterable<Document> buscaAnioNacimiento = magos.find(Filters.lt("yearOfBirth", 1979));
		for (Object mago : buscaAnioNacimiento) {
			System.out.println(((Document) mago).toJson());
			}
		
		//3. Mostrar todos los personajes cuyo atributo "wood" de la propiedad "wand" sea "holly"
		FindIterable<Document> buscarVarita = magos.find(Filters.eq("wand.wood", "holly"));
		for (Object mago : buscarVarita) {
			System.out.println(((Document) mago).toJson());
			}
		
		/*4. Mostrar todos los personajes que estén vivos (propiedad "alive" igual a true) y 
		además sean estudiantes (propiedad "hogwartsStudent" igual a true).*/
		FindIterable<Document> buscarVivos = magos.find(Filters.and(Filters.eq("alive", true),
				Filters.eq("hogwartsStudent", true)));
		for (Object mago : buscarVivos) {						
			System.out.println(((Document) mago).toJson());
			}
		
	}
	
	
	
}
