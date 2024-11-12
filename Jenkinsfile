pipeline {
  agent any
  stages {
    stage('Clone') {
      steps {
        git branch: 'main', url: 'https://github.com/your-username/DinoGame.git'
      }
    }
    stage('Build') {
      steps {
        sh 'docker build -t your-dockerhub-username/dino-game .'
      }
    }
    stage('Test') {
      steps {
        echo 'Skipping tests for now...'
        // Add test commands here if you have tests
      }
    }
    stage('Push to Docker Hub') {
      steps {
        withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
          sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD'
          sh 'docker push your-dockerhub-username/dino-game'
        }
      }
    }
    stage('Deploy to Kubernetes') {
      steps {
        echo 'Deploying to Kubernetes...'
        // Add Kubernetes deployment commands here
      }
    }
  }
}
