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
           mvn test
        '''
              
            }
        }
       
        
        
    }
    
}
