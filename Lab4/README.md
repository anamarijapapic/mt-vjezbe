##### Mobilne tehnologije – Laboratorijske vježbe

# Vježba 4

Napraviti novi Firebase projekt. Dodati svoju bazu (Firebase Realtime Database) i importati podatke iz
datoteke `import_json.json` (nalazi se na Moodle-u ili možete napraviti svoj). Izraditi novi Android projekt.
Povezati Firebase i Android projekt. U Android aplikaciji napraviti activity za registraciju i login. Ukoliko je
user uspješno autenticiran preusmjeri se na activity sa RecyclerView-om koji će prikazivati sve predmete
iz baze podataka (i to samo naziv predmeta). RecyclerView se treba ažurirati kada dodje do promjene u
podacima na strani baze.  

Klikom na jednu ćeliju koja se nalazi u RecyclerView-u, otvara se novi Activity u kojem se nalaze detalji o
predmetu (ime predavača i na kojoj je godini). U tom Activity-u navedene podatke se može izmijeniti i
izmijenjene spremiti u bazu pritiskom na botun „save“. Promjene se moraju detektirati i na
RecyclerView-u sa početnog activity-a.

Sadržaj `import_json.json` datoteke:
```json
{  "predmeti": [
    {
      "godina": 1,
      "ime": "Digitalna",
      "predavac": "Ivan Kedzo"
    },
    {
      "godina": 1,
      "ime": "Analiza 1",
      "predavac": "Ariana Burazin"
    },
    {
      "godina": 3,
      "ime": "Analiza 2",
      "predavac": "Ivo Baras"
    },
    {
      "godina": 2,
      "ime": "Mrezne usluge i programiranje",
      "predavac": "Haidi Bozikovic"
    },
    {
      "godina": 5,
      "ime": "Mobilne tehnologije",
      "predavac": "Marina Rodic"
    }
  ]
}
```