pipeline {
    agent any
    
    stages {
        stage('Verify Env'){
            steps{
                    sh '''
          docker version
           docker info
          docker-compose version 
           curl --version
           java --version
        '''
              
            }
        }
        stage('Update Project') {
            steps {
                echo 'PULLING ..';
                    git branch : 'oussemaghridki',
                    url : 'https://github.com/wafa71/devops.git';
            }
        }
        stage('Maven clean install'){
            steps {
               echo 'cleaning ..'
               sh 'mvn clean install'
            }
            
        }
        stage('Maven compile'){
            steps {
               echo 'compiling ..'
               sh 'mvn compile'
            }
        }
        stage('Sonarqube'){
            steps {
                echo ' scanning please wait this might take a while ..'
              withSonarQubeEnv(installationName: 'sq1'){
                  sh 'mvn clean package sonar:sonar'
              }
               
            }
        }
       stage("Docker image creation") {
            steps {
                sh 'docker build -t oussemaghridki/backend:latest .'
            }
        }
        stage("Storing docker image") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
        	sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
            sh 'docker push oussemaghridki/backend:latest'
            }
        }
        }
       
        
        stage("Cleaning After Push") {
            steps {
               sh "docker rmi oussemaghridki/backend:latest"
            }
        }
        
        stage("Publish to Nexus Repository") {
            steps {
                sh 'mvn clean deploy -s settings.xml'
            }
        }
        
        stage('Running Mysql server and Backend app')
        {
            steps {
                sh 'docker-compose up --build -d'
                sh 'docker-compose ps'
            }
        }
        
        
    }
    post {
            always {
                sh 'docker-compose down --remove-orphans -v --rmi all'
                sh 'docker-compose ps'
            }
            success {
                emailext attachLog: true, body: '', subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: 'ghridki.oussema@esprit.tn'
            }
            failure {
                emailext attachLog: true, body: '', subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!', to: 'ghridki.oussema@esprit.tn'
            }
            
        }
}
