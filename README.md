# Dockerize-Microservices-KonG-Swagger-Security-JWT

  # To register in docker we need to build the jar file 

    mvn clean install

  i have an  dockercompose.yaml file in the project so docker compose up the file

# To Implement the Kong gateway we need to install Kong Api Gateway ...

HERE I AM USING THE DOCKER TO PULL THE KONG AND KONG DATABASE AND RUN ALL THE ENDPOINTS IN AN SINGLE PORT

docker run -d --name kong-database \
--network=micro \
-p 5555:5432 \
-e "POSTGRES_USER=kong" \
-e "POSTGRES_DB=kong" \
-e "POSTGRES_PASSWORD=postgres" \
postgres:latest



docker run -it --rm \
--network=micro \
-e "KONG_DATABASE=postgres" \
-e "KONG_PG_HOST=kong-database" \
-e "KONG_PG_PASSWORD=postgres" \
kong:latest kong migrations bootstrap


docker run -d --name kong \
--network=micro \
-e "KONG_LOG_LEVEL=debug" \
-e "KONG_DATABASE=postgres" \
-e "KONG_PG_HOST=kong-database" \
-e "KONG_PG_PASSWORD=postgres" \
-e "KONG_PROXY_ACCESS_LOG=/dev/stdout" \
-e "KONG_ADMIN_ACCESS_LOG=/dev/stdout" \
-e "KONG_PROXY_ERROR_LOG=/dev/stderr" \
-e "KONG_ADMIN_ERROR_LOG=/dev/stderr" \
-e "KONG_ADMIN_LISTEN=0.0.0.0:8001, 0.0.0.0:8444 ssl" \
-p 9000:8000 \
-p 9443:8443 \
-p 9001:8001 \
-p 9444:8444 \
kong:latest


# After the Kong is running as a container in the Docker

  WE NEED TO REGISTER ALL THE SERVICES IN THE DOCKER 

  => To register the end points in the kong services
------------------------------------------
curl -i -X POST \
--url http://localhost:9001/services/ \
-H 'Content-Type: application/json' \
-d '{
  "name": "authenticate",
  "url": "http://192.168.96.3:8081/authenticate",
  "host": "api.ct.id"
}'


curl -i -X POST \
--url http://localhost:9001/services/authenticate/routes \
-H 'Content-Type: application/json' \
-d '{                                          
  "paths": ["/authenticate"]
}'

curl -i -X POST \
--url http://localhost:9000/authenticate \
-H 'Content-Type: application/json' \
-d '{"username": "admin", "password": "admin"}'

————————>save-emp
 curl -i -X POST \
--url http://localhost:9001/services/ \
-H 'Content-Type: application/json' \
-d '{
  "name": "saveemp",     
  "url": "http://192.168.96.7:8082/",            
  "host": "api.ct.id"
}'




curl -i -X POST \
--url http://localhost:9001/services/saveemp/routes \
-H 'Content-Type: application/json' \
-d '{
  "paths": ["/"]            
}'

———————————>save-dept
curl -i -X POST \
--url http://localhost:9001/services/ \
-H 'Content-Type: application/json' \
-d '{
  "name": "savedept",     
  "url": "http://192.168.96.3:8081/",            
  "host": "api.ct.id"
}'


curl -i -X POST \
--url http://localhost:9001/services/savedept/routes \
-H 'Content-Type: application/json' \
-d '{
  "paths": ["/"]
}'
———————————>get employed by id with departments

curl -i -X POST \
--url http://localhost:9001/services/ \
-H 'Content-Type: application/json' \
-d '{
  "name": "get",   
  "url": "http://192.168.96.5:8082/1",   
  "host": "api.ct.id"
}'


 curl -i -X POST \
--url http://localhost:9001/services/get/routes \
-H 'Content-Type: application/json' \
-d '{
  "paths": ["/1"]           
}'

---------------------------------------------
// This is an example for the registering the service end points .... you need to register all the end points to make the all the endpoints run in the same port.

we can see all the services in the http://localhost:9001/services using get method.
we can also post all the service end points using the post method using the http://localhost:9001/services with the JSON Body of service end points details.

# To view the swagger api for the end points 

Hit http://localhost:8082/swagger-ui/index.html for employee controller.
Hit http://localhost:8081/swagger-ui/index.html for department-controller.

# To change the JWT expiraation time

create custom.properties file in outside of the folder and uncomment the config and custom property locator.

