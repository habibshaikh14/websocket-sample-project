# websocket-sample-project
This is a sample project for learning the implementation of websockets between front-end and back-end.

:::Tech Stack:::
Front-End: Angular
Back-End: Spring

:::Setup process:::
1. Clone the repository.
2. Open the demo-back as a maven project in any Java IDE.
3. Run command "npm install" in the demo-front folder.

:::Local Deployment:::
- Backend:
  1. Run maven install in the demo-back project.
  2. Run the main class in the IDE or run the application as a spring boot APP.
  3. Application started on port: http://localhost:8080.
- Front
  1. Run npm start command in the demo-front project.
  2. Application started on port: http://localhost:4200.

:::Demo:::
1. Launch the front-end using http://localhost:4200.
2. Click on connect to create a websocket connection.
3. You will be given an ID for this websocket client connection.
4. Use Endpoint: http://localhost:8080/sendAll/{message} to send message to all client connections.
5. Use Endpoint: http://localhost:8080/send/{id}/{message} to send message to a specific client connection.
