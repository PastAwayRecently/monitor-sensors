# monitor-sensors
Run this commands in terminal in order to run application
1) Rebuild project with 

   mvn clean package  
   
2) Delete saved data with

   docker compose down --volumes   
   
3) To run application in a background use

   docker-compose up --build    
   
4) To get access to db in Docker's terminal run

   docker exec -it postgres_db psql -U admin -d sensor_db


Access to running application via url:
http://localhost:8080/sensors