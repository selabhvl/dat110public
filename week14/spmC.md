### Link-nivå generelt

- Hva er det hovedoppgaven til link-layer ? 
- What other technologies than ethernet exist for local area network?
- Hva er de ulike routing-funksjonene?
- Hvor høy hastighet kan man oppnå med ethernet?
- Forskjell på link- og network layer?
- I et routersystem hvor broadcasting er i bruk, hvordan instruerer routeren de ulike hostene om evt når de kan snakke med routeren? f.eks. i et system som bruker channel partition protocol?
- Hvordan jobber Link layer sammen med de andre layerne?
- What is a Ethernet? 
- Slide 10, Brodcast links - transmission Datagrammet blir sendt til alle. Hvis alle nodene sender og mottar, vil det ikke bli en del opphopning av unødvendige datagrammer? Evt reformulering av spm. N1, N2, N3 sender alle datagrammer, kun datagrammet fra N3 er ment for N4. Vil man ikke risikere delay?
- Er det mulig å "lure" icmp ved å alltid sammenligne destinasjons adressen med seg selv, og dermed plukke opp alle frames som blir mottatt?

### Routers, Hubs and switches

- What's the difference between a router and a switch?
- Hva er forskjellen på en switch og en ruter?
- I hvilke tilfeller er en hub å foretrekke fremfor en swith, og hvorfor? (hvis switch er smartere, hvorfor ikke burke den på alt? )
- What is the function of a switch?
- Hva er ulike brukstilfeller for broadcast links og point-to-point links?
- Er det noen annen grunn til å bruke switched eternet annent en å eliminere kolisjoner?
- Er det andre teknologier enn Ethernet som «normalt» brukes for LANs ? 

### Interference/bit errors

- Er det vanlig å kombinere flere metoder for EDC? Eller vil dette ikke være gunstig? For eksempel Parity checks og checksum.
- Do you have examples of interference on the communication link?
- Are bit level errors as likely in fibre transmission aswell?
- How is a signal collision detected in a local area network?
- Er det nogen gang man ville bruke parity checks eller checksumming i prakisis?
- Hvilken error detection technique blir mest brukt i dag?
- What are some of the pros and cons of the different error detection techniques?
- Bruker man Parity checks, checksumming og cyclic redundancy checks seperat eller bruker vi de samtidig?
- What is the most used/useful error detection technique?
- Hva vil være mest nyttig å bruke broadcast link eller point-to-point link? Og hvorfor?
- Hva er den beste metoden for å løse error detection?
- Hvilken type interferens er det typisk som forårsaker bit errors?
- In error detection, can you use something even more safe if you are transmitting something important. Like the state, military or spacestation.
- Would you just use a better/higher cdc or is it possible to make it fault proof? Or will it always be prone to bit changes.

### Medium access controller

- Hva er den beste av de forskjellige multiple access protokollene på link layer?
- Kunne du ha forklart litt mer utdypende om bildet på slide 25?
- What is carrier sense?

### Link/MAC adresser

- Kan et nettverkskort fake MAC-adressen sin (endre den til noe annet enn den innebygde adressen)?
- Hvorfor har vi MAC og IP adresse?
- Er MAC-adresse en egen adresse som en hvilken som helst elektronisk enhet har, slik at de kan snakke sammen med andre enheter?
- Hvor mye data pleier å går tapt til bit-level errors?
- Kan vi gå tom for MAC adresser?
- hvis pc-er har unikt ip adresse , hvorfor trenger man MAC addresse 
- Hvor stor er MAC sin adresseplass?
- Er det en sammenheng mellom MAC adresser og IPv4/IPv6?
- Could you explain more about addressing in LAN from the ethernet slide?
- Sikkert et dumt spørsmål ,men hvorfor ikke bare bruke nettverksnivå adressen som Mac addresse?

### ARP

- Fungerer ARP som en oversetter fra IP-adresse til MAC-adresse? Fungerer den bare en vei?
- Hvor kommer den absolutte adressen til en netverksadapter ifra?
- Hva er forskjellen mellom MAC table og ARP table?
- Hva gjør en ARP?
- What does the ARP protocol do?

### annet

- Hva er forskjellen på http og https?
- what is multi-hop forwarding?
