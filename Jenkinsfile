pipeline {
    environment { 
        registry = "aymenarfaoui/devops_achat" 
        registryCredential = 'aymenarfaoui' 
        dockerImage = '' 
    }
    agent any
    stages {
        stage('Checkout GIT') {
            steps {
                echo "Getting project from Git"
                // Get some code from a GitHub repository
                git url:'https://github.com/wafa71/devops.git', branch: 'Aymen_Arfaoui';
            }
        }
        stage('Building our image') {
            steps {
                script { 
                    dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                }
            } 
        }
        stage('Deploy our image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()
                    }
                }
            }
        }
        stage('Cleaning up') { 
            steps { 
                sh "docker rmi $registry:$BUILD_NUMBER" 
            }
        }
        stage('MVN CLEAN') {
            steps {
                // maven clean
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
           stage('MVN COMPILE') {
            steps {
                // maven compile
                sh 'mvn -Dmaven.test.failure.ignore=true compile'
            }
        }
         stage('JUNIT / MOCKITO') {
            steps {
                // maven compile
                sh 'mvn -Dmaven.test.failure.ignore=true test'
            }
        }
        stage('MVN SONARQUBE') {
            steps {
                // maven compile
                sh 'mvn -Dmaven.test.failure.ignore=true -Dsonar.login=admin -Dsonar.password=0000 sonar:sonar'
            }
        }
        stage('NEXUS') {
            steps {
                sh "mvn deploy -DskipTests";
            }
        }
    }
}