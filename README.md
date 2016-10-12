# Sales taxes problem
## COMPILE AND RUN
Il progetto fa uso di Maven: `mvn package` crea un jar eseguibile.
La classe Main nel package marco esegue lo stesso test fatto nella classe TestEndToEnd, ma stampa a video i risultati.
In `src/test` sono presenti i test JUnit scritti.
`TestEndToEnd` usa un input in forma di stringa dell'intera lista e verifica che l'output sia quello previsto.

La sintassi per l'input è la seguente:
- I vari oggetti nel 'carrello della spesa' sono separati da newline (`\n`)
- Dall'inizio della riga fino al primo spazio è la quantità del prodotto
- Dal primo spazio all'ultima istanza della stringa ` at ` è il nome del prodotto
- Il resto indica il prezzo unitario del prodotto