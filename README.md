# ChessDocker
#### This application provides Rest APIs to allow you to play against the chess AI. 
The application can be run using either sbt or by containerising it with Docker/Capstan. 
Method 1: Docker
To run the application, use `sbt run`

The application is designed to accept user input via rest commands by default. To play against itself, the value in the application.conf file must be changed to one and the output for the functions in rest controller must be Unit.

The application has 4 main interfaces linked to the 4  rest controllers. Furthermore, the RestTemplate is handled by the UserConsumeService.scala

There are 2 ways to start:
1) send a firststart request of the format:
> {
>       "username": "clientA",
>       "color": "White",
>       "from_pos": "",
>       "to_pos": ""        
> }

2) Send a start request
> {
>       "username": "clientA",
>       "color": "White",
>       "from_pos": "a2",
>       "to_pos": "a3"   
> }

  and you will recieve back a json response of the form:
> {
>       "username": "computer2",
>       "from_pos": "b2",
>       "to_pos": "b3",
>       "promoted": null
> }

Send a move request and recieve back a json response of the same form. 
If the end is achieved, then a end is sent by the machine through RestTemplate.
If game is over on user side, the client is supposed to signal end of game.  
> {
>       "username": "clientA",
>       "from_pos": "b2",
>       "to_pos": "b3",
>       "promoted": null
> }
> 
> {
>       "username": "computer2",
>       "from_pos": "g2",
>       "to_pos": "g3",
>       "promoted": null   
> }

Send a end request and recieve back a end request with the opponent's name:   
>  {
>     "username":"clientA",
>     "message":"Stalemate! Draw!"   
>  }
> 
>  {
>     "username":"computer2",
>     "message":"Stalemate! Draw!"   
>  }

To build the images, Capstan and Docker are required in your system.
Install all necessary dependencies for the application
To develop the jar, I've used SBT Native Packager.  
To create jar for Capstan, use  
`sbt universal:stage`  
Since I'm running the application in Windows and needed Linux for Capstan,
take only the **/universal** folder from target, place it in a folder with **Capstanfile**
and the **package.yaml** and **run.yaml** in a folder inside the **main** folder called **meta**.  
create package using  
`capstan package compose -p chess-spring`  
run using  
`capstan run chess-spring -p qemu --boot default`  
To create jar for Docker, use  
`sbt docker:publishLocal`  
run the container using   
`$docker run --rm -p8080:8080 <package_name>`  





