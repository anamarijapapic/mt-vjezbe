##### Mobilne tehnologije – Laboratorijske vježbe

# Vježba 3

Izraditi aplikaciju „Zabilješke“.

Aplikacija ima sljedeću specifikaciju:
- Pokretanjem aplikacije na ekranu se iscrtaju sve zabilješke u RecyclerView.
- U desnom donjem uglu staviti „floating action button“. Klikom na njega se otvara novi Activity za kreiranje zabilješke. Novokreirana zabilješka se sprema u bazu podataka (SQLite). Obavezno je koristiti biblioteku Room. Dodavanjem nove zabilješke, korisnika vratiti na početni ekran na kojemu mora biti vidljiva novododana zabilješka.
- Klikom na pojedini element u RecyclerView-u se otvara novi Activity na kojemu se ispisuje odabrana zabilješka. Zabilješku je moguće izmijeniti i spremiti klikom na dugme „save“. Također ju je moguće obrisati klikom na dugme „delete“. U slučaju brisanja, potrebno je tražiti potvrdu korisnika u dijalogu. Nakon brisanja ili snimanja, Activity se treba ukloniti i vratiti na Recycler view Activity na kojem trebaju biti vidljive promjene iz prethodnog koraka.
