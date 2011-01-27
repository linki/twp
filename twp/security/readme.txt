Erstellung eines KeyStores für Java (enthält das lokale Zertifikat und den privaten Schlüssel zum signieren).
Für den Fall, dass man eine *.p12 Datei ohne Passwort hat, muss man diese erst konvertieren:

openssl pkcs12 -in src.p12 -out temp.pem -nodes
openssl pkcs12 -export -in temp.pem -out src2.p12

Danach kann man die *.p12 Datei mit dem Passwort als Quelle für keytool angeben und wie folgt den KeyStore generieren:

keytool -importkeystore -srckeystore src2.p12 -srcstoretype pkcs12 -destkeystore keystore.jks -deststoretype JKS