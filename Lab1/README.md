##### Mobilne tehnologije – Laboratorijske vježbe

# Vježba 1

## Zadatak 1: Constraint Layout
Kreirati Android projekt sa praznim Activiy-em. Izraditi prezentacijsku XML datoteku na nacin da
koristenjem Constraint-ova po izboru postignete izglede prikazane na donjim slikama. Izgled treba
biti postojan prilikom prelaska uredjaja u horizontalnu/verikalnu poziciju.

![Vježba 1 - Zadatak 1: Izgledi 1](https://github.com/anamarijapapic/mt-vjezbe/assets/92815435/4941e792-e53e-497b-8946-c5797fed9242)
![Vježba 1 - Zadatak 1: Izgledi 2](https://github.com/anamarijapapic/mt-vjezbe/assets/92815435/1041cc02-4075-48a3-b347-d84a99b2e076)

## Zadatak 2: Jednostavni kalkulator
### 2.1.
Na Layout staviti 2 polja za unos brojeva i jedno polje za unos operatora. Korisnik
moze unjeti operator +, -, * ili /. Na ekran dodati botun. Klikom na botun se rezultat
operacije ispisuje na tekst view. Ukoliko se unos operatora razlikuje od navedenih,
korisniku se na ekran ispise toast poruka (detalje vidjeti na
https://developer.android.com/guide/topics/ui/notifiers/toasts). Voditi racuna o tome kako
nije dozvoljeno dijeliti sa nulom (opet toast poruka) i kako je kvocijent tipa Float. Izgled
ekrana nije unaprijed zadan, ali voditi računa o nepreklapanju kontrola (koristiti
ConstraintLayout).

### 2.2.
Izmjeniti prethodni zadatak. View za unos operatora zamijeniti sa view-om spinner, u
kojem ce na izbor biti dostupna cetiri operatora (+, -, *, /). Klikom na botun se otvara novi
Activity i na njemu se ispisuje rezultat. Prouciti dokumentaciju spinner-a na
https://developer.android.com/guide/topics/ui/controls/spinner.
