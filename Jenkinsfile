pipeline{
    agent any
          environment {

        registry = "wafabenyahia/backend"

        registryCredential = 'dockerhub_id'

        dockerImage = ''
    }
    stages{
        stage('GIT'){
            steps {
                echo  'getting project from git';
                 git branch :'wafabenyahia' , url : 'https://github.com/wafa71/devops.git'
            }

        }
           stage('Maven clean'){
            steps {
               sh 'mvn clean'
            }

        }

           stage('Maven compile'){
            steps {
               sh 'mvn compile'
            }

        }
         stage('maven package'){
        steps {
            sh 'mvn package -Dmaven.test.skip=true'
        }
    }
     stage('Maven test'){
            steps {
              sh 'mvn test -Dmaven.test.skip=true'
            }

        }

           stage('mvn sonarQube'){
            steps {
                sh 'mvn sonar:sonar \
  -Dsonar.projectKey=devops \
  -Dsonar.host.url=http://192.168.1.18:9000 \
  -Dsonar.login=1177180e205a7dc9fae9d52d8c48506cd8b2f3ee'
            }
        }
          stage('nexus'){
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
        //  stage('Building our image') {

        //     steps {

        //         script {

        //           dockerImage = docker.build registry + ":$BUILD_NUMBER"

        //         }

        //     }
        // }
        //      stage('Deploy our image') {
        //     steps {

        //         script {

        //             docker.withRegistry( '', registryCredential ) {

        //                 dockerImage.push()
        //             }

        //         }

        //     }

        // }

        // stage('Cleaning up') {

        //     steps {

        //         sh "docker rmi $registry:$BUILD_NUMBER"

        //     }

        // }

              stage('docker compose') {

            steps {

                sh "docker-compose up -d"

            }

        }

}}