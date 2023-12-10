##### Mobilne tehnologije – Laboratorijske vježbe

# Vježba 2

U vježbi treba izraditi Android aplikaciju koja će poslati zahtjev na GitHub API i
dohvatiti sve repozitorije sa više od 100 000 zvjezdica (https://api.github.com/search/repositories?q=stars:>100000).  
Informacije o repozitorijima će biti prikazani u RecyclerView-u.  
Svaki ViewHolder treba imati avatar vlasnika repozitorija, ime repozitorija i broj zvjezdica.

Rješenje podijeliti na sljedeće stavke:

1. Izučiti i primjeniti ConstraintLayout-a za potrebe prikaza.
2. U projekt dodati RecyclerView sa mock-anim podacima koji će se u konačnici zamijeiti sa pravima.
3. Svaka ćelija RecyclerView-a treba imati avatar vlasnika repozitorija, ime repozitorija i broj zvjezdica. Za dohvaćanje avatara sa mreže izučiti paket Glide (ili neki drugi po želji).
4. Izučiti GitHub-ov REST API i konstruirati pravilni upit za dohvaćanje svih repozitorija sa više od 100 000 zvjezdica.
5. Izučiti Gson za potrebe modeliranja Java objekata u koje će se proparsirati podaci sa API-a.
6. Izučiti Retrofit v2 za potrebe dohvaćanja podataka sa API-a.
7. Kreirati Java objekte za Gson.
8. Dohvatiti podatke sa API-a koristeći Retrofit v2.
