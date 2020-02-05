# Starwars

#### Rodando a aplicação localmente:

1.Inicie a imagem do Mysql utilizando o docker-compose:
```docker-compose up```
        

2.Construa o projeto:
``` ./gradlew clean build``` 
        
3.Navegue até o jar:
```cd build```
        
4.Execute o jar:
 ```java -jar starwars-0.0.1-SNAPSHOT.jar``` 
 
5.Acesse o Swagger:
  ```http://localhost:8080/swagger-ui.html`