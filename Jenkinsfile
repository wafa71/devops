pipeline {
    agent any

    stages {
       stage('Git checkout') {
            steps {
                echo 'PULLING ..';
                    git branch : 'skanderKahouli',
                    url : 'https://github.com/wafa71/devops.git';
            }
        }
          stage('Unit Testing'){
            steps {
               echo 'testing ..'
               sh 'mvn clean test -Ptest'
            }
        }
         stage('Maven compile'){
            steps {
               echo 'compiling ..'
               sh 'mvn -Dmaven.test.failure.ignore=true compile'
            }
        }
       stage('Maven clean '){
            steps {
               echo 'cleaning ..'
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            
        }
       
             stage('Sonarqube'){
            steps {
                echo ' scanning ..'
               withSonarQubeEnv(installationName: 'sonarQb'){
                   sh 'mvn -Dmaven.test.failure.ignore=true clean package sonar:sonar'
               }
               
            }
        }
       
       
     
        stage('Build Artifact'){
            steps {
               echo 'deploy ..'
               sh 'mvn -DskipTests -Dmaven.test.failure.ignore=true clean deploy'
            }
        }
        stage('Build Docker image') { 

            steps { 

               sh 'docker build -t skander26/devops:latest .'
            } 
        }

        stage('Deploy Image to DockerHub') { 
          steps {
                 withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
         	sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
             sh 'docker push skander26/devops:latest'
           }
         }

        } 

        stage('Cleaning up') { 

            steps { 

                sh "docker rmi skander26/devops:latest" 
            }

        }
      
    }
}