# Heroes
This project it is an example of an API for managing heroes Marvel and DC COMICS.

#### Hero json example:
```json
{
    "name":"Heroe_Abc",
    "biography":"Biography_test",
    "gender":"gender_test", 
    "universe":"Universe_test", 
    "abilities":[
        "exceptional martial artist",
        "supernatural knowledge",
        "master swordsman"
    ]
}
```
### Premise
* Required: JDK 16
* Run using Docker:
  * Required: Docker
  * Required: Docker Compose

### Run Tests
Linux
```sh
sudo ./mvnw test
```
Windows
```sh
mvnw.cmd test
```

### Build and Run Application using Docker
#### Build Image
Linux
```sh
sudo ./mvnw clean package -DskipTests 

sudo docker build -t heroes .
```
Windows
```sh
mvnw.cmd clean package -DskipTests && docker build -t heroes .
```

#### Run
Linux
```sh
sudo docker-compose up
```
Windows
```sh
docker-compose up
```

### Curls Examples
* Create Hero
```sh
curl -d '{"name":"Heroe_Abc", "biography":"Biography_test", "gender":"gender_test", "universe":"Universe_test", "abilities":["exceptional martial artist", "supernatural knowledge", "master swordsman"]}' -H "Content-Type: application/json" -X POST http://localhost:8080/heroes/create -w "HTTP Status - %{http_code}"
```
* List Heroes
```sh
curl -H "Content-Type: application/json" -X GET http://localhost:8080/heroes -w "HTTP Status - %{http_code}"
```
* Get Hero
```sh
curl -H "Content-Type: application/json" -X GET http://localhost:8080/heroes/Heroe_Abc -w "HTTP Status - %{http_code}"
```
* Delete Hero
```sh
curl -H "Content-Type: application/json" -X DELETE http://localhost:8080/heroes/Heroe_Abc -w "HTTP Status - %{http_code}"
```