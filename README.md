Challenge: NASA Climate Change Records per Year:

Armar un prototipo de API Rest que permita registrar temperatura anual para diferentes paises.

Entidades Principales: Pais (codigoPais, nombre) Temperatura(id, codigoPais, anio, grados) 

Países
- POST /paises : que permita la creación de un país

     RequestBody: {
     “codigoPais”: 32,
     “nombre”: “Argentina”
     }
		 
- GET /paises : que devuelva la lista de países SIN las temperaturas.
- GET /paises/{id} : que devuelva la info de un pais en particular(SIN las temperaturas)
- PUT /paises/{id} : que actualice solo el nombre del país. Usar un requestBody que solo tenga el
nombre del país.

Temperaturas
- POST /temperaturas : que registre una temperatura de un país en un año específico

     RequestBody {
     “codigoPais”: 32,
     “anio”: 2010,
     “grados”: 38.6
     }
- GET /temperaturas/paises/{codigoPais} : que devuelva la lista de temperaturas con sus años de un
país especifico, indicado por “codigoPais”.
- DELETE /temperaturas/{id}: no se borrará la temperatura id, deberá cambiar el año a 0


BONUS:

- GET /temperaturas/anios/{anio} : que devuelva la lista de temperaturas de un año en particular en
el siguiente formato JSON Array:

[{
“nombrePais”: “Argentina”,
“grados”: 29
},
{
“nombrePais”: “Venezuela”,
“grados”: 45
}]

- GET /temperaturas/maximas/{codigoPais} : que devuelva la temperatura máxima para un país en
particular en este formato JSON(informar el año en que ocurrió)

{
“nombrePais”: “Venezuela”,
“temperaturaMaxima”: 45,
“anio”: 2011
}


