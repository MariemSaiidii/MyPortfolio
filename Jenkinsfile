pipeline {
    agent any
    
    tools {
        jdk 'Java17'
        maven 'Maven3'
        nodejs 'Node18'
    }

    environment {
        MYSQL_DB = 'database'
        MYSQL_PORT = '3306'
        APP_NAME = "portfolio-app-cicd-pipeline"
        RELEASE = "1.0.0"
        DOCKER_USER = "mariem631" // Updated to your Docker Hub username
        DOCKER_PASS = 'dockerhub'
        IMAGE_NAME = "${DOCKER_USER}/${APP_NAME}"
        IMAGE_TAG = "${RELEASE}-${BUILD_NUMBER}"
    }

    stages {
        stage('Start MySQL (no password)') {
            steps {
                sh 'docker rm -f mysql-test || true'
                sh '''
                    docker run --name mysql-test \
                        -e MYSQL_ALLOW_EMPTY_PASSWORD=yes \
                        -e MYSQL_DATABASE=$MYSQL_DB \
                        -p $MYSQL_PORT:3306 \
                        -d mysql:8.0 \
                        --default-authentication-plugin=mysql_native_password

                    echo "Waiting for MySQL to start..."
                    sleep 20
                '''
            }
        }

        stage("Cleanup Workspace") {
            steps {
                cleanWs()
            }
        }

        stage("Checkout from SCM") {
            steps {
                git branch: 'main', credentialsId: 'github', url: 'https://github.com/MariemSaiidii/MyPortfolio.git'
            }
        }

        stage("Build Application") {
            steps {
                script {
                    // Build Angular frontend
                    dir('portfolio-frontend') {
                        sh 'npm install'
                        sh 'npm run build --prod'
                    }

                    // Build Spring Boot backend
                    dir('portfolio-backend') {
                        sh 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage("Test Application") {
            steps {
                dir('portfolio-backend') { // Changed from demo1 to portfolio-backend
                    sh 'mvn test'
                }
            }
        }

        stage('SonarQube Analysis Backend') {
            steps {
                dir('portfolio-backend') {
                    script {
                        withSonarQubeEnv(credentialsId: 'jenkins-sonarqube-token') {
                            sh 'mvn sonar:sonar'
                        }
                    }
                }
            }
        }

        stage('SonarQube Frontend Analysis') {
            steps {
                dir('portfolio-frontend') {
                    script {
                        withSonarQubeEnv(credentialsId: 'jenkins-sonarqube-token') {
                            sh 'npm install sonar-scanner'
                            sh 'npx sonar-scanner -Dsonar.projectKey=frontend-project-key -Dsonar.sources=src'
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    waitForQualityGate abortPipeline: false, credentialsId: 'jenkins-sonarqube-token'
                }
            }
        }

        stage('Build & Push Docker Images') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_PASS) {
                        def backendImage = docker.build("${IMAGE_NAME}-backend:${IMAGE_TAG}", 'portfolio-backend/') // Changed from demo1
                        backendImage.push()
                        backendImage.push('latest')

                        def frontendImage = docker.build("${IMAGE_NAME}-frontend:${IMAGE_TAG}", 'portfolio-frontend/')
                        frontendImage.push()
                        frontendImage.push('latest')
                    }
                }
            }
        }

        stage("Trivy Scan") {
            steps {
                script {
                    sh 'docker run -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image mariem631/portfolio-app-cicd-pipeline-frontend:latest --no-progress --scanners vuln --exit-code 0 --severity HIGH,CRITICAL --format table' // Updated to mariem631
                    sh 'docker run -v /var/run/docker.sock:/var/run/docker.sock aquasec/trivy image mariem631/portfolio-app-cicd-pipeline-backend:latest --no-progress --scanners vuln --exit-code 0 --severity HIGH,CRITICAL --format table' // Updated to mariem631
                }
            }
        }

        stage('Stop MySQL') {
            steps {
                sh '''
                    docker stop mysql-test || true
                    docker rm mysql-test || true
                '''
            }
        }

        stage('Cleanup Artifacts') {
            steps {
                script {
                    sh "docker rmi ${IMAGE_NAME}-backend:${IMAGE_TAG} || true"
                    sh "docker rmi ${IMAGE_NAME}-backend:latest || true"
                    sh "docker rmi ${IMAGE_NAME}-frontend:${IMAGE_TAG} || true"
                    sh "docker rmi ${IMAGE_NAME}-frontend:latest || true"
                }
            }
        }

        /* 
        stage('Trigger CD Pipeline') {
            steps {
                script {
                    sh "curl -v -k --user clouduser:${JENKINS_API_TOKEN} -X POST -H 'cache-control: no-cache' -H 'content-type: application/x-www-form-urlencoded' --data 'IMAGE_TAG=${IMAGE_TAG}' '9.163.88.247:8080/job/gitops-cdpipeline/buildWithParameters?token=argocd-token'"
                }
            }
        }
        */
    }

    post {
        always {
            echo 'Pipeline finished'
        }
    }
}