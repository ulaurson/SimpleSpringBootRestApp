Lihtne Sring Boot REST veebirakendus.



Tarkvara on kompileeritav ehitustööriista gradle abil.
Tarkvara kompileerimiseks on vaja teha järgmised sammud:

	1. Arvutisse peab olema installeeritud gradle.
	
	2. Sisenege kausta, kus asub tarkvara lähtekood.
	
	3. Käsurealt jooksutage järgmine skript:
		gradle build
	Antud käsk kompileerib faili serverApp.jar, mis sisaldab serverit ja 
	faili clientApp.jar, mis sisaldab klienti, kes suhtleb serveriga.
	
	4. Serveri jooksutamiseks käivitage järgmine skript:
			java -jar server/build/libs/serverApp.jar

	5. Kliendi jooksutamiseks käivitage järgmine skript:
		java -jar client/build/libs/clientApp.jar
		
		
		
Serveris saab teha järgmisi päringuid:

	GET /hello - tagastab teksti "Hello Word!".
	
	GET /calculate?num1=3&num2=5&op=sum - tagastab 8. 
		Töötab suvalise num1, num2 ja op sisendi peale.
		op väärtus võib olla üks neist:
			sum - liitmine, sub - lahutamine, prod - korrutamine ja div - jagamine
			
	POST /calculate - tagastab sama tulemuse, mis GET /calculate, kuid 
		POST päringu kehaks on JSON formaadis dokument näiteks {"num1": 3, "num2': 5, "op": "sum"}.
		
	GET /employee - tagastab andmebaasist nimekirja kõikidest töötajatest koos nende alluvatega.
	

Kliendi rakendus:
	Rakenduse käivitamiseks palutakse kasutajal sisestada num1, num2 ja op (vaata GET /calculate).
	Seejärel tehakse sisenditest JSON formaadis dokument ja saadetakse serverile ning
	server saadab tagasi kliendile tehte tulemuse.
	
	
Andmebaasi konfigureerimine:
	Server on liidestatud suhtlemaks PostgreSQL andmebaasiga.
	Kaustast server/src/main/resources leiate faili application.yml.
	Selles failis saate muuta andmebaasi seadistusi.
	
		Andmebaasi aadressi muutmine:
			spring.datasource.url: [jdbc:postgresql://localhost:5432/employees]
			Vaikeväärtused:
				port: 5432
				andmebaasi nimi: employees (sellesse andmebaasi luuakse tabel)
				
		Andmebaasi kasutaja nime ja parooli muutmine:
			Kasutajanimi:
				spring.datasource.username: [postgres] (Vaikeväärtus postgres)
			Parool:
				spring.datasource.password: [postgres] (Vaikeväärtus postgres)
		
		
Ülemuste ja alluvate tabel:
	Kaustast server/src/main/resources leiate faili import.sql.
	
	Selle faili abil luuakse uus tabel. 
		Tabeli nimi: employees
		Tabeli veerud:
			employee_id (int) - töötaja ID (täisarv)
			name (varchar(255)) - töötaja nimi (tekst)
			manager_id (int) - ülemuse ID (täisarv)
	
	Kõige väiksema manager_id'ga töötaja on kõige suurem ülemus.
	
	Fail sisaldab ka näiteid kuidas sisestada uusi töötajaid andmebaasi.

