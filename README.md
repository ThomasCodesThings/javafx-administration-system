
# JavaDoc file -> index.html

# Zámer projektu + finálna verzia

## Moja Cukráreň

Základom firmy je jej vlastník, ktorý spravuje všetky veci týkajúce s celkového chodu firmy.
Manažér sa stará o správne spracovávanie objednávok od zákazníkov a o to aby firma mala
vždy dostatok surovín na výrobu. Ďalej má firma viacero teamleaderov ktorí spravujú svoj tím
ktorý sa skladá z bežných zamestnancov. Teamleader prideľuje úlohy svojim členom teamu
podľa aktuálnych požiadaviek. To znamená že zamestnanec nemusí mať fixnú pozíciu vo firme.
Zároveň všetci vyššie spomínaní členovia firmy sú jej zamestnancami. Zamestnanca je možné
povýšiť/ degradovať alebo dokonca aj vyhodiť z firmy. Cukráreň berie vstupné suroviny na
výrobu od rôznych dodávateľov. Cukráreň prijíma objednávky od zákazníkov. Je možné
kedykoľvek sledovať ich stav, prípadne ich upraviť podľa dodatočných požiadaviek alebo
dokonca aj objednávku zrušiť. Cukráreň vyrába viacero druhov produktov, ako sú napríklad
koláče, zákusky, zmrzliny, torty a iné. Touto činnosťou sa zaoberajú zamestnanci spolu so
svojimi teamleadermi. Cukráreň môže vyrábať výsledné produkty aj nad rámec objednávok ak
má na to dostatočné kapacity. Navyše vyrobené produkty sa uskladnia do skladu a použijú sa
v prípade potreby. Stav celého výrobného procesu je možné neustále sledovať. Po úspešnom
spracovaní objednávky sa zákazníkovi vystaví výsledná faktúra.

# Finálna verzia

## Dedenie

Mám triedu **Person** ktorá má atribúty meno, priezvisko, dátum narodenia, vek, a bydlisko.
Z nej dedím pre triedu **Employee** (každý zamestnanec má svoje osobné číslo), a trieda
**Employee** sa rozširuje(dedí o triedu vlastníka( **Owner** ), Manager-a a **TeamLeader** - a. Každá z
nich má svoje vlastné premenné navyše.

Person

Employee

Triedy jednotlivých „typov“ zamestnancov

Ide o trojvrstvové dedenie.

## Polymorfizmus

Napríklad metóda **add(Emoployee employee** ) ktorá zoberie ako parameter zamestnanca a v každej
ďalšej triede dené( **Owner, Manager, TeamLeader** ) sa môže implementovať „po svojom“. Ukážka
z triedy Owner:

## Druhá hierarchia

Rozhranie Observera

Produkt

Podtyp Produktu(zmrzlina)


Podtyp zmrzliny(Šmolková zmrzlina)

Takéto dedenia používam pri delení produktov a ingrediencii(dvojvrstvová hierarchia)

Polymorfizmus môže byť vykonaní prekonaním metód v triede Product, ale to momentálne
v projekte nevyužívam.

## Zapuzdrenie(Enkapsulácia)

Skoro všetky(alebo aj všetky) premenné mám nastavené ako **private**. Čiže na to aby som sa
k nim mohol dostať alebo modifikovať z iných tried musím použiť „gettery“ a „settery“, ktoré
mám tiež všade programe.

## Agregácia

Zatiaľ mám 2 triedy ktoré mám na agregáciu a to je trieda **Login** (kde vytváram objekt
o kombinácii meno, heslo) a **Residence** (kde vytváram detaily miesta obydlia(adresa, PSČ,
mesto, krajina). Triedu **Login** využívam v triede **Owner** a triedu **Residence** v triede
cukrárne( **Patisserie** ) a triede osoby( **Person** )

## Návrhový vzor Observer

Pôvodné využitie malo byť na zaznamenávanie a spracovávanie zmien počtu produktov pri
vytváraní objednávky. Keďže táto časť v projekte nie je zatiaľ funkčná tak to je len
naimplementované. Základom sú dve rozhrania ProductObserver a OrderObserver. V triede
Order mám pole pozorovaných prvkov rozhrania ProductObserver. V tej istej triede
upozorňujem pozorované prvky pomocou metódy notifyObservers() a pomocou metódy
triedy update() v triede Product.

## Vlastné výnimky

Táto metóda sa nachádza v triede **AuthenticationControl**. A vyhodí sa táto výnimka práve
vtedy keď sa majiteľ cukrárke prihlási pod správnym používateľským menom a nesprávnym
heslom. Na overenie vypíšem text ktorý je generovaný v metóde vlastnej výnimky
**getMessage()**.

Podobne mám aj ďalšiu výnimku **ItemNotSelected**

ktorá sa vyhodí ak si na začiatku nezvolíme cukráreň s ktorou chceme ďalej pracovať.

## Oddelenie grafického rozhrania + vlastné handlery

Projekt je na 99 % robený v štýle že v časti balíka controllera pracujem s dátami, tlačítkami,
textovými boxami a inými vecami len v tom jednom mieste a čo sa týka GUI tak to všetko
riešim cez vlastné triedy v balíku view. Všetky tieto triedy sú vlastnoručne spravené. Príklad
vlastného **Event Handlera** :

## MultiThreading(Viacniťovosť)

Využívam ju na spúšťanie rôznych inštancii cukrární na viacerých vláknach pomocou metódy
**start()** a **run(),** aby som mohol s nimi paralelne pracovať.

## Generickosť a vhniezdené triedy

Sklad( **Warehouse<E>** ) pre cukráreň mám realizovaný ako spájaný zoznam do ktorého
pridávam/odoberám prvky. Viem do neho vložiť akýkoľvek prvok. Tento spájaný zoznam sa
skladá z uzlov triedy **Node**. Trieda **Node** je vhniezdená v triede Warehouse nasledovne:

## Explicitné použitie RTTI

Používam to najmä na zistenie typu objektu. Nachádza sa to na viacerých miestach v projekte.
Napríklad takto:

Kde zisťujem či je daný **zamestnanec Team Leader** , **Manažér** alebo bežný pracovník. Aby som
ho vedel zaradiť do správneho zoznamu a pridať mu správne osobné číslo. Tento príklad sa
nachádza v triede **Owner**.

## Lambda výrazy

Napríklad tu v cykle v triede **Order** kde kontrolujem duplicitné číslo objednávky.

Ďalšie lambda výrazy sú v **controlleri** a zopár je ich aj v balíku **view**.

## Aspektovo-Orientované programovanie

V projekte mám vytvorený jeden súbor ktorý využívam na kontrolu počtu prvkov produktu
vytvorený pomocou AspectJ, ale nemám ho odskúšaný. (Len okrajové „splnenie“ kritéria).

## Použitie serializácie

Serializáciu mám takmer všade pri triedach v modeli aby som vedel priebežne ukladať
a načítavať dáta z databázy **PatisserieDatabase**. Príklad serializácie:

A potom využívam metódy **load()** a **save()** v triede **PatisserieDatabase**.

# Zhrnutie + poznámky

Všetky okná fungujú, tlačidlá sú intuitívne. Na úspešné uloženie vykonaných zmien je
potrebné vypnúť všetky okná prostredníctvom „X“ v pravom hornom okne. Dáta v tabuľkách
sa aktualizujú s každým odhlásením, prihlásením sa. Niekedy k tomu dokonca program sám
vyzýva.

# Zmeny of finálnej prezentácie

- Pridaný návrhový vzor Observer
- Sfunkčnený celý systém
- + veľa iných tried pre view a controller

Zoznam zmien – pri commitoch na githube.

V prípade akýchkoľvek otázok ma neváhajte kontaktovať.

Dajú sa robiť všetky funkcie od pridávania objednávok cez predávanie a odoberanie
zamestnancov, nastavovanie ich činností a spracovávanie objednávok.

# UML diagram

Je ako samostatný obrázok na gitbube(UML.png)


