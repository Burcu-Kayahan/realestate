pipeline {
    agent any
    environment {
        APP_NAME="house"
        APP_STACK_NAME="rl-prj"
        APP_REPO_NAME="real_project_fronted"
        APP_REPO_NAME_1="real_project_backend"
        AWS_REGION="us-east-1"
        AWS_ACCOUNT_ID=sh(script: 'export PATH="$PATH:/usr/local/bin" && aws sts get-caller-identity --query Account --output text', returnStdout: true).trim()
        ECR_REGISTRY="${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_REGION}.amazonaws.com"
        AWS_ACCESS_KEY_ID = credentials('aws_access_key_id')
        AWS_SECRET_ACCESS_KEY = credentials('aws_secret_access_key')
        DATABASE_USERNAME=credentials('database_username')
        DATABASE_PASSWORD = credentials('database_password')
        DATABASE_URL=credentials('database_url')
        DATABASE_NAME=credentials('database_name')

    }
    stages {

    
        stage('Update Configuration File') {
    steps {
        script {
            def configFile = './Prettier_Homes_BE-master/src/main/resources/application.properties'

            def fileContent = readFile configFile

            // Dosyadaki eski değerleri credential'larla değiştir
            fileContent = fileContent.replaceAll('spring.datasource.url=.*', "spring.datasource.url=jdbc:mysql://${DATABASE_URL}")
            fileContent = fileContent.replaceAll('spring.datasource.username=.*', "spring.datasource.username=${DATABASE_USERNAME}")
            fileContent = fileContent.replaceAll('spring.datasource.password=.*', "spring.datasource.password=${DATABASE_PASSWORD}")

            // Dosyaya değiştirilmiş içeriği yaz
            writeFile file: configFile, text: fileContent
        }
    }
}

    

        stage('Prepare Tags for Docker Images') {
            steps {
                echo 'Preparing Tags for Docker Images'
                script {
                    env.IMAGE_TAG_FE = "${ECR_REGISTRY}/${APP_REPO_NAME}:frontend-prod-ver${BUILD_NUMBER}"
                    env.IMAGE_TAG_BE = "${ECR_REGISTRY}/${APP_REPO_NAME_1}:backend-prod-ver${BUILD_NUMBER}"
                   
                }
            }
        }
        stage('Build App Docker Images') {
            steps {
                echo 'Building App Dev Images'
                sh """
                    docker build --force-rm -t "${IMAGE_TAG_FE}" "${WORKSPACE}/PrettierHomesFE-master"
                    docker build --force-rm -t "${IMAGE_TAG_BE}" "${WORKSPACE}/Prettier_Homes_BE-master"
                    docker image ls
                """
            }
        }
        
        
        stage('Create ECR Repository') {
            steps {
                script {
                    sh """
                        aws configure set aws_access_key_id "$AWS_ACCESS_KEY_ID"
                        aws configure set aws_secret_access_key "$AWS_SECRET_ACCESS_KEY"
                        aws ecr describe-repositories --repository-names "${APP_REPO_NAME}" --region ${AWS_REGION} ||  aws ecr create-repository --repository-name "${APP_REPO_NAME}" --region ${AWS_REGION}
                    """
                }
            }
        }
    
         stage('Create ECR Repository_2') {
            steps {
                echo "Creating ECR Repository ${APP_REPO_NAME_1}"
                sh """
                    aws configure set aws_access_key_id "$AWS_ACCESS_KEY_ID"
                    aws configure set aws_secret_access_key "$AWS_SECRET_ACCESS_KEY"
                    aws ecr describe-repositories --repository-names "${APP_REPO_NAME_1}" --region ${AWS_REGION} || \
                    aws ecr create-repository --repository-name "${APP_REPO_NAME_1}" --region ${AWS_REGION}
                """
            }
        }
        stage('Push Images to ECR Repo') {
            steps {
                echo "Pushing ${APP_NAME} App Images to ECR Repo"
                sh """
                    aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REGISTRY}
                    docker push "${IMAGE_TAG_FE}"
                    docker push "${IMAGE_TAG_BE}"
                """
            }
        }
      
       
      
        stage('Destroy the infrastructure') {
            steps {
                timeout(time: 5, unit: 'DAYS') {
                    input message: 'Approve terminate'
                }
                script {
                                
                    // Prune all local Docker images
                    sh 'docker image prune -af'
                    
                    // Delete the ECR repository (this will remove all images in the repository)
                    sh """
                        aws ecr delete-repository --repository-name ${APP_REPO_NAME} --region ${AWS_REGION} --force
                        aws ecr delete-repository --repository-name ${APP_REPO_NAME_1} --region ${AWS_REGION} --force
                    """
                }
                }
            }
        }
    }
    post {
        always {
            echo 'Deleting all local images'
            sh 'docker image prune -af'
        }
        failure {
            echo 'Delete the Image Repository on ECR due to the Failure'
            sh """
                aws ecr delete-repository --repository-name ${APP_REPO_NAME} --region ${AWS_REGION} --force
                aws ecr delete-repository --repository-name ${APP_REPO_NAME_1} --region ${AWS_REGION} --force
             """
        }
    }

