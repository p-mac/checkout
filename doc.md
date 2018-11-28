This is defined as a Serverless Microservices application, with configurable endpoints and DataSources.
It is packaged and runs as a jar application
It has the only purpose of testing some mechanics for a simple application.
a run.sh script has been provided in order to run it from the project folder.

The application exposes a set of REST APIs to access the resources for the different entities:
a. Product  (/products)
b. Offer    (/offers)
c. Basket   (/baskets)

for each entity the REST APIs exposes the CRUD operations and the Retrieve is doubled in an "id" or criteria type model.
The Basket update performs an automatic updates of the Items amounts (partial/total) and a specific "basket add" endpoint
has been added as a PUT /baskets/{id}/{productId} to add an item to the basket.
Also in this case the basket total/partial amounts will be updated.

The Serverless application relays on a Sparkjava embedded server and uses dynamic configured routes to expose the endpoints.
It uses also Jackson to provide json parsing capabilities and MapDB as an In Memory (OffHeap) storage for the data.
also the DataSources are configurable so the current storage might be easily replace with a different one,
by simply implementing an adapter based on the DataSourceAdapter interface.

The Services are implemented as single operations that can be combined/extended on purpose by defining them
in the specific routing descriptor.

Each operation implements a general Operation interface and this provides a simple way of extending the Operation
just like it is done for the Basked Update and Add Operations.

A facility API has been provided to load some json file containing some dummy entities: /loader/{entity-name}
(example: "/loader/Product" ), this will load the json entities into the DB.

A simplified Unit Testing has been provided for the main components.


