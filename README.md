# MarvelHeroes_Android

Proyecto para la realización de la prueba técnica de openbank

Pantallas que componen la aplicación:
* Splash
* Listado de superhéroes
* Detalle de un superhéroe
* Listado de cómics

Arquitectura escogida: MVVM
Librerías usadas para la vista:
* Retrofit
* Gson
* Picasso
* CardView
* RecyclerView
* SwipeRefreshLayout
* ConstraintLayout

La estructura del proyecto se compone de los siguientes paquetes:
* adapter: Adapters para mostrar las listas de personajes y de cómics
* api: Clases donde se define la estructura que tendrán las llamadas a la API
* model: Clases para el modelo
* Repository: Interfaz y repositorio donde se harán las llamadas a la API
* utils: clases de utilidad
* view: Todo lo que tiene que ver con la vista (activities)
* viewmodel: Los viewmodels encargados de comunicarse entre la vista y las demás capas

Al inicio de la aplicación se muestra un splash simulando un loading con un timer de 2 segundos. 
Después pasamos a la actividad principal donde se muestra un listado de personajes en formato de Grid de 2 columnas.
Al pinchar sobre uno pasaremos a la vista del detalle.
Al pinchar sobre cómics se nos mostrará un listado con los cómics de este superhéroe.

