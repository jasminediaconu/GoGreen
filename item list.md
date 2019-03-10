# List of items with C02 values

## Food
- eat a regular meal  <br> 
	* base: emission of a meal of junk food per person <br> 
	* score: 3.96 kg C02 per kg per person (0 kg CO2 saved) <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Regular meal', 'food', 0); <br> 

- eat a vegetarian meal <br> 
	* base: emission of a meal of junk food per person <br>  
	* score: 2.93 kg C02 per kg per person (1.06 kg CO2 saved) <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Vegetarian meal', 'food', 1.06); <br> 

- eat a vegan meal <br> 
	* base: emission of a meal of junk food per person <br> 
	* score: 2.22 kg C02 per kg per person (1.74 kg CO2 saved) <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Vegan meal', 'food', 1.74); <br> 

- buying local produce(fruit and vegetables) <br> 
	* base: emission transport to store of imported produce <br> 
	* score: 1.025 kg C02 per kg of produce (2.935 kg CO2 saved) <br>
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Local produce', 'food', 2.935); <br> 
	
	
	
	
## Energy
- lowering the temperature of your home for a day ('gray' energy) <br>
	* base: 20 °C <br>
	* score: 554.7 g C02 per °C <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Lower temperature', 'energy', 0.554); <br> 

- installing solar panels <br>
	* base: average power usage <br>
	* score: 649 g C02 per panel per day <br>
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Solar panel', 'energy', 0.649); <br> 

- change to LEDs <br>
	* base: emission of energy use of an average light bulb <br>
	* score: 97.35 g C02 per bulb per day <br>
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('LEDs', 'energy', 0.097); <br> 



## Transport
- using a bike/ walking instead of using the car <br>
	* base: emission of car per km (drives on gas; worst case) <br>
	* score: 350 g C02 per km <br>
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('By bike', 'transport', 0.350); <br> 

- using public transport instead of the car <br>
	* base: emission of car per km (drives on gas; worst case) <br>
	* score: 275 g C02 per km  <br>
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Public transport', 'transport', 0.275); <br> 

- going by car <br>
	* base: emission of car per km (drives on gas; worst case) <br>
	* score: x g C02 per km (based on type of care) <br>
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Gas, Mini', 'transport', 0.233); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Gas, Compact car', 'transport', 0.243); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Gas, Station wagon', 'transport', 0.225); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Gas, Supercar', 'transport', 0); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Gas, SUV', 'transport', 0.219); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Gas, Van', 'transport', 0.190); <br> 
	* 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Hybrid, Mini', 'transport', 0.295); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Hybrid, Compact car', 'transport', 0.300); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Hybrid, Station wagon', 'transport', 0.275); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Hybrid, Supercar', 'transport', 0.308); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Hybrid, SUV', 'transport', 0.278); <br>
	* 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Electric, Mini', 'transport', 0.350); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Electric, Compact car', 'transport', 0.350); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Electric, Station wagon', 'transport', 0.350); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Electric, Supercar', 'transport', 0.350); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Electric, SUV', 'transport', 0.350); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Electric, Pickup', 'transport', 0.350); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Electric, Van', 'transport', 0.350); <br>
	* 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Diesel, Mini', 'transport', 0.238); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Diesel, Compact car', 'transport', 0.258); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Diesel, Station wagon', 'transport', 0.240); <br>
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Diesel, SUV', 'transport', 0.239); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Diesel, Pickup', 'transport', 0.190); <br> 
	* sql: INSERT INTO items ("name", "type", "co2") VALUES ('Diesel, Van', 'transport', 0.220); <br>

https://www.nextgreencar.com/emissions/make-model/  <br>


|Car type\ emission type|Gas    |Hybrid |Electric   |Diesel |
|---                    |---    |---    |---        |---    |
|Mini                   |117    |55     |0          |112    |
|Compact car            |107    |50     |0          |92     |
|Station wagon          |125    |75     |0          |110    |
|Supercar               |350    |42     |0          |null   |
|SUV                    |131    |72     |0          |111    |
|Pickup                 |null   |null   |0          |160    |
|Van                    |160    |null   |0          |130    |
* when the value is null, no data was found on existing models or no models existed.


## Sources
https://www.directenergy.com/blog/how-much-can-you-save-by-adjusting-your-thermostat/ <br>
https://www.eia.gov/tools/faqs/faq.php?id=97&t=3 <br>
https://www.co2emissiefactoren.nl/wp-content/uploads/2017/12/2017-12-Elektriciteit.pdf <br>
https://www.solarpowerrocks.com/solar-basics/how-much-electricity-does-a-solar-panel-produce/ <br>
https://www.thesimpledollar.com/the-light-bulb-showdown-leds-vs-cfls-vs-incandescent-bulbs-whats-the-best-deal-now-and-in-the-future/ <br>
https://www.delijn.be/nl/overdelijn/organisatie/zorgzaam-ondernemen/milieu/co2-uitstoot-voertuigen.html <br>
https://www.nextgreencar.com/emissions/make-model/ <br>
https://vanfueldata.vehicle-certification-agency.gov.uk/ <br>
https://link.springer.com/article/10.1007/s10584-014-1169-1%EF%BB%BF <br>
https://www.extension.harvard.edu/inside-extension/buying-local-do-food-miles-matter <br>




