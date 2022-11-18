pipeline {
    agent any
   
    stages {
        stage('GIT') {
          
         steps {
                echo 'cloning project from GIT'
                git branch : "Aymen_Arfaoui" , 
                url :'https://github.com/wafa71/devops.git'
            }
        }
        stage('MVN CLEAN') {
            steps {
               sh 'mvn clean'
            }
        }
          stage('Test') {
            steps {
               sh 'mvn test'
            }
        }
        stage('MVN COMPILE') {
            steps {
               sh 'mvn compile'
            
           }
        }
      
        stage ('Sonar'){
            steps {
        
    sh "mvn sonar:sonar \
  -Dsonar.projectKey=achat \
  -Dsonar.host.url=http://169.254.83.100:9000 \
  -Dsonar.login=43b7738fdac4f83cd58f48c2207078c3ca3e00fb"

    }
        }
        stage('Nexus') {
      steps {
        sh 'mvn deploy:deploy-file -DgroupId=tn.esprit.rh \
  -DartifactId=achat \
  -Dversion=1.0 \
  -Dpackaging=jar\
  -Dfile=target/achat-1.0.jar  \
  -DgeneratePom=true \
  -DrepositoryId=achat.repo\
  -Durl=http://192.168.1.18:8081/repository/maven-releases/ '
        
      }
    }
     stage("Building Docker Image") {
                steps{
                    sh 'docker build -t aymenarfaoui/achat .'
                }
        }
        
        
           stage("Login to DockerHub") {
                steps{
                   // sh 'sudo chmod 666 /var/run/docker.sock'
                    sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u aymenarfaoui -p waterpolo12'
                }
        }
        stage("Push to DockerHub") {
                steps{
                    sh 'docker push aymenarfaoui/achat'
                }
        }
    
               stage("Docker-compose") {
                steps{
                    sh 'docker-compose up -d'
                }
        }
    }
}