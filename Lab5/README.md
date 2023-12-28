##### Mobilne tehnologije – Laboratorijske vježbe

# Vježba 5

Izraditi aplikaciju u kojoj se na Activity ispisuju koordinate lokacije uređaja (ili lokacije koja je označena
na simulatoru) te adresa, grad i država koja je vezana za te koordinate. Obavezno koristiti Google
PlayServices.Location API.  

Kako biste stekli konceptualni uvid u zadatak, prije nego krenete u izradu aplikacije, možete proučiti
primjere sa predavanja. Proučite i slideove (Location i Geocoding) u kojem je pojašnjenje primjera sa
predavanja.

## Upute:

Koristiti FusedLocationProviderClient  
https://developers.google.com/android/reference/com/google/android/gms/location/FusedLocationProviderClient

### Koraci:

1. Instalirati Google PlayServices komponetu u Android Studio:  
https://developer.android.com/training/location/retrieve-current#setup
2. Zatražiti dopuštenja:  
https://developer.android.com/training/location/permissions
3. Dohvatiti posljednju znanu lokaciju kako korisnik ne bi čekao na određivanje trenutne lokacije:  
https://developer.android.com/training/location/retrieve-current#java
4. Odrediti postavke za preciznost u učestalost osvježavanja (update) lokacije:  
https://developer.android.com/training/location/change-location-settings#location-request
5. Opcionalno (nije obavezno): provjeriti postavke uređaja i ako su potrebne stvari isključene obavijestiti korisnika da ih uključi  
https://developer.android.com/training/location/change-location-settings#java
6. Preplatiti se na osvježavanje (update) lokacije.  
https://developer.android.com/training/location/request-updates#java
7. Za geokodiranje koristiti Android.Location.Geocoder kao i na primjeru sa predavanja.  
https://developer.android.com/reference/android/location/Geocoder
