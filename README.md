# Linkedin Echobox Rest API
A REST API to integrate with ebx-linkedin-sdk, deployed using Docker.

## Prerequisites: 
* Download and install docker and docker-compose
* Download and move to 'linkedin-echobox-restapi'
<br>`$ git clone https://github.com/kmponis-interview-training/linkedin-echobox-restapi.git`

## Deploy with docker-compose 
<br>`$ docker-compose up`

## Test URL and Code Coverage (100%)
<br>`$ open http://<docker_ip_address>:9999/swagger-ui.html`
<br>`$ open linkedin-echobox/target/jacoco-reports/index.html`

### (Optional) Build, deploy and upload using Dockerfile-noDC
* Move to 'linkedin-echobox', Build and Deploy
<br>`$ cd linkedin-echobox`
<br>`$ docker build -t linkedinechoboximage -f Dockerfile-noDC .`
<br>`$ docker run -p 9999:8882 linkedinechoboximage`
* Upload to dockerhub for external use
<br>`$ docker tag linkedinechoboximage kbonis/linkedin-echobox-image:latest`
<br>`$ docker login`
<br>`$ docker push kbonis/linkedin-echobox-image:latest`
