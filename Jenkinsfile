pipeline {
    agent any
    
    stages {
        stage('GIT') {
            steps {
                git branch :'Omar_Benromdhane' ,
                url : 'https://github.com/wafa71/devops.git'
            }
        }
        //stage('CLEAN INSTALL') {
          //  steps {
            //    sh 'mvn clean install -Dmaven.test.skip=true'
            //}
       // }
        stage('COMPILE STAGE') {
            steps {
                sh 'mvn clean install -Dmaven.test.skip=true'
                sh 'mvn compile'
            }
        }
        stage('PACKAGING STAGE') {
            steps {
                sh 'mvn package -Dmaven.test.skip=true'
            }
        }
        stage('SONARQUBE STAGE'){
            steps {
                   withSonarQubeEnv(installationName: 'sq'){
                   sh 'mvn clean package sonar:sonar'
               }
            }
        }
        
        
        
         /*stage('Nexus'){
            steps {
               sh 'mvn deploy -DskipTests -s setting.xml'
                }
        }*/
        stage('DOCKER BUILD IMAGE STAGE'){
            steps {
                script{
                        sh 'docker build -t omarbnr/omar_bnr:latest .'
                    }
                }
        }
        /*stage('Login'){
            steps {
                        sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                }
        }*/
        stage('DOCKER PUSH IMAGE STAGE'){
            steps {
            withCredentials([usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
        	sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
            sh 'docker push omarbnr/omar_bnr:latest'
                }
        }
        }
        stage('DOCKER REMOVING IMG') {
            steps {
                sh 'docker rmi omarbnr/omar_bnr:latest'
            }
        }
        stage('DOCKER COMPOSE STAGE') {
            steps{
                script{
                        sh 'docker-compose up -d'
                    }
            }
        }
        
        /*stage('MVN TEST STAGE') {
        steps{
            sh'mvn test'
        }
        post {
            always {
            junit testResults: '* ///target/surefire-reports/.xml', allowEmptyResults: true
        }
        }
          
        }*/
        /* stage('MOCKITO TEST STAGE') {
            steps {
           sh 'mvn clean test -DfailIfNoTests=false -Dtest=com.esprit.examen.services.SecteurActiviteServiceImplMock' 
            }
        }*/
        
       /*  stage('JUNIT TEST STAGE') {
            steps {
            sh 'mvn clean test -DfailIfNoTests=false -Dtest=com.esprit.examen.services.SecteurActiviteServiceImplTest -Dmaven.test.failure.ignore=true'  
            sh 'mvn clean test -DfailIfNoTests=false -Dtest=com.esprit.examen.services.SecteurActiviteServiceImplTest -Dmaven.test.failure.ignore=true'
            }
        }*/
        
        stage("QUALITY GATE"){
            steps {
                   timeout(time: 3, unit: 'MINUTES'){
                       waitForQualityGate abortPipeline: true
                   }
               }
            }
        stage('EMAIL STAGE ') {
        steps{
            mail bcc: '',
            body: 'congratulations Omar, the pipeline is working ',
            cc: '', from: '',
            replyTo: '', subject: '[test valiation] The pipeline is working on something here ...',
            to: 'omar.benromdhane@esprit.tn'
        }
        }  

    }
}
