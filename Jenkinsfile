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
        DOCKER_USER = "mariem631"
        DOCKER_PASS = 'dockerhub' // This should be a credential ID, not a literal; see notes below
        IMAGE_NAME = "${DOCKER_USER}/${APP_NAME}"
        IMAGE_TAG =  "${RELEASE}-${BUILD_NUMBER}"
        SONAR_HOST_URL = "http://localhost:9000" // Replaced placeholder with assumed local URL
    }

    stages {
        stage('Start MySQL (no password)') {
            steps {
                bat 'docker rm -f mysql-test || exit /b 0'
                bat 'docker run --name mysql-test -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -e MYSQL_DATABASE=%MYSQL_DB% -p %MYSQL_PORT%:3306 -d mysql:8.0 --default-authentication-plugin=mysql_native_password'
                bat 'echo Waiting for MySQL to start...'
                powershell 'Start-Sleep -Seconds 20'
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
                    dir('portfolio-frontend') {
                        bat 'npm install'
                        bat 'npm run build --prod'
                    }
                    dir('portfolio-backend') {
                        bat 'mvn clean package -DskipTests'
                    }
                }
            }
        }

        stage("Test Application") {
            steps {
                dir('portfolio-backend') {
                    bat 'mvn test'
                }
            }
        }

        stage('SonarQube Analysis') {
            parallel {
                stage('Backend Analysis') {
                    steps {
                        dir('portfolio-backend') {
                            withSonarQubeEnv(credentialsId: 'SonarQube', installationName: 'SonarQube') {
                                bat '''
                                    mvn sonar:sonar ^
                                    -Dsonar.projectKey=portfolio-backend ^
                                    -Dsonar.sources=src/main/java ^
                                    -Dsonar.exclusions=**/test/**,**/target/**,**/generated/** ^
                                    -Dsonar.java.binaries=target/classes
                                '''
                            }
                        }
                    }
                }
                stage('Frontend Analysis') {
                    steps {
                        dir('portfolio-frontend') {
                            withSonarQubeEnv(credentialsId: 'SonarQube', installationName: 'SonarQube') {
                                bat '''
                                    npx sonar-scanner ^
                                    -Dsonar.projectKey=portfolio-frontend ^
                                    -Dsonar.sources=src ^
                                    -Dsonar.exclusions=node_modules/**,dist/**,**/test/**,**/generated/** ^
                                    -Dsonar.nodejs.executable.path=node
                                '''
                            }
                        }
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    timeout(time: 10, unit: 'MINUTES') { // Keep at 10 min, aim to stay under
                        def qg = waitForQualityGate(abortPipeline: false)
                        if (qg.status != 'OK') {
                            echo "Quality Gate status: ${qg.status}. Review issues in SonarQube dashboard."
                        }
                    }
                }
            }
        }

        stage('Build & Push Docker Images') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    script {
                        docker.withRegistry('https://index.docker.io/v1/', 'dockerhub') {
                            def backendImage = docker.build("${IMAGE_NAME}-backend:${IMAGE_TAG}", 'portfolio-backend/')
                            backendImage.push()
                            backendImage.push('latest')

                            def frontendImage = docker.build("${IMAGE_NAME}-frontend:${IMAGE_TAG}", 'portfolio-frontend/')
                            frontendImage.push()
                            frontendImage.push('latest')
                        }
                    }
                }
            }
        }

        stage('Stop MySQL') {
            steps {
                bat 'docker stop mysql-test || exit /b 0'
                bat 'docker rm mysql-test || exit /b 0'
            }
        }

        stage('Cleanup Artifacts') {
            steps {
                script {
                    bat "docker rmi ${IMAGE_NAME}-backend:${IMAGE_TAG} || exit /b 0"
                    bat "docker rmi ${IMAGE_NAME}-backend:latest || exit /b 0"
                    bat "docker rmi ${IMAGE_NAME}-frontend:${IMAGE_TAG} || exit /b 0"
                    bat "docker rmi ${IMAGE_NAME}-frontend:latest || exit /b 0"
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline finished'
        }
    }
}
