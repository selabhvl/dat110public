
### nettverksnivå

- Hvorfor har man to "planes" i forbindelse med packet forwarding?
- Bruker routere forskjellige routing algoritmer, eller ligger det informasjon i datagrammet om hvilken algoritme routeren skal bruke for forwarding?
- Hva er mest vanlig å bruke av: "Per-router distributed control plane" og "Logically centralized control plane"? I hvilke tilfeller er den ene mer egnet enn den andre?
- i hvilke tilfeller skal man bruke lokal routing funksjon i stedet for global?
- Kan du fortelle kort kva "Routing function" i network layer er?
- What are the main functions implemented at the network layer?
- Hvor mange elementer pleier det å være i forwarding table?
- Når bør man bruke Per-router distributed control plane og når bør man bruke Logically centralized control plane?
- Finnes det andre måter å velge stien til datagramene en "routing algorithm" og "software-defined networking"?
- Hvor befinner denne remote controlleren seg fysisk?
- Hva er det control-plane gjør ? eller hva er det den viser oss?
- What are the main functions implemented at the network layer.
- Hvilken funksjon har remote controller i en ruter?
- Større forskjell med routnings algorime kontra CAs?
- Are there network layer protocols in every host/router?
- How to convert 32 bit ip addresses to regular ip addresses

### nettverk som grafer, link-kostnader, least-cost paths

- How does a router estimate its link costs, does it ping the connected router and measure the time it takes to recieve an answer?
- Kan en "avstand" endre seg for Node1 til Node2 når man har funnet avstanden tidligere?
- Hvordan finner man vekten mellom noder?
- how do you find and use edge weight in practice?
- Er det mulig at det finnes noder i en nettverks graf der kostnaden er ukjent?
- Forstår ikke hvordan "least-cost-path" ikke altid er den korteste når det gjelder kommunikasjon mellom noder i nettverket. Kort forklaring på dette kanskje?
- Hvordan estimerer ruterne leveringstiden? Pinger de naboene og tar tiden? 
- Når en ser på routing via/som grafer, vil "edge weight" også inkludere prosesseringstid i nodene (ruterene), eller er dette irrelevant siden det er en måleenhet for minst motstand?
- Vil utregning av "least-cost path" være en belastning på systemet om det er mange nok rutere? Eventuell hvor stor
- Hvorfor er ikke en vei eller ruter med minst kostnad den korteste veien? Er det kun pga antall hopp eller er det noe mer som avgjør det?
- in the third lecture video, there is talk about how to calculate least- cost path. Can this be explained again?
- Hvilken av veiene er best å ta? Least -cost path eller shotest path? 
- What determens the cost of the paths when choosing routes? Why are some more expensive then others?(is it just distance? or speed or traffic) And how often/can it change?

### Link-state routing

- Im assuming LS routing is often used in localized networks that doesn't alter its topology alot (like in a facility or an entreprise). But in the event that a new router is introduced into the graph. Does every node start recalculating its forwarding table, or does it just include the new node and adjust its forwarding table to accomodate it?
- In Link State Routing, how can infinite looping be solved? 
- Bruker rutere på internett i hovedsak link-state eller distance-vector routing algoritmer?
- Andre relevante algoritmer enn Dijkstra’s algoritme som brukes til å regne ut kost effektive veier i grafen ?
- Link-state rounting: Finnes det eksempler på at routere bruker algoritmer som ikke har lowest cost som mål? Eller er det alltid lowest cost vha. Djikstra’s algoritme?
- Er det andre algoritmer en dijkstras algoritme som kan bli tatt i bruk for link-state routing algoritme?
- Hvor legges ansvaret for utregning av Dijkstras algoritme ved LS (OSPF)?
- Er det en grense for kor mange noder man kan utføre Dijkstras algoritme gjennom?
- Forstod ikke helt notasjonen på Dijkstra's algoritme. hva betyr det når det står at N': "set of nodes whose least cost path is definitively known"?
- Mulig å gå gjennom eit anna eksempel på initialisering av djikstras algoritme?
- Hvor effektiv er Djikstras algoritme i forhold til andre algoritmer?
- Is Dijkstra's algorithm the most widely used algorithm for finding the least cost paths between routers? Also, are there other algorithms better suited for other circumstances?
- Is Dijkstra’s Algorithm the most used and perhaps the "best" method?
- What other algorithms than dijkstras can be used?

### Distance-vector Routing

- Hvorfor oppdateres minst kostnad til å være "3, R3"  og ikkje "3, R5" i step 2 for D(R4), p(R4) i utregningen av link-state routing algoritmen, Djikstra's algoritme? (Video "Link State Routing"  timestamp ca 12:55)
-

### Link-state vs. distance vector

- Hvilken routing algoritme er mest brukt?
- Hvilke routing algoritme er mest vanlig brukt?
- Link-state angår "global knowledge" mens distance-vector angår "local knowledge".
Er det realistisk å bruke Link-state-algoritmer i den virkelige verden? (Hvordan kan du vere sikker på at alle er "oppdatert" på nyeste "global knowledge"?)
- Brukes det andre algoritmer enn dijkstras, i såfall hvilke? Forskjell mellom local og global? 
- Når er det mest effektivt å bruke link-state, og når er det best å bruke distance-vector routing algorithms?
- hva er manual configuration og static configuration ?
- Which one is better link state or distance vector?
- Hvilken problemer kan oppstå ved link-state routing og distance-vector routing? Kan du komme med noen eksempler?
- what is the difference between link state routing and distance vector routing? Will it not always have the same cost in the end after the distance vector has found its shortest way?
- What are two popular examples of distance vector routing protocols?
- Når vil vi bruke Link-state over Distance-vector?
- Når vil det være fordelsmessig å bruke DV (RIP) i stedet for LS (OSPF)?
- Når er det bedre å bruke distance vector over link state og vice versa?
- Når velger man LS over DV eller motsatt?
- Hvordan vet man hvilken "routing" algoritme som blir brukt/skal brukes?
- Hva er beste brukstilfeller for Link-State og Distance-vector, og hva er best?
- Which approach for routing function is used the most between the global/link-state routing algorithms and the local/distance-vector routing algorithms?
- Hvilke av algoritmene er mest brukt, og i hvilke tilfeller er de ulike foretrukket?
- Hva er mest nyttig å bruke link-state eller distance-vector?
- Under Global / link state routing algorithm så sier du at systemet er sentralisert og at hver ruter har informasjon om connectivity og communication links. Er ikke dette definisjonen på et desentralisert system? Hvis ikke, hva er isåfall forskjellen?
- Hvilken routing-algoritme er mest vanlig i nettverksstruktur på datasentre?
- Hva er best av link-state (LS) og cistance-vector (DV)?

### Annet

- Hvordan fungerer ping og traceroute?
- Foretrekkes det med reliable delivery på link-layeren eller på transport layer? eller begge deler?
- hva er manual configuration og static configuration ?
- hav er manual eller static configuration? 
- Hva er de forskjellige delene som sendes og brukes av data link laget?
